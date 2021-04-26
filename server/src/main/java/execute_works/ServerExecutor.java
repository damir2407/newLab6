package execute_works;

import commands.LanguageManager;
import messenger.Messenger;
import request_structure.Request;
import request_structure.RequestKeeper;
import server_validate.Answer;
import server_validate.AnswerKeeper;
import server_validate.ResultKeeper;
import server_works.ServerSendKeeper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class ServerExecutor implements ServerExecuteKeeper {
    private ServerCommandKeeper serverCommandManager;
    private LanguageManager languageManager;
    private boolean isLanguage = true;
    private DatagramPacket datagramPacket;
    private ServerSendKeeper serverSender;
    private ServerReadKeeper serverReader;

    public ServerExecutor(ServerCommandKeeper serverCommandManager) {
        this.serverCommandManager = serverCommandManager;
    }

    public ServerExecutor(LanguageManager languageManager, ServerSendKeeper serverSender, ServerReadKeeper serverReader) {
        this.languageManager = languageManager;
        this.serverSender = serverSender;
        this.serverReader = serverReader;
    }

    public ServerExecutor() {
    }

    @Override
    public AnswerKeeper giveAnswer(DatagramPacket datagramPacket) {
        try {
            this.datagramPacket = datagramPacket;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            RequestKeeper request = (Request) objectInputStream.readObject();


            try {
                if (isLanguage) {
                    if (languageManager.getMapOfLanguages().containsKey(request.getCommand())) {
                        return new Answer().ok(pickLanguage(languageManager.getMapOfLanguages().get(request.getCommand())));
                    }
                }
            } catch (NullPointerException e) {
                isLanguage = false;
            }


            ResultKeeper result = pickCommand(request);
            if (!result.isOK()) {
                return new Answer().error(result.getErrorMessage());
            } else return new Answer().ok(result.getObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultKeeper pickCommand(RequestKeeper request) {
        ResultKeeper result = (serverCommandManager.getAllCommands().get(request.getCommand()).execute(request.getArgs()));
        return result;
    }

    public ServerExecuteKeeper pickLanguage(Messenger messenger) {
        CreateKeeper creator = new Creator(messenger);
        return creator.createObjects(datagramPacket.getAddress(), datagramPacket.getPort(), serverSender, serverReader);
    }
}