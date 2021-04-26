package commands;

import server_validate.ResultKeeper;

public interface ServerCommand{

    ResultKeeper execute(Object... args);
}
