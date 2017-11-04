package pl.pgrudev.core.session;

import pl.pgrudev.client.Command;

public class Request {
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
