package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
import utility.Result;
import utility.Success;


/**
 * Command 'insert'. Adds a new element to collection.
 */
public class InsertCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "insert null {element}";
    private Messenger messenger;


    public InsertCommand(CollectionManager collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */

    @Override
    public Result<Object> execute(Object... args) {
        SpaceMarine spaceMarine = (SpaceMarine) args[1];
        Integer key = (Integer) args[0];
        collectionManager.insertToCollection(key, spaceMarine);
        return new Success<String>(messenger.successfullyAddMessage());

    }
}