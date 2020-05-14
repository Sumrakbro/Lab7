package Lab5.commands;


import Lab5.com.company.Color;

import java.util.ArrayList;

/**
 Показывает историю выполненых команд
 */
public class Command_History extends A_command implements Command {
    public final static boolean status=true;
    public Command_History() {
    }

    @Override
    public ArrayList<String> execute() {
        int index = 1;
        ArrayList<String> result=new ArrayList<>();
        ArrayList<Command> history = this.getHistory();
        System.out.println(Color.ANSI_YELLOW+"История комманд:");
       result.add(Color.ANSI_YELLOW+"История комманд:");
        for (Command com : history) {
            System.out.println("\t"+index + "." + com);
            result.add("\t"+index + "." + com);
            index++;
        }
    System.out.print(Color.ANSI_RESET);
        return result;
    }
    @Override
    public String toString() {
        return "Command History";
    }

    @Override
    public String showAbility() {
        return "Command History- выводит 10 последних,выполненных комманд";
    }
    @Override
    public boolean getStatus() {
        return  status;
    }
}

