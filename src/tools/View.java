package tools;

import game.Cell;

public class View {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

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
