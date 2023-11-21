package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.HashMap;

public class RemoveKeyCommand extends Command {
    public RemoveKeyCommand(Application application) {
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
        getApplication().getDocument().getCollection().remove(key);
    }
}
