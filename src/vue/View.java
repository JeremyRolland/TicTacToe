package vue;

import model.Cell;
import model.Player;

public class View {

    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_BLUE = "\u001B[34m";

    //Affiche le plateau de jeu
    public void display(String[][] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                System.out.print(ANSI_BLUE + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public String getMessageChoiceGame() {
        return "Choix du jeu:"
                + "\n" + "TicTacToe: taper \"1\""
                + "\n" + "Gomoku: taper \"2\""
                + "\n" + "Puissance 4: taper \"3\"";
    }
    // Choix type de partie
    public String getMesaggeChoicePlayer() {
        return "Choix du type de partie:"
                + "\n" + "Humain Vs Humain: taper \"1\""
                + "\n" + "Humain Vs IA: taper \"2\""
                + "\n" + "IA Vs IA: taper \"3\"";
    }
    // Rejouer
    public String getMessageRestart() {
        return "Voulez-vous recommencer une partie ?";
    }
    // Fin
    public String getMessageQuitGame() {
        return "Fin de la partie";
    }

    // Demande un choix
    public String askTwoChoice(String message, String choix1, String choix2) {
        return message + "[" + choix1 + "][" + choix2 + "]";
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

    // Annoncer le vainqueur
    public void announceWinner(String name) {
        this.messageVictory("Le joueur " + name + " remporte la partie !");
    }
}
