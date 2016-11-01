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
        ImageView boardElement = (ImageView)e.getSource();
        if (turn) {
            boardElement.setAccessibleText("O");
            boardElement.setImage(new Image("assets/o.bmp"));
        }
        else {
            boardElement.setAccessibleText("X");
            boardElement.setImage(new Image("assets/x.bmp"));
        }
        boardElement.setDisable(true);

        if (this.checkIfThereIsAWinner(e)) {
            for (ImageView imageElement : boardElements) {
                imageElement.setDisable(true);
            }
            System.out.println("We have a winner");
        }
        else turn = !turn;
    }

    private boolean checkIfThereIsAWinner(Event e) {
        int counter = 1;
        //for (ImageView boardElement : boardElements) {
//            System.out.println(boardElement.getAccessibleText());
//            System.out.println(counter++);
        //}
        if (boardElements.get(0).getAccessibleText().equals(boardElements.get(1).getAccessibleText())) {
            return true;
        } else {
            return false;
        }
    }

    public void initialize() {
        for (ImageView boardElement : boardElements) {
            boardElement.setImage(new Image("assets/nothing.bmp"));
            boardElement.setAccessibleText("N");
        }
    }
}