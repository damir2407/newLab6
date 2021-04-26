package commands;

import request_structure.RequestKeeper;

public interface AskCommand {

    RequestKeeper prepare(String argument);
}
