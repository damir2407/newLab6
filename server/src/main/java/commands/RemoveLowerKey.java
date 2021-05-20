package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'remove_lower_key'. Removes elements lower than user entered.
 */
public class RemoveLowerKey implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "remove_lower_key null";
    private Messenger messenger;

    public RemoveLowerKey(CollectionManager collectionManager, Messenger messenger) {
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
        try {
            if (collectionManager.size() == 0) {
                return new Error(messenger.collectionIsEmptyMessage());
            }
            Integer key = Integer.parseInt(String.valueOf(args[0]));
            collectionManager.sortCollection();
            if (collectionManager.removeByKeyIfLower(key) > 0) {
                return new Success<String>(messenger.successfullyDeleteMessage());
            } else
                return new Success<String>(messenger.notSuccessfullyDeleteByKeyMessage());
        } catch (NumberFormatException e) {
            return new Error(messenger.numberFormatArgumentMessage());
        }
    }
}
