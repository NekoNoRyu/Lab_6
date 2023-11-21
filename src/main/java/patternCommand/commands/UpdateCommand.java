package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.Map;

public class UpdateCommand extends Command {
    public UpdateCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        long id;
        try {
            id = Parser.parseId(getApplication().getCommandArg());
        } catch (InvalidDataException e) {
            id = getApplication().getInteractiveParser().parseId();
        }
        boolean containsId = false;
        Long key = -9223372036854775808L;
        while (true) {
            for (Map.Entry<Long, StudyGroup> entry :
                    getApplication().getDocument().getCollection().entrySet()) {
                if (entry.getValue().getId() == id) {
                    containsId = true;
                    key = entry.getKey();
                }
            }
            if (!containsId) {
                System.out.println("Element with this id is not exists");
                id = getApplication().getInteractiveParser().parseId();
            } else {
                break;
            }
        }
        getApplication().getDocument().getCollection().remove(key);
        getApplication().getDocument().insert(key);
        getApplication().getDocument().getCollection().get(key).setId(id);
    }
}
