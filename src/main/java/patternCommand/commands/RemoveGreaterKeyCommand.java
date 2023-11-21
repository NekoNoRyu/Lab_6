package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.HashMap;
import java.util.Map;


public class RemoveGreaterKeyCommand extends Command {
    public RemoveGreaterKeyCommand(Application application) {
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
        HashMap<Long, StudyGroup> hashMap = new HashMap<>(getApplication().getDocument().getCollection());
        for (Map.Entry<Long, StudyGroup> entry : hashMap.entrySet()) {
            if (entry.getKey() > key) {
                getApplication().getDocument().getCollection().remove(entry.getKey());
            }
        }
    }
}
