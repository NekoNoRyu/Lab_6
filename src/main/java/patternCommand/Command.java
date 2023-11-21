package patternCommand;

import lombok.Getter;

public abstract class Command {
    @Getter
    private Application application;

    public Command(Application application) {
        this.application = application;
    }

    abstract public void execute();
}
