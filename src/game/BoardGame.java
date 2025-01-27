package game;

import player.Player;
import tools.InteractionUtilisateur;
import tools.View;

public abstract class BoardGame {

    protected int size;
    protected Cell[][] board;
    protected View view = new View();
    protected InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);
    protected Player currentPlayer;

    public BoardGame(int size) {
        this.size = size;
        this.board = new Cell[size][size];
        this.initBoard();
    }

    // Initialiser le plateau de jeu
    public void initBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getBoard() {

        return board;
    }

    // Lancer une partie
    protected abstract void play();
    // Initialiser les joueurs
    protected abstract Player[] initializePlayers(int gameType);
    // Vérifie fin de partie
    protected abstract boolean isOver(Cell[][] board);
    // Vérifie si un joueur gagne
    protected abstract boolean isWin(Cell[][] board);
    // Vérifie si plateau complet
    protected abstract boolean isBoardFull();

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
