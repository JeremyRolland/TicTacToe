import java.io.IOException;

public class TicTacToe {

    final int size = 3;
    Cell[][] board = new Cell[size][size];
    View view = new View();
    InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);
    private Player currentPlayer;

    // Constructeur qui initialise le tableau de Cell
    public TicTacToe() {
        this.initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void play() {
        int[] position = {-1, -1};
        int gameType = interactionUtilisateur.getGameType();

        Player[] players = this.initializePlayers(gameType);;
        Player currentPlayer = players[0];

        view.display(board);

        while (true) {
            position = currentPlayer.getMoveFromPlayer(this);
            board[position[0]][position[1]].setOwner(currentPlayer);
            view.display(board);
            if (isOver(board, currentPlayer)) {
                interactionUtilisateur.restartGame(this);
            } else {
                currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
            }
        }
    }

    private Player[] initializePlayers(int gameType) {
        switch (gameType) {
            case 1:
                return new Player[]{ new HumanPlayer("X"), new HumanPlayer("O") };
            case 2:
                return new Player[]{ new HumanPlayer("X"), new ArtificialPlayer("O") };
            case 3:
                return new Player[]{ new ArtificialPlayer("X"), new ArtificialPlayer("O") };
            default:
                view.messageError("PAS COMPRIS");
                return new Player[]{};
        }
    }

    //Fin de partie
    public boolean isOver(Cell[][] board, Player player) {
        // Plateau non rempli
        boolean isFull = true;

        // Vérifie les conditions de victoire (lignes et colonnes)
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].getOwner() != null &&
                    board[i][0].getOwner().equals(board[i][1].getOwner()) &&
                    board[i][1].getOwner().equals(board[i][2].getOwner())) {
                view.messageVictory("Le joueur " + player.getName() + " gagne la partie");
                return true;
            }
            if (board[0][i].getOwner() != null &&
                    board[0][i].getOwner().equals(board[1][i].getOwner()) &&
                    board[1][i].getOwner().equals(board[2][i].getOwner())) {
                view.messageVictory("Le joueur " + player.getName() + " gagne la partie");
                return true;
            }
        }

        // Vérifie les diagonales
        if (board[0][0].getOwner() != null &&
                board[0][0].getOwner().equals(board[1][1].getOwner()) &&
                board[1][1].getOwner().equals(board[2][2].getOwner())) {
            view.messageVictory("Le joueur " + player.getName() + " gagne la partie");
            return true;
        }
        if (board[0][2].getOwner() != null &&
                board[0][2].getOwner().equals(board[1][1].getOwner()) &&
                board[1][1].getOwner().equals(board[2][0].getOwner())) {
            view.messageVictory("Le joueur " + player.getName() + " gagne la partie");
            return true;
        }

        // Vérifie si le plateau est plein
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getOwner() == null) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) break;
        }

        // Plateau plein
        if (isFull) {
            view.messageVictory("Match nul !!!");
            return true;
        }

        return false;
    }


}
