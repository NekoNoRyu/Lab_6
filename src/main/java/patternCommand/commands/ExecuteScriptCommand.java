package patternCommand.commands;

import patternCommand.Application;
import patternCommand.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ExecuteScriptCommand extends Command {
    public ExecuteScriptCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (getApplication().getPaths().contains(getApplication().getCommandArg())) {
            System.out.println("Recursion canceled");
            return;
        }
        try {
            File file = new File(getApplication().getCommandArg());
            getApplication().getPaths().add(getApplication().getCommandArg());
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNext()) {
                getApplication().getCommands().add(i, scanner.nextLine());
                i++;
            }
            scanner.close();
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println(getApplication().getCommandArg() + ": file with script not found, it can only be readable");
        }
    }
}
