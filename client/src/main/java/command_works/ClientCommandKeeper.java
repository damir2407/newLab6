package command_works;


import commands.AskCommand;
import commands.ClientCommand;
import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintKeeper;

import java.util.HashMap;

/**
 * interface for command manager
 */

public interface ClientCommandKeeper {


    HashMap<String, ClientCommand> getClientCommands();


    HashMap<String, Boolean> getAvailableCommands();

    void mapOfAvailableCommands();

    void clientCommands();

    void mapOfAskCommands();

     void pushCommands();

    HashMap<String, AskCommand> getAskCommands();
    
}
