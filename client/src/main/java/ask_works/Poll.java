package ask_works;

import client_validate.ClientValidator;
import data.AstartesCategory;
import messenger.Messenger;
import print_works.PrintKeeper;
import utility.Result;
import utility.Success;

import java.util.Scanner;

/**
 * Asks a user a marine's value.
 */

public class Poll implements PollKeeper {

    private Scanner userScanner;
    private boolean fileMode;
    private Messenger messenger;
    private PrintKeeper printMachine;
    private ClientValidator clientValidator;

    public Poll(Scanner userScanner, ClientValidator clientValidator, Messenger messenger, PrintKeeper printMachine) {
        this.userScanner = userScanner;
        this.clientValidator = clientValidator;
        this.fileMode = false;
        this.messenger = messenger;
        this.printMachine = printMachine;
    }


    /**
     * Asks a user the marine's name.
     *
     * @return Marine's name.
     */
    @Override
    public String claimName() {
        String name;
        printMachine.println(messenger.nameInputMessage());
        name = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(name);
        Result<Object> result = clientValidator.finalCheckName(name);
        if (result instanceof Error)
            printMachine.println(((Error) result).getMessage());
        if (result instanceof Success)
            name = ((Success<String>) result).getObject();
        return name;
    }

    /**
     * Sets a scanner to scan user input.
     *
     * @param userScanner Scanner to set.
     */
    @Override
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    @Override
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets marine asker mode to 'User Mode'.
     */
    @Override
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Sets marine asker mode to 'File Mode'.
     */
    @Override
    public void setFileMode(boolean fileMode) {
        this.fileMode = fileMode;
    }

    /**
     * Asks a user the marine's XCoordinate.
     *
     * @return Marine's coordinate X.
     */
    @Override
    public Float claimXCoordinate() {
        String fieldX;
        Float x = null;
        printMachine.println(messenger.xCoordinateInputMessage());
        fieldX = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldX);
        Result<Object> result = clientValidator.finalCheckX(fieldX);
        if (result instanceof Error)
            printMachine.println(((Error) result).getMessage());
        if (result instanceof Success)
            x = ((Success<Float>) result).getObject();
        return x;
    }

    /**
     * Asks a user the marine's YCoordinate.
     *
     * @return Marine's coordinate Y.
     */
    @Override
    public Double claimYCoordinate() {
        String fieldY;
        Double y = null;
        printMachine.println(messenger.yCoordinateInputMessage());
        fieldY = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldY);
        if (clientValidator.finalCheckY(fieldY) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckY(fieldY)).getMessage());
        if (clientValidator.finalCheckY(fieldY) instanceof Success)
            y = ((Success<Double>) clientValidator.finalCheckY(fieldY)).getObject();
        return y;
    }

    /**
     * Asks a user the marine's health.
     *
     * @return Marine's health.
     */
    @Override
    public Float claimHealth() {
        String fieldHealth;
        Float health = null;
        printMachine.println(messenger.healthInputMessage());
        fieldHealth = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldHealth);
        if (clientValidator.finalCheckHealth(fieldHealth) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckHealth(fieldHealth)).getMessage());
        if (clientValidator.finalCheckHealth(fieldHealth) instanceof Success)
            health = ((Success<Float>) clientValidator.finalCheckHealth(fieldHealth)).getObject();
        return health;
    }

    /**
     * Asks a user the marine's heartCount.
     *
     * @return Marine's heartCount.
     */
    @Override
    public Integer claimHeartCount() {
        String fieldHeartCount;
        Integer heartCount = null;
        printMachine.println(messenger.heartCountInputMessage());
        fieldHeartCount = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldHeartCount);
        if (clientValidator.finalCheckHeartCount(fieldHeartCount) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckHeartCount(fieldHeartCount)).getMessage());
        if (clientValidator.finalCheckHeartCount(fieldHeartCount) instanceof Success)
            heartCount = ((Success<Integer>) clientValidator.finalCheckHeartCount(fieldHeartCount)).getObject();
        return heartCount;
    }

    /**
     * Asks a user the marine's height.
     *
     * @return Marine's height.
     */
    @Override
    public float claimHeight() {
        String fieldHeight;
        float height = 0;
        printMachine.println(messenger.heightInputMessage());
        fieldHeight = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldHeight);
        if (clientValidator.finalCheckHeight(fieldHeight) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckHeight(fieldHeight)).getMessage());
        if (clientValidator.finalCheckHeight(fieldHeight) instanceof Success)
            height = ((Success<Float>) clientValidator.finalCheckHeight(fieldHeight)).getObject();
        return height;
    }

    /**
     * Asks a user the marine's category.
     *
     * @return Marine's category.
     */
    @Override
    public AstartesCategory claimCategory() {
        String fieldCategory;
        AstartesCategory category = null;
        String line = "";
        for (AstartesCategory astartesCategory : AstartesCategory.values()) {
            line += astartesCategory.name() + " ";
        }
        printMachine.println(messenger.listOfAvailableCategoriesMessage(line));
        printMachine.println(messenger.categoryInputMessage());
        fieldCategory = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldCategory);
        if (clientValidator.finalCheckCategory(fieldCategory) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckCategory(fieldCategory)).getMessage());
        if (clientValidator.finalCheckCategory(fieldCategory) instanceof Success)
            category = ((Success<AstartesCategory>) clientValidator.finalCheckCategory(fieldCategory)).getObject();
        return category;
    }

    /**
     * Asks a user the marine's chapterName.
     *
     * @return Marine's chapterName.
     */
    @Override
    public String claimChapterName() {
        String name = null;
        printMachine.println(messenger.chapterNameMessage());
        name = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(name);
        if (clientValidator.finalCheckChapterName(name) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckChapterName(name)).getMessage());
        if (clientValidator.finalCheckChapterName(name) instanceof Success)
            name = ((Success<String>) clientValidator.finalCheckChapterName(name)).getObject();
        return name;
    }

    /**
     * Asks a user the marine's chapterWorld.
     *
     * @return Marine's chapterWorld.
     */
    @Override
    public String claimChapterWorld() {
        String world = null;
        printMachine.println(messenger.chapterWorldMessage());
        world = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(world);
        if (clientValidator.finalCheckChapterWorld(world) instanceof Error)
            printMachine.println(((Error) clientValidator.finalCheckChapterWorld(world)).getMessage());
        if (clientValidator.finalCheckChapterWorld(world) instanceof Success)
            world = ((Success<String>) clientValidator.finalCheckChapterWorld(world)).getObject();
        return world;
    }
}
