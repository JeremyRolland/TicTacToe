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

}
