package commands;

import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;


/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class ClientExitCommand implements ClientCommand {
    private final String name = "exit";
    private Messenger messenger;


    public ClientExitCommand(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public ResultKeeper execute(String args) {
        if (!args.isEmpty()) {
            return new Result().error(messenger.argumentErrorMessage(name, false));
        }
        return new Result().ok(messenger.successfullyExitMessage());
    }
}
