package utility;

public interface Result<T> {
/*    public enum State {OK, ERROR}

    private State state;
    private String errorMessage;
    private Object object;

    public Result(State state, String errorMessage, Object object) {
        this.state = state;
        this.errorMessage = errorMessage;
        this.object = object;
    }

    public Result() {
    }


    @Override
    public ResultKeeper ok(Object object) {
        return new Result(State.OK, null, object);
    }

    @Override
    public ResultKeeper error(String errorMessage) {
        return new Result(State.ERROR, errorMessage, null);
    }

    @Override
    public boolean isOK() {
        return (this.state == State.OK);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public Object getObject() {
        return object;
    }*/
}