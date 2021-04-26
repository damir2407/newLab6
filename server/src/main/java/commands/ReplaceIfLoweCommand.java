package commands;

import collection_works.CollectionKeeper;
import data.SpaceMarine;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'replace_if_lowe'. Replace element if lower
 */
public class ReplaceIfLoweCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "replace_if_lowe null {element}";
    private Messenger messenger;

    public ReplaceIfLoweCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        Integer key = (Integer) args[0];
        SpaceMarine spaceMarine = (SpaceMarine) args[1];
        if (!collectionManager.getByKey(key)) {
            return new Result().error(messenger.itemNotFoundMessage());
        }
        if (collectionManager.replaceIfLowe(key, spaceMarine)) {
            return new Result().ok(messenger.successfullyReplaceMessage());
        } else return new Result().ok(messenger.notSuccessfullyReplaceMessage());
    }
}
