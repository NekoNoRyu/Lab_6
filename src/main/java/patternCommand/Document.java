package patternCommand;

import collectionClasses.StudyGroup;
import lombok.Getter;
import lombok.Setter;
import parser.InvalidDataException;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Getter
public class Document {
    Application application;

    public Document(Application application) {
        this.application = application;
    }

    @Setter
    private String collectionPath = "";
    @Setter
    private HashMap<Long, StudyGroup> collection;
    private LocalDateTime initTime;

    public void open(String collectionPath) throws FileNotFoundException {
        collection = new HashMap<>();
        initTime = LocalDateTime.now();
        File file = new File(collectionPath);
        Scanner scanner = new Scanner(file);
        while (true) {
            try {
                insertCSV(scanner.nextLine());
            } catch (NoSuchElementException e) {
                System.out.println("Collection is recorded");
                break;
            }
        }
    }

    public void insertCSV(String csv) {
        try {
            ArrayList<String> elements = Parser.parseCSVToArrayList(csv);

            if (elements.size() != 10) {
                throw new InvalidDataException("Number of elements for insert can only be 10");
            }

            Long key = Parser.parseKey(elements.get(0));
            if (collection.containsKey(key)) {
                throw new InvalidDataException(key + " : element with this key already exists");
            }

            StudyGroup studyGroup = Parser.parseArrayListToStudyGroup(new ArrayList<>(elements.subList(1, 10)));
            long id = collection.values().stream().mapToLong(StudyGroup::getId).max().orElse(0) + 1;
            studyGroup.setId(id);

            collection.put(key, studyGroup);
        } catch (InvalidDataException e) {
            System.out.println(csv + " : problem with data");
            System.out.println(e.getMessage());
        }
    }

    public void insert(Long key) {
        while (true) {
            if (collection.containsKey(key)) {
                System.out.println("Element with this key already exists");
                key = getApplication().getInteractiveParser().parseKey();
            } else {
                break;
            }
        }

        StudyGroup studyGroup = getApplication().getInteractiveParser().parseStudyGroup();
        long id = collection.values().stream().mapToLong(StudyGroup::getId).max().orElse(0) + 1;
        studyGroup.setId(id);

        collection.put(key, studyGroup);
    }

    public void clear() {
        collection = new HashMap<>();
    }
}
