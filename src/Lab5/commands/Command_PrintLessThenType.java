package Lab5.commands;

import Lab5.com.company.Receiver;
import Lab5.com.company.TicketType;

import java.util.ArrayList;

/**
 Данная команда выводит все объекты коллекции, тип которых меньше заданного
 */
public class Command_PrintLessThenType extends Command_Print {
    TicketType type;
    public final static boolean status=false;
    public Command_PrintLessThenType(TicketType type) {
    this.type=type;
    }

    public Command_PrintLessThenType() {

    }
    @Override
    public boolean getStatus() {
        return  status;
    }
    @Override
    public ArrayList<String> execute() {
        return Receiver.print(A_command.getSet(),this.type);
    }
    @Override
    public String toString() {
        return "Command PrintLessThenType <Type>: "+ this.type;
    }

    @Override
    public String showAbility() {
        return "Command PrintLessThenType <Type>- выводит элементы, значения Type которых меньше";
    }
}
