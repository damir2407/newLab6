package client_validate;


import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import messenger.Messenger;
import server_validate.Result;
import server_validate.ResultKeeper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;

/**
 * class for check marine's fields
 */

public class ClientFieldsValidation implements ClientValidator {
    private Messenger messenger;


    public ClientFieldsValidation(Messenger messenger) {
        this.messenger = messenger;
    }



    /**
     * @param x to check
     * @return x coordinate
     */
    @Override
    public ResultKeeper finalCheckX(String x) {
        Float newX;
        try {
            newX = Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectXCoordinateMessage());
        }
        if (!(newX != null && newX <= 610))
            return new Result().error(messenger.incorrectXCoordinateMessage());
        return new Result().ok(newX);
    }

    /**
     * @param y to check
     * @return y coordinate
     */
    @Override
    public ResultKeeper finalCheckY(String y) {
        Double newY = null;
        try {
            newY = Double.parseDouble(y);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectYCoordinateMessage());
        }
        if (newY == null)
            return new Result().error(messenger.incorrectYCoordinateMessage());
        return new Result().ok(newY);
    }

    /**
     * @param chapterName to check
     * @return chapter name
     */
    @Override
    public ResultKeeper finalCheckChapterName(String chapterName) {
        if (chapterName == null || chapterName.isEmpty())
            return new Result().error(messenger.incorrectChapterNameMessage());
        return new Result().ok(chapterName);
    }

    /**
     * @param chapterWorld to check
     * @return chapter world
     */
    @Override
    public ResultKeeper finalCheckChapterWorld(String chapterWorld) {
        if (chapterWorld == null)
            return new Result().error(messenger.incorrectChapterWorldMessage());
        return new Result().ok(chapterWorld);
    }

    /**
     * @param chapter to check
     * @return chapter
     */

    /**
     * @param name to check
     * @return name
     */
    @Override
    public ResultKeeper finalCheckName(String name) {
        if (name == null || name.isEmpty())
            return new Result().error(messenger.incorrectNameMessage());
        return new Result().ok(name);
    }

    /**
     * @param date to check
     * @return date
     */

    /**
     * @param health to check
     * @return health
     */
    @Override
    public ResultKeeper finalCheckHealth(String health) {
        Float newHealth;
        try {
            newHealth = Float.parseFloat(health);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectHealthMessage());
        }
        if (newHealth == null || newHealth <= 0)
            return new Result().error(messenger.incorrectHealthMessage());
        return new Result().ok(newHealth);
    }



    /**
     * @param heartCount to check
     * @return heartCount
     */
    @Override
    public ResultKeeper finalCheckHeartCount(String heartCount) {
        Integer newHeartCount;
        try {
            newHeartCount = Integer.parseInt(heartCount);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectHeartCountMessage());
        }
        if (newHeartCount == null || newHeartCount <= 0 || newHeartCount > 3)
            return new Result().error(messenger.incorrectHeartCountMessage());
        return new Result().ok(newHeartCount);
    }

    /**
     * @param height to check
     * @return height
     */
    @Override
    public ResultKeeper finalCheckHeight(String height) {
        float newHeight;
        try {
            newHeight = Float.parseFloat(height);
        } catch (NumberFormatException e) {
            return new Result().error(messenger.incorrectHeightMessage());
        }
        return new Result().ok(newHeight);
    }

    /**
     * @param category to check
     * @return category
     */
    @Override
    public ResultKeeper finalCheckCategory(String category) {
        AstartesCategory newCategory;
        try {
            newCategory = AstartesCategory.valueOf(category.toUpperCase());
            if (newCategory == null)
                return new Result().error(messenger.incorrectCategoryMessage());
        } catch (IllegalArgumentException e) {
            return new Result().error(messenger.categoryDoesNotExist());
        }
        return new Result().ok(newCategory);
    }


}
