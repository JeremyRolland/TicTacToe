import controller.Controller;

public class Main {
    public static void main(String[] args) {

        Controller app = new Controller();
        app.playGame(app.initGame());
    }
}