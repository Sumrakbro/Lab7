package Server;


import Lab5.com.company.Coordinates;
import Lab5.com.company.Person;
import Lab5.com.company.Ticket;
import Lab5.com.company.TicketType;
import Lab5.commands.A_command;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Database {
    String url;
    String user;
    String password;
    String driverPath;
    static Connection connection;

    public Database(String url, String user, String password, String driverPath) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driverPath = driverPath;
    }

    public void downloadDriver() {
        try {
            Class.forName(this.driverPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connectionToDatabase() {
        this.downloadDriver();
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed : " + e.getMessage());
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        this.createType();
        createSeq();
        this.createTable();
    }

    public ArrayList<String> AuthorizationUser(User user) throws SQLException {
        if (checkLogin(user)) {
            if (checkPassword(user)) {
                return new ArrayList<>(Collections.singleton("Successful authorization " + user.getLogin()));
            } else return new ArrayList<>(Collections.singleton("Wrong password"));
        }
        return new ArrayList<>(Collections.singleton("There is no user with this nickname"));
    }

    private boolean checkLogin(User user) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
        while (resultSet.next()) {
            String login = resultSet.getString(1);
            System.out.println("1:" + login + " 2:" + user.getLogin());
            if (user.getLogin().equals(login)) return true;
        }
        return false;
    }

    private boolean checkPassword(User user) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
        while (resultSet.next()) {
            String login = resultSet.getString(1);
            if (user.getLogin().equals(login)) {
                return Hashcode.crypt(user, resultSet.getString(3)).equals(resultSet.getString(2));
            }
        }
        return false;
    }

    public ArrayList<String> registerUser(User user) throws SQLException {
        if (!checkLogin(user)) {
            String hashedPassword = Hashcode.crypt(user);
            System.out.println("Ник:" + user.getLogin() + " Пароль:" + user.getPassword());
            String command = "INSERT INTO Users VALUES ('" + user.getLogin() + "','" + hashedPassword
                    + "','" + user.getSalt() + "');";
            executor(command);
            return new ArrayList<>(Collections.singleton("Successful registration " + user.getLogin()));
        }
        return new ArrayList<>(Collections.singleton("Пользователь с данным логином существует."));
    }


    public static void executor(String command) {
        Statement stmt;
        try {
            stmt = connection.createStatement();
            if (stmt != null) {
                stmt.executeUpdate(command);
            }
        } catch (SQLException ignored) {
        }
    }


    public void createType() {
        executor("CREATE TYPE person  AS(passportID text,y bigint);");
        executor(" CREATE TYPE coordinates AS( x integer,y integer);");
        executor("CREATE TYPE  type AS ENUM" +
                " ('cheap','budgetary','usual','vip');");
    }

    public void createTable() {
        executor("CREATE TABLE Tickets" + "(ID serial primary key,Title text," +
                "coordinates coordinates,creationdate timestamp DEFAULT NOW(),price double precision," +
                "type type, person person,owner text);");
        executor("CREATE TABLE Users" + "(login text," + " password text," + "salt text);");
    }

    public void createSeq() {
        executor(" CREATE SEQUENCE iterator " + "start 1" + " increment 1 " + " NO MAXVALUE " + " CACHE 1;");
    }

    public static void readData() throws SQLException {
        TreeSet<Ticket> set = new TreeSet<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Tickets");
        Ticket newTicket;
        while (resultSet.next()) {
            long id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String coordinates_str = resultSet.getString(3);
            String substring = coordinates_str.substring(coordinates_str.indexOf("(") + 1,
                    coordinates_str.indexOf(","));
            String substring1 = coordinates_str.substring(coordinates_str.indexOf(",") + 1,
                    coordinates_str.indexOf(")"));
            Coordinates coordinates = new Coordinates(Double.parseDouble(substring), Float.parseFloat(substring1));
            Timestamp timestamp = resultSet.getTimestamp(4);
            double price = resultSet.getDouble(5);
            String type_str = resultSet.getString(6);
            TicketType type = null;
            if (type_str != null && !type_str.isEmpty()) type = TicketType.valueOf(type_str.toUpperCase());
            String person_str = resultSet.getString(7);
            String owner = resultSet.getString(8);
            Person person = null;
            if (person_str != null && !person_str.isEmpty()) {
                substring = person_str.substring(person_str.indexOf("(") + 1,
                        person_str.indexOf(","));
                substring1 = person_str.substring(person_str.indexOf(",") + 1,
                        person_str.indexOf(")"));
                person = new Person(Long.parseLong(substring1), substring);
            }
            if (person != null && type != null)
                newTicket = new Ticket(title, coordinates, price, type, person, timestamp);
            else if (person != null) newTicket = new Ticket(title, coordinates, price, person, timestamp);
            else newTicket = new Ticket(title, coordinates, price, type, timestamp);
            newTicket.setid(id);
            newTicket.setOwner(owner);
            set.add(newTicket);
        }
        for (Ticket t: set) {
            System.out.println("Кол:"+t);
        }
        A_command.setSet(set);
    }
}
