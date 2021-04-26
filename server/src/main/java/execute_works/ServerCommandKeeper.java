package execute_works;

import collection_works.CollectionKeeper;
import commands.ServerCommand;
import messenger.Messenger;

import java.util.HashMap;

public interface ServerCommandKeeper {

    void instantiateCommands();

    void putCommands();


    HashMap<String, ServerCommand> getAllCommands();
}
