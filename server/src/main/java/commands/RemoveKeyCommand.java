package commands;


import collection_works.CollectionKeeper;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'remove_key'. Removes element by key.
 */
public class RemoveKeyCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "remove_key null";
    private Messenger messenger;

    public RemoveKeyCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        if (collectionManager.getByKey(key) == false) {
            return new Result().error(messenger.itemNotFoundMessage());
        }
        collectionManager.removeByKey(key);
        return new Result().ok(messenger.successfullyDeleteMessage());
    }
}
