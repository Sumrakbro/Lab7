package Lab5.commands;

import Lab5.com.company.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public abstract class A_command implements Command {
    private static TreeSet<Ticket> set;

    public static void setSet(TreeSet<Ticket> set) {
        A_command.set = set;
    }

    public static TreeSet<Ticket> getSet() {
        return set;
    }

    private static ArrayList<A_command> commands = new ArrayList<>();

    public static ArrayList<A_command> getCommands() {
        return commands;
    }
    @Override
    public abstract boolean getStatus();
    public static void addNewCommand(A_command... commandsList) {
        commands.addAll(Arrays.asList(commandsList));
    }

}
