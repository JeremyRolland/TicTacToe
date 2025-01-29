package model;

public class Gomoku extends BoardGame{

    // Constructeur
    public Gomoku() {
        this.size = 15;
        this.board = new Cell[size][size];
        super.initBoard();
        this.winCondition = 5;
    }

}
