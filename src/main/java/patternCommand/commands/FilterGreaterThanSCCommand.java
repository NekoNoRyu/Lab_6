package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.Map;

public class FilterGreaterThanSCCommand extends Command {
    public FilterGreaterThanSCCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        long studentsCount;
        try {
            studentsCount = Parser.parseStudentsCount(getApplication().getCommandArg());
        } catch (InvalidDataException e) {
            studentsCount = getApplication().getInteractiveParser().parseStudentsCount();
        }
        for (Map.Entry<Long, StudyGroup> entry :
                getApplication().getDocument().getCollection().entrySet()) {
            if (entry.getValue().getStudentsCount() > studentsCount) {
                System.out.println(entry);
            }
        }
    }
}
