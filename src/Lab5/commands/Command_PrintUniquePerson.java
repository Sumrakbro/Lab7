package Lab5.commands;

import Lab5.com.company.Person;
import Lab5.com.company.Receiver;


import java.util.ArrayList;

/**
 Данная команда выводит все объекты коллекции, которые имеют уникальное поле Person
 */
public class Command_PrintUniquePerson extends Command_Print {
    Person person;
    public final static boolean status=true;
    public Command_PrintUniquePerson(Person person) {
        this.person=person;
    }

    public Command_PrintUniquePerson() {

    }
    @Override
    public boolean getStatus() {
        return  status;
    }
    @Override
    public ArrayList<String> execute() {
       return Receiver.print(A_command.getSet(),this.person);
    }

    @Override
    public String toString() {
        return "Command PrintUniquePerson <Person>: "+this.person;
    }

    @Override
    public String showAbility() {
        return  "Command PrintUniquePerson <Person>- выводит уникальные значения Person";
    }
}
