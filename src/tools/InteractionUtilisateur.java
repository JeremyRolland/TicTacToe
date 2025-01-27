package tools;

import game.TicTacToe;
import player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InteractionUtilisateur {

    private final View view;

    public InteractionUtilisateur(View view){
        this.view = view;
    }

    //Demander le type de partie
    public int getGameType() {
        do {
            try {
                view.messageNormal(
                        "Choix du type de partie:"
                                + "\n" + "Humain Vs Humain: taper \"1\""
                                + "\n" + "Humain Vs IA: taper \"2\""
                                + "\n" + "IA Vs IA: taper \"3\"");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int choixUtilisateur = Integer.parseInt(br.readLine());
                if(choixUtilisateur < 1 && choixUtilisateur > 3) {
                    view.messageError("Erreur: Veuillez entrer un nombre compris entre 1 et 3");
                } else {
                    return choixUtilisateur;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                view.messageError("Erreur: Veuillez entrer un nombre entier.");
            }
        } while(true);
    }

    //Recommencer une partie
    public void restartGame(TicTacToe game) {

        do{
            try{
                view.messageNormal("Voulez-vous recommencer une partie ? [oui/non]");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String choixUser = br.readLine();
                if(choixUser.equals("oui")) {
                    game.initBoard();
                    game.play();
                } else if (choixUser.equals("non")) {
                    this.quitGame();
                } else {
                    view.messageError("Erreur: Entrez seulement les mots demandés");
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        } while(true);

    }

    //Quitter le jeu
    public void quitGame() {
        view.messageNormal("Fin de la partie");
        System.exit(0);
    }

    //Annoncer le vainqueur
    public void announceWinner(Player player) {
        view.messageVictory("Le joueur " + player.getName() + " remporte la partie !");
    }

}
