package game;

import player.Player;
import tools.InteractionUtilisateur;
import tools.View;

public abstract class BoardGame {

    protected int size;
    protected int winCondition;
    protected Cell[][] board;
    protected View view = new View();
    protected InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);
    protected Player currentPlayer;

    // Initialiser le plateau de jeu
    public void initBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getBoard() {return board;}

    // Lancer une partie
    public abstract void play();
    // Initialiser les joueurs
    protected abstract Player[] initializePlayers(int gameType);
    // Vérifie fin de partie
    protected abstract boolean isOver(Cell[][] board);
    // Vérifie si un joueur gagne
    protected abstract boolean isWin(Cell[][] board);
    // Vérifie si plateau complet
    protected abstract boolean isBoardFull();
    // Essai de check qqn gagne
    protected abstract boolean checkWin(Player player);

    // Récupère les lignes d'un tableau de CEll
    public Cell[][] extractLines(Cell[][] board) {
        Cell[][] newTab = new Cell[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newTab[i][j] = board[i][j];
            }
        }
        return newTab;
    }

    // Récupère les colonnes en lignes depuis un tableau de Cell
    public Cell[][] extractColumns(Cell[][] board) {

        Cell[][] newTab = new Cell[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newTab[i][j] = board[j][i];
            }
        }
        return newTab;
    }

    // Récupère les diagonales montantes en ligne depuis un tableau de Cell
    public Cell[][] extractUpperDiagonal(Cell[][] board, int nbSymbol) {

        Cell[][] newTab = new Cell[board.length + board[0].length - 1 - (nbSymbol - 1) * 2][Math.min(board.length, board[0].length)];
        // Initialise le tableau
        for (int i = 0; i < newTab.length; i++) {
            for (int j = 0; j < newTab[0].length; j++) {
                newTab[i][j] = new Cell();
            }
        }

        for(int k = nbSymbol - 1; k < board.length + board[0].length - nbSymbol - 1; k++) {
            for (int i = k; i < board.length; i--) {
                for (int j = 0; j < board[0].length; j++) {
                    newTab[k - nbSymbol - 1][j] = board[i][j];
                }
            }
        }
        return newTab;
    }


    // Découpe tableau en lignes
    public boolean hasWinner(Cell[][] board, int nbSymbol) {

        // Vérifier les lignes
        Cell[][] Lines = this.extractLines(board);
        if(this.lineHasWinner(Lines, nbSymbol)) {
            return true;
        }
        // Vérifier les colonnes
        Cell[][] Columns = this.extractColumns(board);
        if(this.lineHasWinner(Columns, nbSymbol)) {
            return true;
        }

        // Récupérer les diagonales
        Cell[][] UpperDiagonal = this.extractUpperDiagonal(board, nbSymbol);
        if(this.lineHasWinner(UpperDiagonal, nbSymbol)) {
            return true;
        }
        return false;
    }

    // Récupère une diagonale en ligne
    public Cell[] diagonalToLine(Cell[][] board, int diagIndex) {

        Cell[] line = new Cell[board[0].length];

        if(diagIndex == 0) {
            for(int i=0; i < board[0].length; i++) {
                line[i] = board[i][i];
            }
        } else if(diagIndex == 2) {
            for(int i=0; i < board[0].length; i++) {
                line[i] = board[2-i][i];
            }
        }
        return line;
    }

    // Vérifie si une ligne gagne depuis un tableau de Cell
    public boolean lineHasWinner(Cell[][] board, int nbSymbol) {
        int i = 0, count;
        Cell[] line = new Cell[board.length];
        boolean hasWinner = false;
        while (i < line.length && !hasWinner) {
            count = 1;
            for (int j = 0; j < line.length; j++) {
                line[j] = board[i][j];
            }
            int k = 1;
            while(k < line.length && !hasWinner) {
                if(line[k].getOwner() != line[k-1].getOwner()) {
                    count = 1;
                } else {
                    count++;
                }
                if(count == nbSymbol && line[k].getOwner() != null) {
                    hasWinner = true;
                }
                k++;
            }
            i++;
        }
        return hasWinner;
    }

}


