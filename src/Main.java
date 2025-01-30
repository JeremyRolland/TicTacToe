import controller.Controller;
import model.BoardGame;

public class Main {
    public static void main(String[] args) throws Exception {

        Controller app = new Controller();
        app.playGame(app.initGame());
    }
}