package model;

public class TicTacToe extends BoardGame {

    // Constructeur qui initialise le plateau
    public TicTacToe(int playersType) {
        super(playersType);
        this.size = 3;
        this.board = new Cell[size][size];
        super.initBoard();
        this.winCondition = 3;
    }

}
