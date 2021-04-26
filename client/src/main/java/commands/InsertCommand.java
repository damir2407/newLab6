package commands;

import data.SpaceMarine;
import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintKeeper;
import request_structure.Request;
import request_structure.RequestKeeper;

public class InsertCommand implements AskCommand {
    private Messenger messenger;
    private PrintKeeper printKeeper;
    private Repeater repeater;

    public InsertCommand(Messenger messenger, PrintKeeper printKeeper, Repeater repeater) {
        this.messenger = messenger;
        this.printKeeper = printKeeper;
        this.repeater = repeater;
    }

    @Override
    public RequestKeeper prepare(String argument) {
        if (argument.isEmpty()) {
            printKeeper.println(messenger.argumentErrorMessage("insert", true));
            return null;
        }

        Integer key;
        key = Integer.parseInt(argument);
        SpaceMarine spaceMarine = repeater.repeatFields(null);

        return new Request("insert", key, spaceMarine);
    }
}
