package file_works;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.SpaceMarine;
import messenger.Messenger;
import server_validate.Answer;
import server_works.ServerSendKeeper;

import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.util.*;

public class Transform implements Transformer {
    private FileKeeper fileManager;
    private Gson gson;
    private FileCheckKeeper fileFieldsChecker;
    private Messenger messenger;
    private ServerSendKeeper serverSender;
    private InetAddress inetAddress;
    private int port;

    public Transform(FileKeeper fileManager, Gson gson, FileCheckKeeper fileFieldsChecker,
                     Messenger messenger, InetAddress inetAddress, int port, ServerSendKeeper serverSender) {
        this.fileManager = fileManager;
        this.fileFieldsChecker = fileFieldsChecker;
        this.gson = gson;
        this.messenger = messenger;
        this.inetAddress = inetAddress;
        this.port = port;
        this.serverSender = serverSender;
    }

    @Override
    public Map<Integer, SpaceMarine> convertFromJson() {
        Map<Integer, SpaceMarine> marines = null;
        try {
            marines = gson.fromJson(fileManager.load(), new TypeToken<Map<Integer, SpaceMarine>>() {
            }.getType());
            if (marines == null) throw new NoSuchElementException();

            if (fileFieldsChecker.check(marines).isOK())
                serverSender.send(new Answer().ok(messenger.collectionSuccessfullyMessage()), inetAddress, port);
            else {
                serverSender.send(new Answer().error(fileFieldsChecker.check(marines).getErrorMessage()), inetAddress, port);
                marines.clear();
            }
        } catch (JsonSyntaxException | NumberFormatException exception) {
            serverSender.send(new Answer().error(messenger.jsonSyntaxMessage()), inetAddress, port);
            return null;
        } catch (NoSuchElementException e) {
            serverSender.send(new Answer().error(messenger.noSuchElementInFileMessage()), inetAddress, port);
            return null;
        } catch (FileNotFoundException | NullPointerException exception) {
            serverSender.send(new Answer().error(messenger.fileNotFoundMessage()), inetAddress, port);
            return null;
        } catch (SecurityException e) {
            serverSender.send(new Answer().error(messenger.securityErrorMessage()), inetAddress, port);
            return null;
        }
        return marines;
    }
}
