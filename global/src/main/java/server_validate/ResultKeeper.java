package server_validate;

public interface ResultKeeper {


    ResultKeeper ok(Object object);

    ResultKeeper error(String errorMessage);

    boolean isOK();

    String getErrorMessage();

    Object getObject();
}
