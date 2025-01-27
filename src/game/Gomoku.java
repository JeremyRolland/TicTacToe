package game;

import player.Player;

public class Gomoku extends BoardGame{


    public Gomoku() {
        super(15);
    }

    @Override
    protected void play() {

    }

    @Override
    protected Player[] initializePlayers(int gameType) {
        return new Player[0];
    }

    @Override
    protected boolean isOver(Cell[][] board) {
        return false;
    }

    @Override
    protected boolean isWin(Cell[][] board) {
        return false;
    }

    @Override
    protected boolean isBoardFull() {
        return false;
    }
}
