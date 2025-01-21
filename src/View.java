public class View {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    //Affiche le plateau de jeu
    public void display(Cell [] board) {
        for(int i=0; i<board.length; i++) {
            if(i % 3 == 0 && i != 0) {
                System.out.println("|");
            }
            System.out.print(ANSI_BLUE + board[i].getRepresentation());
        }
        System.out.println("|");
        System.out.print("\n");
    }

    public void askCoordinate(char coordinate) {
        System.out.print(ANSI_BLACK + "Entrer [" + coordinate + "]: ");
    }

    public void messageNormal(String message) {
        System.out.println(ANSI_BLACK + message);
    }

    public void messageVictory(String message) {
        System.out.println(ANSI_GREEN + message);
    }

    public void messageError(String message) {
        System.err.println(message);
    }
}
