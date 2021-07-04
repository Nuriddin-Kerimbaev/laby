package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public TextArea textArea;
    public TextField textField;
    public Button butt;
    private ArrayList<String> messages = new ArrayList<>();
    private Server server = null;
    private Client client = null;
    InetAddress host;
    int port;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            host = InetAddress.getByName("localhost");
            port = 5000;
            server = new Server();
            //server.run();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void start(ActionEvent actionEvent) throws IOException{
        try {
            server.run();
            //System.out.println(id);
            client = new Client(host, port);
            //Запускаем логику клиента
            client.start();
            client.BW.write(textField.getText().toCharArray());
            textArea.appendText(String.valueOf(client.BF.read()));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        /*Socket socket = null;
        try{
            client = new Client();
            client.setInt(Integer.parseInt(textField.getText()));
            server = new Server();
            server.start();
            client.start();
            synchronized (messages) {
                messages.add(client.getNameClient()+" > "+server.getOut().toString());
            }
            textArea.appendText(messages.toString());
        } catch (IOException e){
            System.out.println("IOException!: " + e);
        }*/
    }
}
