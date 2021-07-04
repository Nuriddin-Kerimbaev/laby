package sample;

import java.io.*;
import java.net.Socket;

public class ClientSession extends Thread {
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final Context context;

    public ClientSession(final Socket socket, final Context context) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.context = context;
    }

    public void run() {
        String msg;
        try {
            msg = reader.readLine();

            this.doWork();

            //выход
            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
    }
}