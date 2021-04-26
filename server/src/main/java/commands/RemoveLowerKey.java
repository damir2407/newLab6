package commands;

import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'remove_lower_key'. Removes elements lower than user entered.
 */
public class RemoveLowerKey implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "remove_lower_key null";
    private Messenger messenger;

    public RemoveLowerKey(CollectionKeeper collectionManager, Messenger messenger) {
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
        Integer key = Integer.parseInt(String.valueOf(args[0]));
        collectionManager.sortCollection();
        if (collectionManager.removeByKeyIfLower(key) > 0) {
            return new Result().ok(messenger.successfullyDeleteMessage());
        } else
            return new Result().ok(messenger.notSuccessfullyDeleteByKeyMessage());
    }
}
