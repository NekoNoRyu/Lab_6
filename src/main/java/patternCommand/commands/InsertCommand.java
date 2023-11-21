package patternCommand.commands;

import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

public class InsertCommand extends Command {
    public InsertCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        Long key;
        try {
            key = Parser.parseKey(getApplication().getCommandArg());
        } catch (InvalidDataException e) {
            key = getApplication().getInteractiveParser().parseKey();
        }
        getApplication().getDocument().insert(key);
    }
}
