package commands;

import collection_works.CollectionKeeper;
import data.SpaceMarine;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "remove_greater {element}";
    private Messenger messenger;

    public RemoveGreaterCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        SpaceMarine spaceMarine = (SpaceMarine) args[0];
        if (collectionManager.size() == 0) {
            return new Result().error(messenger.collectionIsEmptyMessage());
        }
        if (collectionManager.removeGreater(spaceMarine) > 0) {
            return new Result().ok(messenger.successfullyDeleteMessage());
        } else
            return new Result().error(messenger.notSuccessfullyDeleteMessage());
    }
}
