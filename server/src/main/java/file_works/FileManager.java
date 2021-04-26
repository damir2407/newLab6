package file_works;

import com.google.gson.Gson;
import data.*;
import messenger.Messenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager implements FileKeeper {

    private Gson gson = new Gson();
    private String path;

    private static final Logger logger = LogManager.getLogger();
    private Messenger messenger;

    public FileManager(String path, Messenger messenger) {
        this.path = path;
        this.messenger = messenger;
    }

    public FileManager(String path) {
        this.path = path;
    }

    @Override
    public String load() throws FileNotFoundException, SecurityException {
        String inputLine;
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNext()) {
                inputLine = scanner.nextLine();
                result.append(inputLine);
            }
        }
        return result.toString();

    }

    /**
     * Writes collection to a file.
     *
     * @param collection Collection to write.
     */
    @Override
    public void writeCollection(Map<Integer, SpaceMarine> collection) {
        try (FileOutputStream collectionFileWriter = new FileOutputStream(new File(path))) {
            collectionFileWriter.write(gson.toJson(collection).getBytes());
            logger.info(messenger.successfullySave());
        } catch (FileNotFoundException | NullPointerException e) {
            logger.info(messenger.canNotSaveFile());
        } catch (IOException e) {
            logger.info(messenger.inputOutputMessage());
        }
    }

    @Override
    public String getPath() {
        return path;
    }


}

