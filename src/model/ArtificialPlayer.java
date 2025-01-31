package model;

import exceptions.InvalidArtificialDifficulty;

public class ArtificialPlayer extends Player {

    private enum Level {EASY,MEDIUM,HARD };
    private final Level IADifficulty;

    // Constructeur
    public ArtificialPlayer(String symbol) {
        super(symbol);
        this.IADifficulty = Level.EASY;
    }

    //Récupère le choix du joueur pour case vide
    public int[] getMoveFromPlayer(Cell[][] board) throws InvalidArtificialDifficulty {
        int[] position = {-1, -1};
            switch(this.IADifficulty) {
                case EASY:
                    return this.generateRandomPosition(board);
                case MEDIUM:
                    return position;
                case HARD:
                    return position;
                default:
                    throw new InvalidArtificialDifficulty();
            }
    }

    public int[] generateRandomPosition(Cell[][] board) {
        int position = -1, coordonneeX = -1, coordonneeY = -1;
        do {
            coordonneeX = (int) (Math.random() * board.length);
            coordonneeY = (int) (Math.random() * board.length);
            //Transforme coordonnée 2D en 1D
            if(board[coordonneeX][coordonneeY].getOwner() == null) {
                System.out.println("position jouée: [" + coordonneeX + "," + coordonneeY + "].");
                return new int[]{coordonneeX, coordonneeY};
            }
        } while (true);
    }

}
