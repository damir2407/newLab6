package file_works;

import data.SpaceMarine;
import utility.Result;

import java.util.Map;

public interface FileCheckKeeper {

    Result<Object> check(Map<Integer, SpaceMarine> collection);
}
