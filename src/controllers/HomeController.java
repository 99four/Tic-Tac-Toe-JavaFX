package controllers;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by damianbachorz on 01/11/2016.
 */
public class HomeController {

    public Button nextButton;
    public TextField usernameInput;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    public void handleClick(Event event) throws IOException {

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
