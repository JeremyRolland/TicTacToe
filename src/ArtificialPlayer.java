import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArtificialPlayer extends Player {

    public enum Level {EASY,MEDIUM,HARD };
    private Level IADifficulty;

    public ArtificialPlayer(String symbol) {
        super(symbol);
        this.IADifficulty = Level.EASY;
    }

    @Override
    //Récupère le choix du joueur pour case vide
    public int getMoveFromPlayer(TicTacToe game) {
        int position = -1;
            switch(this.IADifficulty) {
                case EASY:
                    return this.generateRandomPosition(game);
                case MEDIUM:

                    Cell[] copyBoard = game.board;
                    //this.changeSymbol();
                   // for(Cell cell : copyBoard) {
                        for(int i=0; i<copyBoard.length; i++) {
                            if(copyBoard[i].getRepresentation().equals("|   ")) {
                                game.board[i].setOwner(this);
                                if (game.isOver(copyBoard)) {
                                    System.out.println("La case [" + i + "]: permet une victoire");
                                } else {
                                    System.out.println("La case [" + i + "]: ne permet pas une victoire");
                                }
                            } else {

                            }

                        }

                   // }

                    return this.generateRandomPosition(game);
                case HARD:
                    return position;
                default:
                    view.messageError("Erreur: Difficulté IA inconnue");
            }
        return position;
    }

    private void changeSymbol() {
        if(super.getSymbol().equals("| X ")) {
            super.setSymbol("O");
        } else {
            super.setSymbol("X");
        }
    }

    private int generateRandomPosition(TicTacToe game) {
        int position = -1, coordonneeX = -1, coordonneeY = -1;
        do {
            coordonneeX = (int) (Math.random() * 3);
            coordonneeY = (int) (Math.random() * 3);
            //Transforme coordonnée 2D en 1D
            position = super.getCoordinate(coordonneeX, coordonneeY);
            if(game.board[position].getOwner() == null) {
                return position;
            }
        } while (true);
    }

}
