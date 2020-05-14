package Client;

import Lab5.com.company.Color;
import Lab5.com.company.Readder;
import Lab5.commands.*;
import Server.Command_Disconnect;
import com.company.Serializer;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {
     private static String login="";

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Client.login = login;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramChannel channel = DatagramChannel.open();
       channel.configureBlocking(false);
        ByteBuffer receiveData = ByteBuffer.allocate(1000000);
        channel.bind(new InetSocketAddress((int) (Math.random() * 6000) + 2000));
        InetAddress serverIP = readIP();
        int chance = 0;
        while (true) {
            if (SendData(channel, serverIP) < 0) {
                chance++;
                if (chance == 5) {
                    System.exit(0);
                }
                continue;
            }
            Thread.sleep(500);
           showAnswer(ReceiveData(receiveData, channel));
        }
    }


    private static int SendData(DatagramChannel channel, InetAddress serverIP) {
        try {
            if (serverIP.isReachable(1000)) {
                channel.connect(new InetSocketAddress(serverIP, 7771));
                System.out.println("Готовим запрос к отправке =) ");
                Command command = Readder.ReadCommandFromConsole();
                if (command instanceof Command_Exit) {
                    channel.send(ByteBuffer.wrap(Serializer.serialize(new Command_Disconnect())), new InetSocketAddress(serverIP, 7771));
                    System.exit(0);
                } else if (command instanceof Command_Save) {
                    System.out.println(Color.ANSI_BLUE + "У вас нет доступа к этой команде)" + Color.ANSI_RESET);
                    channel.disconnect();
                    return 1;
                }
               if(command instanceof Command_Add) ((Command_Add) command).setLogin(login);
              else  if(command instanceof Command_Clear) ((Command_Clear) command).setLogin(login);
               else  if(command instanceof Command_RemoveByIndex) ((Command_RemoveByIndex) command).setLogin(login);
               else  if(command instanceof Command_RemoveGreater)((Command_RemoveGreater) command).setLogin(login);
               else  if(command instanceof Command_Update)((Command_Update) command).setLogin(login);
               else  if(command instanceof Command_RemoveLower) ((Command_RemoveLower) command).setLogin(login);
                channel.send(ByteBuffer.wrap(Serializer.serialize(command)), new InetSocketAddress(serverIP, 7771));
                System.out.println("Отправили");
                channel.disconnect();
                return 1;
            }
            System.out.println(Color.ANSI_RED + "Сервер временно недоступен!" + Color.ANSI_RESET);
            return -1;
        } catch (IOException e) {
            System.err.println("IOException  " + e);
            return -1;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static ArrayList<String> ReceiveData(ByteBuffer receiveData, DatagramChannel channel) {
        try {
            receiveData.clear();
            System.out.println("Ожидаем ответ...");
            SocketAddress from = channel.receive(receiveData);
            ArrayList<String> answer = new ArrayList<>();
            if (from != null) {
                receiveData.flip();
                answer = (ArrayList<String>) Serializer.deserialize(receiveData.array());
            }
            return answer;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("IOException Receive " + e);
            return null;
        }
    }

    private static void showAnswer(ArrayList<String> answer) {
        System.out.println("Ответ сервера: ");
        if(answer.isEmpty()) return;
        for (String str : answer) {
            System.out.println("\t" + str);
        }
        String string=answer.get(0).trim();
        if(string.substring(0,string.lastIndexOf(" ")).equalsIgnoreCase("Successful authorization")||
                string.substring(0,string.lastIndexOf(" ")).equalsIgnoreCase("Successful registration")){
            setLogin(string.substring(string.lastIndexOf(" ")+1));
        }
    }

    private static InetAddress readIP() throws UnknownHostException {
        InetAddress server = null;
        while (true) {
            System.out.print("Введите IP адресс сервера:");
            Scanner scanner = new Scanner(System.in);
            try {
                server = InetAddress.getByName(scanner.nextLine());
            } catch (UnknownHostException e) {
                System.out.println(Color.ANSI_RED + "Неверный IP!" + Color.ANSI_RESET);
                continue;
            }
            break;
        }
        return server;
    }
}

