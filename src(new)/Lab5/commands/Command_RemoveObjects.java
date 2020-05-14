package Lab5.commands;
import Lab5.com.company.Receiver;
import Lab5.com.company.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class  Command_RemoveObjects extends Command_DeleteByProperty{
Ticket ticket;
String login;
    public final static boolean status=true;
   public Command_RemoveObjects( Ticket ticket) {
    this.ticket=ticket;
    }

    public Command_RemoveObjects() {
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public ArrayList<String> execute() throws SQLException {
        return Receiver.remove(A_command.getSet(),this.ticket,-1,login);
    }
    @Override
    public boolean getStatus() {
        return  status;
    }
}
