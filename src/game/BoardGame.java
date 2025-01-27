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

    // Méthode pour vérifier une direction spécifique (horizontal ou vertical)
    protected boolean checkDirection(Player player, int row, int col, int rowDir, int colDir) {
        int count = 0;
        for (int i = 0; i < this.winCondition; i++) {
            int newRow = row + i * rowDir;
            int newCol = col + i * colDir;
            if (newRow >= 0 && newRow < this.size && newCol >= 0 && newCol < this.size &&
                    this.board[newRow][newCol].getOwner() == player) {
                count++;
                if (count == this.winCondition) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    // Méthode pour vérifier la diagonale descendante (de haut à gauche à bas à droite)
    protected boolean checkDiagonalDescendante(Player player, int row, int col) {
        return checkDirection(player, row, col, 1, 1);
    }

    // Méthode pour vérifier la diagonale ascendante (de bas à gauche à haut à droite)
    protected boolean checkDiagonalAscendante(Player player, int row, int col) {
        return checkDirection(player, row, col, -1, 1);
    }

    // Classe de gestion du tableau pour traitement
    public static class Tableau {

        // Découpe tableau en lignes
        public Cell[][] cutTab(Cell[][] board) {

            Cell[][] newTab = new Cell[board.length * board.length - 1][board[0].length];
            int lineCount = 0;

            // Récupérer lignes
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    newTab[i][j] = board[i][j];
                }
                lineCount++;
            }

            // Récupérer colonnes
            for (int i = 0; i < board.length; i++) {
                newTab[lineCount] = columnToLine(board,i);
                lineCount++;
            }

            // Récupérer les diagonales
            newTab[lineCount] = diagonalToLine(board,0);
            newTab[lineCount + 1] = diagonalToLine(board,2);

            return newTab;
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

        // Récupère une colonne en ligne
        public Cell[] columnToLine(Cell[][] board, int columnIndex) {

            Cell[] line = new Cell[board[0].length];

            for(int i = 0; i < board[0].length; i++) {
                line[i] = board[i][columnIndex];
            }
            return line;
        }
    }


}
