package commands;

import collection_works.CollectionKeeper;
import data.SpaceMarine;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "update id {element}";
    private Messenger messenger;

    public UpdateCommand(CollectionKeeper collectionManager, Messenger messenger) {
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
        Integer id = (Integer) args[0];
        SpaceMarine spaceMarine = (SpaceMarine) args[1];
        Integer key;
        key = collectionManager.getKeyById(id);
        if (key == null) {
            return new Result().error(messenger.itemNotFoundMessage());
        }

        collectionManager.removeById(id);

        collectionManager.insertToCollection(key, spaceMarine);
        return new Result().ok(messenger.successfullyUpdatedMessage());
    }
}

