package patternCommand.commands;

import collectionClasses.FormOfEducation;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoveAnyByFOECommand extends Command {
    public RemoveAnyByFOECommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        FormOfEducation formOfEducation;
        try {
            formOfEducation = Parser.parseFormOfEducation(getApplication().getCommandArg());
        } catch (InvalidDataException e) {
            formOfEducation = getApplication().getInteractiveParser().parseFormOfEducation();
        }
        if (formOfEducation == FormOfEducation.NULL) {
            formOfEducation = null;
        }
        HashMap<Long, StudyGroup> hashMap = new HashMap<>(getApplication().getDocument().getCollection());
        for (Map.Entry<Long, StudyGroup> entry : hashMap.entrySet()) {
            if (entry.getValue().getFormOfEducation() == formOfEducation) {
                getApplication().getDocument().getCollection().remove(entry.getKey());
                break;
            }
        }
    }
}
