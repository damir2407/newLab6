package commands;

import collection_works.CollectionKeeper;
import data.SpaceMarine;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'insert'. Adds a new element to collection.
 */
public class InsertCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "insert null {element}";
    private Messenger messenger;


    public InsertCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        SpaceMarine spaceMarine = (SpaceMarine) args[1];
        Integer key = (Integer) args[0];
        collectionManager.insertToCollection(key, spaceMarine);
        return new Result().ok(messenger.successfullyAddMessage());

    }
}