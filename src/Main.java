import model.BoardGame;
import model.Game;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        BoardGame board = game.createBoardGame();
        board.play();
    }
}