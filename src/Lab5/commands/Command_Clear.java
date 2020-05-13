package Lab5.commands;



import Lab5.com.company.Receiver;

import java.sql.SQLException;
import java.util.ArrayList;

/**
Данная команда очищает коллекцию
 */
public class Command_Clear extends A_command implements Command {
    String login;
    public final static boolean status=true;
    public Command_Clear() {
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Override
    public boolean getStatus() {
        return  status;
    }
    @Override
    public ArrayList<String> execute() throws SQLException {
       return Receiver.clear(A_command.getSet(),this.login);
    }
    public String getLogin() {
        return login;
    }
    @Override
    public String toString() {
        return "Command Clear";
    }

    @Override
    public String showAbility() {
        return "Command Clear- очищает коллекцию";
    }
}

