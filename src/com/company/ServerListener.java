package com.company;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Student08 on 19.05.2015.
 */
public class ServerListener extends Thread{

    private BufferedReader reader;
    String messageFromServer;

    public ServerListener (BufferedReader reader){
        this.reader = reader;
        start();


    }

    @Override
    public void run(){

        while (true){

            try {
                messageFromServer = reader.readLine();
                if (messageFromServer != null) {
                    System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
