package com.company;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Student08 on 19.05.2015.
 */
public class ServerListener extends Thread{

    private BufferedReader reader;
    String messageFromServer;
    String login;

    public ServerListener (BufferedReader reader, String login){
        this.reader = reader;
        this.login = login;
        start();


    }

    @Override
    public void run(){

        while (true){

            try {
                messageFromServer = reader.readLine();
                if (messageFromServer.startsWith(ChatClient.login + ":")) messageFromServer = null;
                if (messageFromServer != null) {
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //
            }

        }


    }
}
