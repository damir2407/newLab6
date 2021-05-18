package execute_works;

import commands.LanguageManager;
import messenger.Messenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request_structure.Request;
import request_structure.RequestKeeper;
import utility.*;
import server_works.ServerSendKeeper;
import utility.Error;

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
    private static final Logger logger = LogManager.getLogger();

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


            Result<Object> result = pickCommand(request);
            if (result instanceof utility.Error) {
                return new Answer().error(((Error<String>) result).getErrorMessage());
            }
            if (result instanceof Success) {
                return new Answer().ok(((Success<?>) result).getObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<Object> pickCommand(RequestKeeper request) {
        Result<Object> result = (serverCommandManager.getAllCommands().get(request.getCommand()).execute(request.getArgs()));
        return result;
    }

    public ServerExecuteKeeper pickLanguage(Messenger messenger) {
        CreateKeeper creator = new Creator(messenger);
        return creator.createObjects(datagramPacket.getAddress(), datagramPacket.getPort(), serverSender, serverReader);
    }
}