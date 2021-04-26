package commands;

import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'execute_script'. Executes scripts from a file. Actually only checks argument and prints messages.
 */
public class ExecuteScriptCommand implements ClientCommand {
    private final String name = "execute_script file_name";
    private Messenger messenger;

    public ExecuteScriptCommand(Messenger messenger) {
        this.messenger = messenger;
    }



    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public ResultKeeper execute(String args) {
        if (args.isEmpty()) {
            return new Result().error(messenger.argumentErrorMessage(name, true));
        }
        return new Result().ok(messenger.successfullyExecuteCommandMessage("execute_command"));
    }
}