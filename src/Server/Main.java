package Server;

import Lab5.com.company.CommandExecutionException;

import java.io.IOException;
import java.sql.SQLException;


class Main {
    public static void main(String[] args) throws IOException, InterruptedException, CommandExecutionException, SQLException {
      Database database=new Database("jdbc:postgresql://pg:5432/studs", "s285490",
                "xnd018","org.postgresql.Driver");
        database.connectionToDatabase();
        database.readData();
        Server server = new Server(database);
        server.start();
    }
}

