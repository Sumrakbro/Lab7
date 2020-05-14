package Lab5.com.company;;


import Lab5.commands.Command;


import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;


public class Invoker {
    /**
    Инвокер. Начинает выполнение любой комманды
     */

    private Command command;

    public Invoker() {
    }

    public void setCommand(Command com) {
        this.command = com;
        this.command.add(this.command);
    }

    public ArrayList<String> executeCommand() throws UncorrectedScriptException, CommandExecutionException,
            IOException, SQLException {
        return command.execute();
    }
    public Command getCommand() {
        return command;
    }
}

