package commands;

import collection_works.CollectionKeeper;
import utility.Result;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand implements ServerCommand {
    private CollectionKeeper collectionManager;
    private final String name = "save";

    public SaveCommand(CollectionKeeper collectionManager) {
        this.collectionManager = collectionManager;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */

    @Override
    public Result<Object> execute(Object... args) {
        try {
            collectionManager.sortCollection();
            collectionManager.saveCollection();
        } catch (Exception e) {
        }
        return null;
    }
}
