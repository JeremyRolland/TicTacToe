package game;

import player.Player;

public class Puissance4 extends BoardGame {

    // Constructeur qui initialise le plateau
    public Puissance4() {
        this.size = 6;
        this.board = new Cell[size][size + 1];
        super.initBoard();
        this.winCondition = 4;
    }

}
