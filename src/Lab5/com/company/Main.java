package Lab5.com.company;;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.TreeSet;

/**
 Данная программа- пример управления коллекцией.
 Выполнена по паттерну: Команда
 Тип коллекции:TreeSet
 @author Balakin Artem
 @since Java8
 */
public class Main {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException, SQLException {
        Readder readder = new Readder("C:\\Input\\input.csv");
        readder.Readfile();
        Invoker invoker = new Invoker();
        while (true) {
            try {
                invoker.setCommand(Readder.ReadCommandFromConsole());
            } catch (IllegalArgumentException e) {
                System.out.println(Color.ANSI_RED+"Вы ввели неправильные аргументы команды.Повторите ввод."+Color.ANSI_RESET);
                continue;
            }
            try {
                invoker.executeCommand();
            } catch (UncorrectedScriptException | CommandExecutionException e) {
                System.out.println(Color.ANSI_RED+"Ошибка в выполнении скрипта!"+Color.ANSI_RESET);
            }
        }
    }
}

