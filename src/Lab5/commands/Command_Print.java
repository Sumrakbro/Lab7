package Lab5.commands;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Command_Print extends A_command implements Command{
  public Command_Print(){

    }
    @Override
    public ArrayList<String> execute() throws SQLException {

      return null;
    }
}
