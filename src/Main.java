import game.*;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        BoardGame board = game.createBoardGame();
        board.play();
    }
}