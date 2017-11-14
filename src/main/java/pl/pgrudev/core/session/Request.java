package pl.pgrudev.core.session;

import pl.pgrudev.client.Command;

import java.util.List;

public class Request {
    private Command command;
    private List<String> args;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

}
