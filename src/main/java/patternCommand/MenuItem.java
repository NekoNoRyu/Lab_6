package patternCommand;

import lombok.Builder;

@Builder
public class MenuItem {
    Command command;

    public void clicked() {
        command.execute();
    }
}
