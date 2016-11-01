package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;


public class Controller {
    //true for O, false for X
    //schena
    //a1 a2 a3
    //b1 b2 b3
    //c1 c2 c3

    @FXML
    private List<ImageView> boardElements;

    private boolean turn = false;

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
        clickedElement.setDisable(true);


        if (this.checkIfThereIsAWinner()) {
            for (ImageView boardElement : boardElements) {
                boardElement.setDisable(true);
            }
            System.out.println("We have a winner");
        }
        else turn = !turn;
    }

    private boolean checkIfThereIsAWinner() {
        int[] tab = {0, 1, 2};
        return this.checkIfElementsAreEqual(tab);
    }

    public boolean checkIfElementsAreEqual(int[] indices) {
        String start = boardElements.get(indices[0]).getAccessibleText();
        for (int i = 1; i < indices.length; i++) {
            if (!start.equals(boardElements.get(indices[i]).getAccessibleText()) || start.equals("N")) {
                return false;
            }
            start = boardElements.get(indices[i]).getAccessibleText();
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