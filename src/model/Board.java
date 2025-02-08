package model;

import model.players.Player;

public class Board {

    private final Cell[][] board;

    public Board(int row, int col) {
        board = new Cell[row][col];
        init();
    }

    // Initialiser le plateau de jeu
    public void init() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    // Retourne un tableau de representation des Cells
    public String[][] convertString() {
        String[][] stringCells = new String[this.board.length][this.board[0].length];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                stringCells[i][j] = this.board[i][j].getRepresentation();
            }
        }
        return stringCells;
    }

    // Attribue une cellule à un joueur
    public void setCellOwner(int[] position, Player player) {
        if(position[0] >= 0 && position[0] < this.board.length && position[1] >= 0 && position[1] < this.board[0].length) {
            this.board[position[0]][position[1]].setOwner(player);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getRowSize() {
        return this.board.length;
    }

    public int getColSize() {
        return this.board[0].length;
    }

    public Player getCellOwner(int row, int col) {
        return this.board[row][col].getOwner();
    }

}
