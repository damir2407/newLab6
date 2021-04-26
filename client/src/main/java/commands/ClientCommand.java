package commands;

import server_validate.ResultKeeper;

public interface ClientCommand {

    ResultKeeper execute(String args);
}
