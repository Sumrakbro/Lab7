package Lab5.commands;

import Lab5.com.company.CommandExecutionException;
import Lab5.com.company.UncorrectedScriptException;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Общий интерфейс для всех комманд
 */
public interface Command extends Serializable {
    ArrayList<Command> history = new ArrayList<>();
    String showAbility();
    default void add(Command command) {
        if (history.size() >=10) delete();
        history.add(command);
    }

    default void delete() {
        while (history.size() >= 10)
            history.remove(0);
    }

    default ArrayList<Command> getHistory() {
        return history;
    }
    ArrayList<String> execute() throws IOException, UncorrectedScriptException, CommandExecutionException, CommandExecutionException, UncorrectedScriptException, SQLException;

    boolean getStatus();
}
