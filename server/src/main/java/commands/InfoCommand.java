package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;

import java.util.Date;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "info";
    private Messenger messenger;

    public InfoCommand(CollectionKeeper collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public ResultKeeper execute(Object... args) {
        String lastInitializationField;
        Date lastInitialization = collectionManager.getLastInitialization();
        if (lastInitialization == null) {
            lastInitializationField = messenger.hasNotBeenInitializationYetMessage();
        } else {
            lastInitializationField = lastInitialization.toString();
        }

        String lastSaveField;
        Date lastSave = collectionManager.getLastSave();
        if (lastSave == null) {
            lastSaveField = messenger.hasNotBeenSaveYetMessage();
        } else {
            lastSaveField = lastSave.toString();
        }

        return new Result().ok(messenger.getCollectionInfo(collectionManager.getType(), collectionManager.size(), lastInitializationField, lastSaveField, collectionManager.getPath()));

    }
}