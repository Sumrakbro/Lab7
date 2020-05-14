package Lab5.commands;

import Lab5.com.company.CommandExecutionException;
import Lab5.com.company.Receiver;
import Lab5.com.company.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;

public class Command_Update extends A_command implements Command {
    public final static boolean status=true;
  String login;
    int index;
    Ticket ticket;
    /**
     Данная команда заменяет объект коллекции по id
     */
    public Command_Update(int index, Ticket ticket) {
        this.index = index;
        this.ticket = ticket;
    }

    public Command_Update() {

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public ArrayList<String> execute() throws CommandExecutionException, SQLException {
       return Receiver.replace(this.index, this.ticket, A_command.getSet(),login);
    }
    @Override
    public String toString() {
        return "Command Update <Index,Element>: "+this.index+" "+this.ticket;
    }

    @Override
    public boolean getStatus() {
        return  status;
    }

    @Override
    public String showAbility() {
        return "Command Update <Index,Element>- обновляет эллемент id,которого равно заданному";
    }
}
