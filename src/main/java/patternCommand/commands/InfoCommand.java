package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;

public class InfoCommand extends Command {
    public InfoCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("info : argument can only be empty");
            return;
        }

        System.out.println(getApplication().getDocument().getCollection().getClass());
        System.out.println("Initialisation time: " + getApplication().getDocument().getInitTime());
        System.out.println("Number of elements: " + getApplication().getDocument().getCollection().size());
    }
}
