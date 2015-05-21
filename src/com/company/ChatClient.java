package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Student08 on 19.05.2015.
 */
public class ChatClient {

    private static final int PORT = 7000;
    private static final String IP = "localhost";
    private Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    Scanner scanner;
    String messageToServer;
    static String login;
    private ServerListener listener;

    public void connect(){

        try {
            socket = new Socket(IP, PORT);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener = new ServerListener(reader, login);
        consoleReader();

    }

    private void consoleReader(){
        //считываем сообщения с консоли и отправляем на сервер
        scanner = new Scanner(System.in);

        login = scanner.nextLine();
        writer.println(login);
        writer.flush();



        try {
            while (true){

                messageToServer = scanner.nextLine();
                writer.println(messageToServer);
                writer.flush();
                if (messageToServer.equals("exit")){
                    scanner.close();
                    close();
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException{

        writer.close();
        listener.stop();
        reader.close();
        socket.close();

    }
}
