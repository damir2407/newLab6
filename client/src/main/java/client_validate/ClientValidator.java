package client_validate;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import server_validate.ResultKeeper;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;

/**
 * interface for FieldsValidation to validate fields
 */

public interface ClientValidator {

    ResultKeeper finalCheckName(String name);

    ResultKeeper finalCheckHealth(String health);

    ResultKeeper finalCheckHeartCount(String heartCount);

    ResultKeeper finalCheckHeight(String height);

    ResultKeeper finalCheckCategory(String category);

    ResultKeeper finalCheckX(String x);

    ResultKeeper finalCheckY(String y);

    ResultKeeper finalCheckChapterName(String chapterName);

    ResultKeeper finalCheckChapterWorld(String chapterWorld);



}
