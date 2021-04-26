package ask_works;

import client_validate.ClientValidator;
import data.AstartesCategory;
import messenger.Messenger;
import print_works.PrintKeeper;

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
        if (!(clientValidator.finalCheckName(name).isOK()))
            printMachine.println(clientValidator.finalCheckName(name).getErrorMessage());
        else name = (String) clientValidator.finalCheckName(name).getObject();
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
        if (!(clientValidator.finalCheckX(fieldX).isOK()))
            printMachine.println(clientValidator.finalCheckX(fieldX).getErrorMessage());
        else x = (Float) clientValidator.finalCheckX(fieldX).getObject();
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
        if (!(clientValidator.finalCheckY(fieldY).isOK()))
            printMachine.println(clientValidator.finalCheckY(fieldY).getErrorMessage());
        else y = (Double) clientValidator.finalCheckY(fieldY).getObject();
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
        if (!(clientValidator.finalCheckHealth(fieldHealth).isOK()))
            printMachine.println(clientValidator.finalCheckHealth(fieldHealth).getErrorMessage());
        else health = (Float) clientValidator.finalCheckHealth(fieldHealth).getObject();
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
        if (!(clientValidator.finalCheckHeartCount(fieldHeartCount).isOK()))
            printMachine.println(clientValidator.finalCheckHeartCount(fieldHeartCount).getErrorMessage());
        else heartCount = (Integer) clientValidator.finalCheckHeartCount(fieldHeartCount).getObject();
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
        if (!(clientValidator.finalCheckHeight(fieldHeight).isOK()))
            printMachine.println(clientValidator.finalCheckHeight(fieldHeight).getErrorMessage());
        else height = (float) clientValidator.finalCheckHeight(fieldHeight).getObject();
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
        if (!(clientValidator.finalCheckCategory(fieldCategory).isOK()))
            printMachine.println(clientValidator.finalCheckCategory(fieldCategory).getErrorMessage());
        else category = (AstartesCategory) clientValidator.finalCheckCategory(fieldCategory).getObject();
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
        if (!(clientValidator.finalCheckChapterName(name).isOK()))
            printMachine.println(clientValidator.finalCheckChapterName(name).getErrorMessage());
        else name = (String) clientValidator.finalCheckChapterName(name).getObject();
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
        if (!(clientValidator.finalCheckChapterWorld(world).isOK()))
            printMachine.println(clientValidator.finalCheckChapterWorld(world).getErrorMessage());
        else world = (String) clientValidator.finalCheckChapterWorld(world).getObject();
        return world;
    }
}
