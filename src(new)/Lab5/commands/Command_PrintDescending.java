package Lab5.commands;



import Lab5.com.company.Receiver;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 Данная команда выводит коллекцию в обратном порядке
 */
public class Command_PrintDescending extends Command_Print{
int i=-1;
    public final static boolean status=false;
    public Command_PrintDescending() {
    }
    @Override
    public boolean getStatus() {
        return  status;
    }
    @Override
    public ArrayList<String> execute() throws SQLException {
        return Receiver.print(A_command.getSet(),this.i);
        }

    @Override
    public String toString() {
        return "Command PrintDescending";
    }

    @Override
    public String showAbility() {

            return "Command PrintDescending-выводит коллекцию в обратном порядке";
    }
}
