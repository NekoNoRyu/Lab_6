package patternCommand.commands;

import collectionClasses.FormOfEducation;
import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.Map;

public class CountByFOECommand extends Command {
    public CountByFOECommand(Application application) {
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
        int i = 0;
        for (Map.Entry<Long, StudyGroup> entry :
                getApplication().getDocument().getCollection().entrySet()) {
            if (entry.getValue().getFormOfEducation() == formOfEducation) {
                i++;
            }
        }
        System.out.println(i);
    }
}
