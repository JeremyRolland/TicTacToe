package controller;

import model.Board;
import model.players.*;
import vue.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class BoardGame {

    protected int winCondition;
    protected View view = new View();
    protected Board board;

    public BoardGame(int row, int col, int winCondition) {
        board = new Board(row, col);
        this.winCondition = winCondition;
    }

    // Retourne coup du joueur
    public int[] getMoveFromPlayer(Player player) {
        int row = -1, col = -1;
        // Joueur humain
        if (player instanceof HumanPlayer) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                try {
                    view.messageNormal("Entrer une coordonnée [x][y]");
                    view.askCoordinate('x');
                    //On s'assure que c'est un nombre
                    row = Integer.parseInt(br.readLine());
                    view.askCoordinate('y');
                    //On s'assure que c'est un nombre
                    col = Integer.parseInt(br.readLine());
                    //Check case vide
                    if(board.getCellOwner(row,col) == null) {
                        return new int[]{row, col};
                    } else {
                        view.messageError("Erreur: Veuillez entrer une coordonnée qui n'est pas déjà utilisée");
                    }
                } catch (NumberFormatException e) {
                    view.messageError("Erreur: Veuillez entrer un nombre entier.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    view.messageError("Erreur: Veuillez rester dans la grille (min = 0 et max = " + (board.getRowSize() - 1) + ")");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (true);
        // Joueur artificiel
        } else {
            do {
                row = (int) (Math.random() * board.getRowSize());
                col = (int) (Math.random() * board.getColSize());
                //Transforme coordonnée 2D en 1D
                if(board.getCellOwner(row,col) == null) {
                    System.out.println("position jouée: [" + row + "," + col + "].");
                    return new int[]{row, col};
                }
            } while (true);
        }
    }

    public Board getBoard() {
        return board;
    }

    public boolean isOver(Board board, Player player) {

        if(this.hasWinner(board, player,this.winCondition) ) {
            return true;
        } else return this.isBoardFull();
    }
    
    private boolean isBoardFull() {
        boolean isFull = true;

        for(int i = 0; i < board.getRowSize(); i++) {
            for(int j = 0; j < board.getColSize(); j++) {
                if(board.getCellOwner(i,j) == null) {
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

    private boolean hasWinner(Board board, Player player, int winCondition) {
        int rows = board.getRowSize();
        int cols = board.getColSize();

        // Vérification des lignes
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - winCondition; j++) { // ajout du parcours des colonnes
                if (checkDirection(board, player, winCondition, i, j, 0, 1)) return true; // Horizontal
            }
        }

        // Vérification des colonnes
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - winCondition; i++) { // ajout du parcours des colonnes
                if (checkDirection(board, player, winCondition, i, j, 1, 0)) return true; // Vertical
            }
        }

        // Vérification des diagonales descendantes
        for (int i = 0; i <= rows - winCondition; i++) {
            for (int j = 0; j <= cols - winCondition; j++) { // ajout du parcours des colonnes
                if (checkDirection(board, player, winCondition, i, j, 1, 1)) return true; // Diagonale descendante
            }
        }

        // Vérification des diagonales ascendantes
        for (int i = winCondition - 1; i < rows; i++) {
            for (int j = 0; j <= cols - winCondition; j++) { // ajout du parcours des colonnes
                if (checkDirection(board, player, winCondition, i, j, -1, 1)) return true; // Diagonale ascendante
            }
        }
        return false;
    }


    // Méthode auxiliaire pour vérifier une direction spécifique
    private static boolean checkDirection(Board board, Player player, int winCondition, int startRow, int startCol, int rowDir, int colDir) {
        int count = 0;
        for (int i = 0; i < winCondition; i++) {
            int newRow = startRow + i * rowDir;
            int newCol = startCol + i * colDir;
            if (newRow >= 0 && newRow < board.getRowSize() && newCol >= 0 && newCol < board.getColSize() && // check bordures car P4 pas carré
                    board.getCellOwner(newRow, newCol) == player) {
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


