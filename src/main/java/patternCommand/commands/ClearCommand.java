package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;

public class ClearCommand extends Command {
    public ClearCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("clear : argument can only be empty");
            return;
        }
        getApplication().getDocument().clear();
    }
}