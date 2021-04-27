package file_works;

import data.SpaceMarine;
import server_validate.ResultKeeper;

import java.util.Map;
import java.util.NavigableMap;

public interface FileCheckKeeper {

    ResultKeeper check(NavigableMap<Integer, SpaceMarine> collection);
}
