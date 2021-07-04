package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{

    private BufferedReader in = null;
    private Socket clientSocket = null;
    private PrintWriter out = null;

    private final int port;
    private Context context;

    public Server() {
        this.port = 5000;
        this.context = new Context();
    }

    public void run() {
        try {
            ServerSocket ss = new ServerSocket(this.port);

            //Цикл ожидания подключений
            while(!this.context.stopFlag) {
                System.out.println("Waiting connection on port:" + this.port);
                //Момент ухода в ожидание подключения
                clientSocket = ss.accept();
                System.out.println("New client connected to server");

                //Создается клиентская сессия
                ClientSession clientSession = new ClientSession(clientSocket, this.context);
                this.context.getSessionsManger().addSession(clientSession);
                //Запуск логики работы с клиентом
                clientSession.start();
            }

            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*finally {
            try {

                clientSocket.close();

            } catch (IOException e) {

                System.err.println("Socket not closed");

            }
        }*/
    }

}
