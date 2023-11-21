import patternCommand.Application;

public class Main {
    public static void main(String[] args) {
        //collection: example/csv
        if (args.length != 0) {
            Application application = new Application();
            application.setDocument(args[0]);
            application.fillMenu();
            application.run();
        } else {
            System.out.println("Enter file with collection path in args[0]");
        }
    }
}
