package patternCommand.commands;

import collectionClasses.StudyGroup;
import patternCommand.Application;
import patternCommand.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("remove_lower : argument can only be empty");
            return;
        }
        StudyGroup studyGroup = getApplication().getInteractiveParser().parseStudyGroup();
        HashMap<Long, StudyGroup> hashMap = new HashMap<>(getApplication().getDocument().getCollection());
        for (Map.Entry<Long, StudyGroup> entry : hashMap.entrySet()) {
            if (entry.getValue().compareTo(studyGroup) < 0) {
                getApplication().getDocument().getCollection().remove(entry.getKey());
            }
        }
    }
}
