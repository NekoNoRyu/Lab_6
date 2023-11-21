package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;

public class ExitCommand extends Command {
    public ExitCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("exit : argument can only be empty");
            return;
        }
        getApplication().stop();
    }
}
