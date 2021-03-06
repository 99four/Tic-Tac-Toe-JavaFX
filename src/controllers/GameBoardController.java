package controllers;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.Optional;

public class GameBoardController {
    @FXML
    private List<ImageView> boardElements;
    @FXML
    private ImageView whoseTurnImage;
    @FXML
    private ImageView whoseNotTurnImage;
    @FXML
    private ImageView a1;
    @FXML
    private ImageView b1;
    @FXML
    private ImageView c1;

    private boolean turn = true; // true for 'O', false for 'X'
    private short counter = 0;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    protected void imageClicked(Event e) {
        ImageView clickedElement = (ImageView)e.getSource();
        if (turn) {
            clickedElement.setAccessibleText("O");
            clickedElement.setImage(new Image("assets/o.bmp"));
        }
        else {
            clickedElement.setAccessibleText("X");
            clickedElement.setImage(new Image("assets/x.bmp"));
        }
        counter++;
        clickedElement.setDisable(true);

        if (this.checkIfThereIsAWinner()) {
            this.disableFreeFields();
            char winner = turn ? 'O' : 'X';
            alert.setTitle("We have a winner!");
            alert.setHeaderText(null);
            alert.setContentText("Winner is " + winner + ". Do you want to play once again?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                this.initialize();
            } else {
                // TODO exit room
                Platform.exit();
            }
            counter = 0;
        } else if (counter == 9) {
            alert.setTitle("We have a draw!");
            alert.setHeaderText(null);
            alert.setContentText("We don't have a winner. Do you want to play once again?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                this.initialize();
            } else {
                // TODO exit room
                Platform.exit();
            }
            counter = 0;
        }
        turn = !turn;
        whoseTurnImage.setImage(turn ? new Image("assets/osmall.bmp") : new Image("assets/xsmall.bmp"));
        whoseNotTurnImage.setImage(turn ? new Image("assets/xsmall.bmp") : new Image("assets/osmall.bmp"));
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
        GridPane.setHalignment(whoseTurnImage, HPos.RIGHT);
        GridPane.setHalignment(a1, HPos.RIGHT);
        GridPane.setHalignment(b1, HPos.RIGHT);
        GridPane.setHalignment(c1, HPos.RIGHT);

        whoseTurnImage.setImage(turn ? new Image("assets/osmall.bmp") : new Image("assets/xsmall.bmp"));
        whoseNotTurnImage.setImage(turn ? new Image("assets/xsmall.bmp") : new Image("assets/osmall.bmp"));

        for (ImageView boardElement : boardElements) {
            boardElement.setDisable(false);
            boardElement.setImage(new Image("assets/nothing.bmp"));
            boardElement.setAccessibleText("N");
        }
    }

    public void disableFreeFields() {
        for (ImageView boardElement : boardElements) {
            if (boardElement.getAccessibleText().equals("N")) {
                boardElement.setDisable(true);
                boardElement.setImage(new Image("assets/disabled.bmp"));
            }
        }
    }
}