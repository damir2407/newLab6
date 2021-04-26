package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'average_of_height'.
 * print the average value of the height field for all elements of the collection
 */
public class AverageOfHeightCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "average_of_height";
    private Messenger messenger;

    public AverageOfHeightCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        if (collectionManager.size() == 0) {
            return new Result().error(messenger.collectionIsEmptyMessage());
        }
        return new Result().ok(messenger.averageOfHeightMessage(collectionManager.getAverageOfHeight()));
    }


}

