package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'print_field_descending_heart_count'. print the values of the heartCount field of all elements in descending order
 */
public class PrintFieldDescendingHeartCountCommand implements ServerCommand {

    private CollectionKeeper collectionManager;
    private final String name = "print_field_descending_heart_count";
    private Messenger messenger;

    public PrintFieldDescendingHeartCountCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        return new Result().ok(collectionManager.getSortedHeartCounts());

    }
}

