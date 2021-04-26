package command_works;


/**
 * interface for CommandReader to read commands
 */
public interface ClientReadKeeper {


    void interactiveMode(ClientExecuteKeeper commandClientExecuteKeeper);
}
