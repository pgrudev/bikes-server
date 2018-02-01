package pl.pgrudev.client;

public class Command {
    public Command(String cmd) {
        this.cmd = cmd;
    }

    private String cmd;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
