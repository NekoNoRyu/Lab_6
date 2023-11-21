package patternCommand.commands;

import collectionClasses.StudyGroup;
import parser.InvalidDataException;
import parser.Parser;
import patternCommand.Application;
import patternCommand.Command;

import java.util.HashMap;

public class ReplaceIfLowerCommand extends Command {
    public ReplaceIfLowerCommand(Application application) {
        super(application);
    }

    @Override
    public void execute() {
        if (!getApplication().getCommandArg().equals("")) {
            System.out.println("replace_if_lower : argument can only be empty");
            return;
        }

        HashMap<Long, StudyGroup> hashMap = getApplication().getDocument().getCollection();
        Long key;
        try {
            key = Parser.parseKey(getApplication().getCommandArg());
        } catch (InvalidDataException e) {
            key = getApplication().getInteractiveParser().parseKey();
        }
        StudyGroup studyGroup = getApplication().getInteractiveParser().parseStudyGroup();
        if (hashMap.containsKey(key)) {
            if (hashMap.get(key).compareTo(studyGroup) > 0) {
                studyGroup.setId(hashMap.get(key).getId());
                getApplication().getDocument().getCollection().replace(key, studyGroup);
            }
        } else {
            System.out.println(key + " : element with this key is not exist");
        }
    }
}
