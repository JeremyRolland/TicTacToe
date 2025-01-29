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

    // Lancer une partie
    public abstract void play();
    // Initialiser les joueurs
    protected abstract Player[] initializePlayers(int gameType);


    // Vérifie fin de partie
    protected boolean isOver(Cell[][] board, Player player, int winCondition) {

        if(this.hasWinner(board, player,winCondition) ) {
            return true;
        } else if(this.isBoardFull()) {
            return true;
        }
        return false;
    }

    // Vérifie si plateau complet
    protected boolean isBoardFull() {
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


    // Vérifie si un joueur a gagné
    public boolean hasWinner(Cell[][] board, Player player, int winCondition) {
        int rows = board.length;
        int cols = board[0].length;

        // Vérification des lignes
        for (int i = 0; i < rows; i++) {
            if (checkDirection(board, player, winCondition, i, 0, 0, 1)) return true; // Horizontal
        }

        // Vérification des colonnes
        for (int j = 0; j < cols; j++) {
            if (checkDirection(board, player, winCondition, 0, j, 1, 0)) return true; // Vertical
        }

        // Vérification des diagonales descendantes
        for (int i = 0; i <= rows - winCondition; i++) {
            for (int j = 0; j <= cols - winCondition; j++) {
                if (checkDirection(board, player, winCondition, i, j, 1, 1)) return true; // Diagonale descendante
            }
        }

        // Vérification des diagonales ascendantes
        for (int i = winCondition - 1; i < rows; i++) {
            for (int j = 0; j <= cols - winCondition; j++) {
                if (checkDirection(board, player, winCondition, i, j, -1, 1)) return true; // Diagonale ascendante
            }
        }
        return false;
    }

    // Méthode auxiliaire pour vérifier une direction spécifique
    private static boolean checkDirection(Cell[][] board, Player player, int winCondition, int startRow, int startCol, int rowDir, int colDir) {
        int count = 0;
        for (int i = 0; i < winCondition; i++) {
            int newRow = startRow + i * rowDir;
            int newCol = startCol + i * colDir;
            if (board[newRow][newCol].getOwner() == player) {
                count++;
                if (count == winCondition) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

}


