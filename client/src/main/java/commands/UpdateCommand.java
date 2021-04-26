package commands;

import data.SpaceMarine;
import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintKeeper;
import request_structure.Request;
import request_structure.RequestKeeper;

public class UpdateCommand implements AskCommand {
    private Messenger messenger;
    private PrintKeeper printKeeper;
    private Repeater repeater;

    public UpdateCommand(Messenger messenger, PrintKeeper printKeeper, Repeater repeater) {
        this.messenger = messenger;
        this.printKeeper = printKeeper;
        this.repeater = repeater;
    }

    @Override
    public RequestKeeper prepare(String argument) {
        if (argument.isEmpty()) {
            printKeeper.println(messenger.argumentErrorMessage("update", true));
            return null;
        }


        Integer id;
        id = Integer.parseInt(argument);
        SpaceMarine spaceMarine = repeater.repeatFields(id);

        return new Request("update", id, spaceMarine);
    }
}
