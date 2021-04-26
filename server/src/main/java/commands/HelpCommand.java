package commands;


import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;

public class HelpCommand implements ServerCommand {

    private final String name = "help";
    private Messenger messenger;

    public HelpCommand(Messenger messenger) {
        this.messenger = messenger;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public ResultKeeper execute(Object... args) {
        return new Result().ok(messenger.getCommandsDescription());
    }
}

