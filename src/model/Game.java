package model;

import vue.InteractionUtilisateur;
import vue.View;

public class Game {
    private final View view = new View();
    private final InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);

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

