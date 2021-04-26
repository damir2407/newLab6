package server_validate;

public interface AnswerKeeper {

    AnswerKeeper ok(Object object);


    AnswerKeeper error(String errorMessage);

    boolean isOK();

    String getErrorMessage();

    Object getObject();
}