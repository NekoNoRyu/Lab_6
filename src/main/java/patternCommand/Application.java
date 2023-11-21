package patternCommand;

import lombok.Getter;
import parser.InteractiveParser;
import patternCommand.commands.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Application {
    private Menu menu = new Menu();
    private boolean isRun = true;
    @Getter
    private Document document;
    @Getter
    InteractiveParser interactiveParser = new InteractiveParser(this);
    @Getter
    private String commandArg = "";
    @Getter
    private LinkedList<String> commands = new LinkedList<>();
    @Getter
    private HashSet<String> paths = new HashSet<>();

    public void setDocument(String collectionPath) {
        document = new Document(this);
        try {
            document.open(collectionPath);
            document.setCollectionPath(collectionPath);
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File with collection not found, it can only be readable");
        }
    }


    public void requestMenu(String command) {
        System.out.println("Execute command " + command);
        String[] splittedCommand = Arrays.stream(command.trim().split(" ", 2))
                .map(String::trim).toArray(String[]::new);
        try {
            if (splittedCommand.length > 1) {
                commandArg = splittedCommand[1];
            } else {
                commandArg = "";
            }
            menu.clickMenuItem(splittedCommand[0]);
        } catch (NullPointerException e) {
            System.out.println(splittedCommand[0] + " " + commandArg + " : not a command");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRun) {
            try {
                if (!commands.isEmpty()) {
                    requestMenu(commands.pollFirst());
                } else if (!paths.isEmpty()) {
                    paths.clear();
                } else {
                    System.out.println("Enter command");
                    requestMenu(scanner.nextLine());
                }
            } catch (NoSuchElementException e) {
                System.out.println("Ctrl + d canceled");
                stop();
            }
        }
    }

    public void stop() {
        isRun = false;
    }


    public void fillMenu() {
        menu.add("help", MenuItem.builder().command(new HelpCommand(this)).build());
        menu.add("info", MenuItem.builder().command(new InfoCommand(this)).build());
        menu.add("show", MenuItem.builder().command(new ShowCommand(this)).build());
        menu.add("insert", MenuItem.builder().command(new InsertCommand(this)).build());
        menu.add("update", MenuItem.builder().command(new UpdateCommand(this)).build());
        menu.add("remove_key", MenuItem.builder().command(new RemoveKeyCommand(this)).build());
        menu.add("clear", MenuItem.builder().command(new ClearCommand(this)).build());
        menu.add("save", MenuItem.builder().command(new SaveCommand(this)).build());
        menu.add("execute_script", MenuItem.builder().command(new ExecuteScriptCommand(this)).build());
        menu.add("exit", MenuItem.builder().command(new ExitCommand(this)).build());
        menu.add("remove_lower", MenuItem.builder().command(new RemoveLowerCommand(this)).build());
        menu.add("replace_if_lower", MenuItem.builder().command(new ReplaceIfLowerCommand(this)).build());
        menu.add("remove_greater_key", MenuItem.builder().command(new RemoveGreaterKeyCommand(this)).build());
        menu.add("remove_any_by_form_of_education", MenuItem.builder().command(new RemoveAnyByFOECommand(this)).build());
        menu.add("count_by_form_of_education", MenuItem.builder().command(new CountByFOECommand(this)).build());
        menu.add("filter_greater_than_students_count", MenuItem.builder().command(new FilterGreaterThanSCCommand(this)).build());
    }
}
