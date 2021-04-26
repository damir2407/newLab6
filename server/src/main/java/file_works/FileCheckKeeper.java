package file_works;

import data.SpaceMarine;
import server_validate.ResultKeeper;

import java.util.Map;

public interface FileCheckKeeper {

    ResultKeeper check(Map<Integer, SpaceMarine> collection);
}
