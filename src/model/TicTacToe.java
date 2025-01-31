package model;

import exceptions.InvalidPlayerType;

public class TicTacToe extends BoardGame {

    // Constructeur qui initialise le plateau
    public TicTacToe() {
        this.size = 3;
        this.board = new Cell[size][size];
        super.initBoard();
        this.winCondition = 3;
    }

}
