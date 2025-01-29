package game;

import player.*;

public class TicTacToe extends BoardGame {

    // Constructeur qui initialise le plateau
    public TicTacToe() {
        this.size = 3;
        this.board = new Cell[size][size];
        super.initBoard();
        this.winCondition = 3;
    }

    public void play() {

        int[] position;
        int gameType = interactionUtilisateur.getGameType();
        Player[] players = this.initializePlayers(gameType);;
        this.currentPlayer = players[0];

        view.display(this.board);

        while (true) {
            position = this.currentPlayer.getMoveFromPlayer(this.board);
            this.board[position[0]][position[1]].setOwner(this.currentPlayer);
            view.display(this.board);
            if (this.isOver(this.board)) {
                interactionUtilisateur.announceWinner(this.currentPlayer);
                interactionUtilisateur.restartGame(this);
            } else {
                this.currentPlayer = (this.currentPlayer == players[0]) ? players[1] : players[0];
            }
        }
    }

     protected Player[] initializePlayers(int gameType) {
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

    public boolean isOver(Cell[][] board) {


        if(isBoardFull() || super.hasWinner(board, 3)) {
            return true;
        }
        return false;
    }

    // Vérifie victoire
    public boolean isWin(Cell[][] board) {

        for (int i = 0; i < board.length; i++) {
            if(board[i][0].getOwner() != null &&
                    board[i][0].getOwner().equals(board[i][1].getOwner()) &&
                    board[i][0].getOwner().equals(board[i][2].getOwner())) {
                interactionUtilisateur.announceWinner(this.currentPlayer);
                return true;
            }
        }
        return false;
    }

    // Plateau plein
    protected  boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j].getOwner() == null) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) break;
        }
        if (isFull) {
            view.messageVictory("Match nul !!!");
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkWin(Player player) {
        return false;
    }

}
