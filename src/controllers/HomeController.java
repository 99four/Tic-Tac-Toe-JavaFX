package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by damianbachorz on 01/11/2016.
 */
public class HomeController {

    @FXML
    private Button buttonNext;

    public void handleClick(Event event) throws IOException {
        System.out.println("jestem tu");
        Stage stage;
        Parent root;
        if (event.getSource() == buttonNext) {
            System.out.println("tu tez");
            //get reference to the button's stage
            stage = (Stage) buttonNext.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/views/GameBoardView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}