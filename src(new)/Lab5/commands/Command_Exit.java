package Lab5.commands;

import Lab5.com.company.Receiver;


import java.util.ArrayList;
import java.util.Collections;

/**
 Данная команда осуществялет выход из программы
 */
public class Command_Exit extends A_command implements Command {
    public final static boolean status=false;
    public Command_Exit() {

    }
    @Override
    public boolean getStatus() {
        return  status;
    }
    @Override
    public ArrayList<String> execute()
    {
        Receiver.exit();
        return new ArrayList<String>(Collections.singleton("Выход выполнен!"));
    }

    @Override
    public String toString() {
        return "Command Exit";
    }

    @Override
    public String showAbility() {
        return "Command Exit- выходит из программы без сохранения изменений";
    }
}
