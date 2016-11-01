package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;

public class Controller {

    @FXML
    private List<ImageView> boardElements;

    private boolean turn = true; // true for 'O', false for 'X'
    private short counter = 0;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    protected void imageClicked(Event e) {
        ImageView clickedElement = (ImageView)e.getSource();
        if (turn) {
            clickedElement.setAccessibleText("O");
            clickedElement.setImage(new Image("assets/o.bmp"));
            counter++;
        }
        else {
            clickedElement.setAccessibleText("X");
            clickedElement.setImage(new Image("assets/x.bmp"));
            counter++;
        }
        clickedElement.setDisable(true);

        if (this.checkIfThereIsAWinner()) {
            for (ImageView boardElement : boardElements) {
                boardElement.setDisable(true);
            }
            char winner = turn ? 'O' : 'X';
            alert.setTitle("We have a winner!");
            alert.setHeaderText("Congratulations!");
            alert.setContentText("Winner is " + winner + ". Do you want to play once again?");
            alert.showAndWait();

        } else {
            if (counter == 9) {
                alert.setTitle("We have a draw!");
                alert.setHeaderText("No contest!");
                alert.setContentText("We don't have a winner. Do you want to play once again?");
                alert.showAndWait();
            }
        }
        turn = !turn;
    }

    private boolean checkIfThereIsAWinner() {
        // schema
        // 0 1 2
        // 3 4 5
        // 6 7 8
        int[][] arrayOfConditions = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // horizontal conditions
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // vertical conditions
                { 0, 4, 8 }, { 2, 4, 6 } // diagonal conditions
        };

        for (int i = 0; i < arrayOfConditions.length; i++) {
            if (this.checkIfElementsAreEqual(arrayOfConditions[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfElementsAreEqual(int[] indices) {
        String start = boardElements.get(indices[0]).getAccessibleText();
        if (start.equals("N")) {
            return false;
        }
        for (int i = 1; i < indices.length; i++) {
            if (!start.equals(boardElements.get(indices[i]).getAccessibleText())) {
                return false;
            }
        }
        return true;
    }

    public void initialize() {
        for (ImageView boardElement : boardElements) {
            boardElement.setImage(new Image("assets/nothing.bmp"));
            boardElement.setAccessibleText("N");
        }
    }
}