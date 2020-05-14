package Lab5.commands;


import Lab5.com.company.Receiver;

import java.util.ArrayList;

/**
 * Команда показывающая информацию о коллекции
 */
public class Command_Info extends A_command implements Command {
    public final static boolean status = false;

    public Command_Info() {
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public ArrayList<String> execute() {
        return Receiver.info(A_command.getSet());
    }

    @Override
    public String toString() {
        return "Command Info";
    }

    @Override
    public String showAbility() {
        return "Command Info- выводит информацию о коллекции";
    }
}
