package game;

import tools.InteractionUtilisateur;
import tools.View;

import java.util.Scanner;

public class Game {
    private View view = new View();
    private InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);

    // Créer l'instance du jeu choisi
    public BoardGame createBoardGame() {

        switch (interactionUtilisateur.getGame()) {
            case 1:
                return new TicTacToe();
            case 2:
                return new Gomoku();
            case 3:
                return new Puissance4();
        }
        return null;
    }
}

