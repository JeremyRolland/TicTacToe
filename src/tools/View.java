package tools;

import game.Cell;

public class View {

    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    //Affiche le plateau de jeu
    public void display(Cell[][] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(ANSI_BLUE + board[i][j].getRepresentation());
            }
            System.out.println("|");
        }
        System.out.println();
    }

    // Demande une coordonnée
    public void askCoordinate(char coordinate) {
        System.out.print(ANSI_BLACK + "Entrer [" + coordinate + "]: ");
    }

    // Message en police noire
    public void messageNormal(String message) {
        System.out.println(ANSI_BLACK + message);
    }

    // Message en police verte
    public void messageVictory(String message) {
        System.out.println(ANSI_GREEN + message);
    }

    // Message en police rouge
    public void messageError(String message) {
        System.err.println(message);
    }
}
