package patternCommand;

import java.util.HashMap;

public class Menu {
    private HashMap<String, MenuItem> hashMap = new HashMap<>();

    public void add(String commandName, MenuItem menuItem) {
        hashMap.put(commandName, menuItem);
    }

    public void clickMenuItem(String commandName) {
        hashMap.get(commandName).clicked();
    }
}