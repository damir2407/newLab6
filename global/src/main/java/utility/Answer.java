package utility;

import java.io.Serializable;

public class Answer implements AnswerKeeper, Serializable {

    public enum State {OK, ERROR}

    public static final long serialVersionUID = 2407704212345L;

    private State state;
    private String errorMessage;
    private Object object;

    public Answer(State state, String errorMessage, Object object) {
        this.state = state;
        this.errorMessage = errorMessage;
        this.object = object;
    }

    public Answer(Object object) {
        this.object = object;
    }

    public Answer() {
    }


    @Override
    public AnswerKeeper ok(Object object) {
        return new Answer(State.OK, null, object);
    }

    @Override
    public AnswerKeeper error(String errorMessage) {
        return new Answer(State.ERROR, errorMessage, null);
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
    }
}
