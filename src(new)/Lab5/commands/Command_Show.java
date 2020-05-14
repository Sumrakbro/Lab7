package Lab5.commands;

import Lab5.com.company.Receiver;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 Данная команда выводит все объекты коллекции
 */
public class Command_Show extends A_command implements Command{
    public final boolean status=false;
    public Command_Show(){

   }
    @Override
    public ArrayList<String> execute() throws SQLException {
        return Receiver.print(A_command.getSet(),1);
   }
    @Override
    public String toString() {
        return "Command Show";
    }

    @Override
    public String showAbility() {
        return "Command Show- выводит эллементы коллекции на экран";
    }
    @Override
    public boolean getStatus() {
        return  status;
    }
}
