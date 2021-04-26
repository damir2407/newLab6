package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;

/**
 * Command 'group_counting_by_category'. Group the elements of the collection
 * by the value of the category field, display the number of elements in each group
 */
public class GroupCountingByCategoryCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "group_counting_by_category";
    private Messenger messenger;

    public GroupCountingByCategoryCommand(CollectionKeeper collectionManager, Messenger messenger) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
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
        return new Result().ok(messenger.getCountingByCategory(collectionManager.getCountingCategory()));
    }
}

