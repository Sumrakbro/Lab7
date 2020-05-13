package Server;

import Lab5.com.company.CommandExecutionException;
import Lab5.com.company.UncorrectedScriptException;
import Lab5.commands.A_command;

import java.io.IOException;
import java.util.ArrayList;

public class Command_Disconnect extends A_command {
    public final  boolean status=true;
    public Command_Disconnect(){ }

    @Override
    public String showAbility() {
        return "Command Disconnect<>- выполняет выход из учетной записи в БД";
    }

    @Override
    public ArrayList<String> execute() throws IOException, UncorrectedScriptException, CommandExecutionException, CommandExecutionException, UncorrectedScriptException {
        return Database_Receiver.Disconnect();
    }
    @Override
    public boolean getStatus() {
        return status;
    }
}
