package vue;

public class View {

    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_BLUE = "\u001B[34m";

    //Affiche le plateau de jeu
    public void display(String[][] board) {
        for (String[] strings : board) {
            for (String string : strings) {
                System.out.print(ANSI_BLUE + "| " + string + " ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    // Choix du jeu
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

    // Fin
    public void messageQuitGame() {System.out.println("Fin de la partie");}

    // Demande si rejoue
    public String messageRestart() {return "Voulez-vous recommencer une partie ? [oui][non]";}

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

    // Annonce vainqueur
    public void announceWinner(String name) {
        this.messageVictory("Le joueur " + name + " remporte la partie !");
    }

}
