package pl.pgrudev.core.session;

import pl.pgrudev.client.Command;

import java.util.Arrays;

public class Request {
    private Command command;
    private Object[] args;

    public Request(Command command, Object[] args) {
        this.command = command;
        this.args = args;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Object[] getArgs() {
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
