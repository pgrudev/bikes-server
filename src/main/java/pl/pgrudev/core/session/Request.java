package pl.pgrudev.core.session;

import pl.pgrudev.client.Command;

import java.util.Arrays;

public class Request {
    private Command command;
    private String[] args;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command=" + command +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
