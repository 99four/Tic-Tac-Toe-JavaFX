package controllers;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

/**
 * Created by damianbachorz on 01/11/2016.
 */
public class HomeController {

    public Button nextButton;
    public TextField usernameInput;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    public void handleClick(Event event) throws IOException {
        String serverName = "localhost";
        int port = 6669;

        System.out.println("Connecting to " + serverName + " on port " + port);
        Socket client = new Socket(serverName, port);

        //Send the message to the server
        OutputStream os = client.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String number = "jkjkjjkkj";

        String sendMessage = number;
        bw.write(sendMessage);
        bw.flush();
        System.out.println("Message sent to the server : "+sendMessage);

        //Get the return message from the server
        InputStream is = client.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String message = br.readLine();
        System.out.println("Message received from the server : " +message);
        client.close();

        if (usernameInput.getText().length() == 0) {
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("You can't have an empty username!");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Stage stage;
            Parent root;
            stage = (Stage) nextButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/views/RoomsView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
