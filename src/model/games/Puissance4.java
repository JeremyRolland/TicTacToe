package model.games;

import controller.BoardGame;
import model.players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Puissance4 extends BoardGame {

    // Constructeur qui initialise le plateau
    public Puissance4() {
        super(6,7,4);
    }

    @Override
    public int[] getMoveFromPlayer(Player player) {
        int coordonneeY = -1;
        // Joueur humain
        if (player.getClass().getSimpleName().equals("HumanPlayer")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                try {
                    view.messageNormal("Entrer la colonne à jouer");
                    view.askCoordinate('y');
                    //On s'assure que c'est un nombre
                    coordonneeY = Integer.parseInt(br.readLine());
                    //Check case vide
                    for(int i = board.getRowSize() - 1; i > 0; i--) {
                        if(board.getCellOwner(i,coordonneeY) == null) {
                            return new int[]{i, coordonneeY};
                        }
                    }
                    view.messageError("Erreur: Veuillez entrer une colonne qui n'est pas déjà pleine");
                } catch (NumberFormatException e) {
                    view.messageError("Erreur: Veuillez entrer un nombre entier.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    view.messageError("Erreur: Veuillez rester dans la grille (min = 0 et max = " + (board.getRowSize() - 1) + ")");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (true);
            // Joueur artificiel
        } else {
            do {
                coordonneeY = (int) (Math.random() * board.getColSize());
                for(int i = board.getRowSize() - 1; i > 0; i--) {
                    if(board.getCellOwner(i,coordonneeY) == null) {
                        System.out.println("position jouée: [" + i + "," + coordonneeY + "].");
                        return new int[]{i, coordonneeY};
                    }
                }
            } while (true);
        }
    }

}
