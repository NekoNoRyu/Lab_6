package patternCommand.commands;

import collectionClasses.StudyGroup;
import patternCommand.Application;
import patternCommand.Command;

import java.util.Map;

public class ShowCommand extends Command {
    public ShowCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("show : argument can only be empty");
            return;
        }
        for (Map.Entry<Long, StudyGroup> entry : getApplication().getDocument().getCollection().entrySet()) {
            System.out.println(entry);
        }
    }
}
