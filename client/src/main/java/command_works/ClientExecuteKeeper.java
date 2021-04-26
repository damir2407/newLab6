package command_works;

/**
 * interface for CommandExecutor
 */

public interface ClientExecuteKeeper {


    void pickCommand(String[] userCommand);

    void scriptMode(String argument);

}
