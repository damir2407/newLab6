package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "clear";
    private Messenger messenger;

    public ClearCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        collectionManager.clearCollection();
        return new Result().ok(messenger.successfullyClearedMessage());
    }


}

