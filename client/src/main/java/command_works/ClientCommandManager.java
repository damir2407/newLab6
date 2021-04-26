package command_works;


import commands.*;

import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintKeeper;


import java.util.HashMap;


/**
 * Class for working with our commands.
 */

public class ClientCommandManager implements ClientCommandKeeper {
    private HashMap<String, ClientCommand> clientCommands = new HashMap<>();
    private HashMap<String, Boolean> availableCommands = new HashMap<>();
    private HashMap<String, AskCommand> askCommands = new HashMap<>();
    private ClientCommand exitCommand;
    private ClientCommand executeScriptCommand;
    private AskCommand insertCommand;
    private AskCommand updateCommand;
    private AskCommand removeGreaterCommand;
    private AskCommand replaceIfLoweCommand;
    private Messenger messenger;
    private PrintKeeper printKeeper;
    private Repeater repeater;

    public ClientCommandManager(Messenger messenger, PrintKeeper printKeeper, Repeater repeater) {
        this.messenger = messenger;
        this.printKeeper = printKeeper;
        this.repeater = repeater;

    }

    @Override
    public void pushCommands() {
        this.exitCommand = new ClientExitCommand(messenger);
        this.executeScriptCommand = new ExecuteScriptCommand(messenger);
        this.insertCommand = new InsertCommand(messenger, printKeeper, repeater);
        this.updateCommand = new UpdateCommand(messenger, printKeeper, repeater);
        this.removeGreaterCommand = new RemoveGreaterCommand(messenger, printKeeper, repeater);
        this.replaceIfLoweCommand = new ReplaceIfLoweCommand(messenger, printKeeper, repeater);
    }


    @Override
    public void clientCommands() {
        clientCommands.put("exit", this.exitCommand);
        clientCommands.put("execute_script", this.executeScriptCommand);
    }

    @Override
    public void mapOfAvailableCommands() {
        availableCommands.put("help", true);
        availableCommands.put("info", true);
        availableCommands.put("show", true);
        availableCommands.put("remove_key", false);
        availableCommands.put("clear", true);
        availableCommands.put("remove_lower_key", false);
        availableCommands.put("average_of_height", true);
        availableCommands.put("group_counting_by_category", true);
        availableCommands.put("print_field_descending_heart_count", true);
    }

    @Override
    public void mapOfAskCommands() {
        askCommands.put("insert", this.insertCommand);
        askCommands.put("update", this.updateCommand);
        askCommands.put("remove_greater", this.removeGreaterCommand);
        askCommands.put("replace_if_lowe", this.replaceIfLoweCommand);
    }


    @Override
    public HashMap<String, ClientCommand> getClientCommands() {
        return clientCommands;
    }

    @Override
    public HashMap<String, Boolean> getAvailableCommands() {
        return availableCommands;
    }


    @Override
    public HashMap<String, AskCommand> getAskCommands() {
        return askCommands;
    }
}
