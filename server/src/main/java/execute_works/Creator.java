package execute_works;

import collection_works.CollectionKeeper;
import collection_works.CollectionManager;
import com.google.gson.Gson;
import file_works.*;
import messenger.Messenger;
import utility.ServerFieldsValidation;
import utility.ServerValidator;
import server_works.ServerSendKeeper;

import java.net.InetAddress;


public class Creator implements CreateKeeper {
    private Messenger messenger;


    public Creator(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public ServerExecuteKeeper createObjects(InetAddress inetAddress, int port,
                                             ServerSendKeeper serverSender, ServerReadKeeper serverReader) {

        FileKeeper fileManager = new FileManager(System.getenv("My_Path"), messenger);
        ServerValidator fieldsValidation = new ServerFieldsValidation(messenger);
        FileCheckKeeper fileFieldsChecker = new FileFieldsChecker(fieldsValidation, messenger);
        Transformer transform = new Transform(fileManager, new Gson(), fileFieldsChecker, messenger, inetAddress, port, serverSender);
        CollectionKeeper collectionManager = new CollectionManager(fileManager, transform);
        collectionManager.convertToCollection();
        ServerCommandKeeper serverCommandManager = new ServerCommandManager(collectionManager, messenger);
        serverCommandManager.instantiateCommands();
        serverCommandManager.putCommands();
        ServerExecuteKeeper serverExecutor = new ServerExecutor(serverCommandManager);
        serverReader.setCollectionManager(collectionManager);
        return serverExecutor;
    }
}
