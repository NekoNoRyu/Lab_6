package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;
import patternCommand.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SaveCommand extends Command {
    public SaveCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("save : argument can only be empty");
            return;
        }

        File file = new File(getApplication().getDocument().getCollectionPath());
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for (Map.Entry<Long, StudyGroup> entry : getApplication().getDocument().getCollection().entrySet()) {
                fileWriter.write(Parser.parseEntryToCSV(entry) + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("No write access");
        }
    }
}
