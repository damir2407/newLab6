package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "show";
    private Messenger messenger;

    public ShowCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        collectionManager.sortCollection();

        if (collectionManager.size() == 0) {
            return new Result().error(messenger.collectionIsEmptyMessage());
        }
        return new Result().ok(messenger.getMarineFieldsInformation(collectionManager.getMarinesCollection()));
    }
}


