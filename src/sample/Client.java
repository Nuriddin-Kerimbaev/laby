package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread{
    private String name;

    Socket socketClient = null;
    InetAddress host;
    int port;
    BufferedReader BF = null;
    BufferedWriter BW = null;
    String str,string;

    public Client(InetAddress host, int port) throws IOException{
        this.host = host;
        this.port = port;
    }

    public void run() {
        try {// получение строки клиентом
            socketClient = new Socket(this.host,this.port);
            BF = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BW = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            this.doWork();

        } catch (IOException e) {

            System.out.println("клиента ошибка : " + e);
        }
    }

    public String getHostClientName(){
        return host.getHostName();
    }

    public void doWork() throws IOException{
        str = String.valueOf(BF.read());
        string = getHostClientName()+" > "+str;
        BW.write(string.toCharArray());
    }
}
