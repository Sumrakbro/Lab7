package Lab5.commands;
import Lab5.com.company.CommandExecutionException;
import Lab5.com.company.Receiver;

import java.util.ArrayList;

/**
 Данная команда удаляет  объекты из коллекции по индексу
 */
public class Command_RemoveByIndex extends  Command_DeleteByProperty{
    int index;
    public final static boolean status=true;
    public Command_RemoveByIndex( int index){
        this.index=index;
    }
    public Command_RemoveByIndex() {

    }
    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }



    @Override
    public ArrayList<String> execute() throws CommandExecutionException {
        return  Receiver.remove(A_command.getSet(),this.index,this.getLogin());
    }
    @Override
    public boolean getStatus() {
        return  status;
    }
    @Override
    public String toString() {
        return "CommandRemoveByIndex <Index>:"+ this.index;
    }

    @Override
    public String showAbility() {
       return  "CommandRemoveByIndex <Index>- удаляет эллемент с таким индексом";
    }
}
