package game;

import player.ArtificialPlayer;
import player.HumanPlayer;
import player.Player;

public class Gomoku extends BoardGame{


    public Gomoku() {
        this.size = 15;
        this.board = new Cell[size][size];
        super.initBoard();
        this.winCondition = 5;
    }

    @Override
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
            if (super.isOver(this.board, this.currentPlayer, this.winCondition)) {
                interactionUtilisateur.restartGame(this);
            } else {
                this.currentPlayer = (this.currentPlayer == players[0]) ? players[1] : players[0];
            }
        }
    }

    @Override
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

}
