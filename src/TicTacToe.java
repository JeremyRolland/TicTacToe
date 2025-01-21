import java.io.IOException;

public class TicTacToe {

    final int size = 9;
    Cell[] board = new Cell[size];
    View view = new View();
    InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);
    private Player currentPlayer;

    // Constructeur qui initialise le tableau de cellules
    public TicTacToe() {
        this.initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < size; i++) {
            board[i] = new Cell();
        }
    }

    public void play() throws IOException {
        int position = -1;
        int gameType = interactionUtilisateur.getGameType();

        Player[] players = this.initializePlayers(gameType);;
        Player currentPlayer = players[0];

        view.display(board);

        while (true) {
            position = currentPlayer.getMoveFromPlayer(this);
            board[position].setOwner(currentPlayer);
            view.display(board);
            if (isOver(board)) {
                interactionUtilisateur.announceWinner(currentPlayer);
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
    public boolean isOver(Cell[] board) {
        //Plateau non rempli
        boolean isFull = true;
        //Conditions de victoire
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Lignes
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colonnes
                {0, 4, 8}, {2, 4, 6}             // Diagonales
        };

        for (int[] condition : winConditions) {
            int a = condition[0], b = condition[1], c = condition[2];
            if (!board[a].getRepresentation().equals("|   ")) {
                if (board[a].getRepresentation().equals(board[b].getRepresentation()) &&
                        board[a].getRepresentation().equals(board[c].getRepresentation())) {
                    return true;
                }
            }

            // Vérifier si le plateau est plein
            if (isFull && board[a].getRepresentation().equals("|   ") ||
                    board[b].getRepresentation().equals("|   ") ||
                    board[c].getRepresentation().equals("|   ")) {
                isFull = false;
            }
        }
        //Plateau plein
        if (isFull) {
            view.messageVictory("Match null !!!");
            return true;
        }
        return false;
    }

}
