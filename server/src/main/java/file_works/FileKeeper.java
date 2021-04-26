package file_works;

import data.SpaceMarine;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.Map;


/**
 * interface for FileManager
 */
public interface FileKeeper {

    String load() throws FileNotFoundException, SecurityException;

    void writeCollection(Map<Integer, SpaceMarine> collection);


    String getPath();

}
