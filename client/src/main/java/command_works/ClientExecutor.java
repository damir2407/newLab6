package command_works;

import ask_works.PollKeeper;
import client_works.ClientSendKeeper;
import messenger.Messenger;
import print_works.PrintKeeper;
import request_structure.Request;
import request_structure.RequestKeeper;
import server_validate.ResultKeeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClientExecutor implements ClientExecuteKeeper {

    private ClientCommandKeeper clientCommandManager;
    private PollKeeper poll;
    private Messenger messenger;
    private PrintKeeper printMachine;
    private ClientSendKeeper clientSender;


    public ClientExecutor(ClientCommandKeeper clientCommandManager, PollKeeper poll, Messenger messenger, PrintKeeper printMachine,
                          ClientSendKeeper clientSender) {
        this.clientCommandManager = clientCommandManager;
        this.poll = poll;
        this.messenger = messenger;
        this.printMachine = printMachine;
        this.clientSender = clientSender;
    }


    /**
     * method for choose command
     *
     * @param userCommand to read information
     */
    @Override
    public void pickCommand(String[] userCommand) {
        try {
            //Проверка на клиентскую команду
            if (clientCommandManager.getClientCommands().containsKey(userCommand[0].trim()) && userCommand[0].trim().equals("execute_script")) {
                ResultKeeper result = (clientCommandManager.getClientCommands().get(userCommand[0]).execute(userCommand[1]));
                if (!result.isOK())
                    printMachine.println(result.getErrorMessage());
                else {
                    scriptMode(userCommand[1]);
                }
            } else if (clientCommandManager.getClientCommands().containsKey(userCommand[0].trim())) {
                ResultKeeper result = (clientCommandManager.getClientCommands().get(userCommand[0]).execute(userCommand[1]));
                if (!result.isOK()) {
                    printMachine.println(result.getErrorMessage());
                } else printMachine.println(result.getObject());
                //проверка на сервернуюкоманду
            } else if (clientCommandManager.getAvailableCommands().containsKey(userCommand[0])) {
                if (clientCommandManager.getAvailableCommands().get(userCommand[0]).equals(userCommand[1].isEmpty())) {
                    RequestKeeper request = new Request(userCommand[0], userCommand[1]);
                    clientSender.send(request);
                } else
                    printMachine.println(messenger.argumentErrorMessage(userCommand[0], !clientCommandManager.getAvailableCommands().get(userCommand[0])));
            } else if (clientCommandManager.getAskCommands().containsKey(userCommand[0])) {
                RequestKeeper request = (clientCommandManager.getAskCommands().get(userCommand[0]).prepare(userCommand[1]));
                if (request != null) clientSender.send(request);
            } else printMachine.println(messenger.commandNotFoundMessage(userCommand[0], userCommand[1]));
        } catch (NumberFormatException e) {
            printMachine.println(messenger.numberFormatArgumentMessage());
        }
    }


    /**
     * method for work with execute_command
     *
     * @param argument script direction
     * @return command execute status
     */
    @Override
    public void scriptMode(String argument) {
        boolean t = false;
        List<String> scriptStack = new ArrayList<>();
        String[] userCommand = {"", ""};
        scriptStack.add(argument);

        try (Scanner scriptScanner = new Scanner(new File(argument))) {

            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = poll.getUserScanner();
            poll.setUserScanner(scriptScanner);
            poll.setFileMode(true);
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                printMachine.println(String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) {
                            t = true;
                        }
                    }
                }
                if (t == true) {
                    t = false;
                    printMachine.println(messenger.recursionMessage());
                } else {
                    pickCommand(userCommand);
                }


            } while (scriptScanner.hasNextLine());
            poll.setUserScanner(tmpScanner);
            poll.setUserMode();

        } catch (FileNotFoundException exception) {
            printMachine.printErr(messenger.scriptFileDoesntExistMessage());

        } catch (NoSuchElementException exception) {
            printMachine.printErr(messenger.noSuchElementInputMessage());
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }


    }
}

