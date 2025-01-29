package game;

import player.*;
import tools.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class BoardGame {

    protected int size;
    protected int winCondition;
    protected Cell[][] board;
    protected View view = new View();
    private InteractionUtilisateur interactionUtilisateur = new InteractionUtilisateur(view);
    protected Player currentPlayer;

    // Initialiser le plateau de jeu
    public void initBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    // Lancer une partie
    public void play() {

        int[] position;
        int gameType = interactionUtilisateur.getGameType();
        Player[] players = this.initializePlayers(gameType);;
        this.currentPlayer = players[0];

        view.display(this.board);

        while (true) {
            position = this.getMoveFromPlayer();
            this.board[position[0]][position[1]].setOwner(this.currentPlayer);
            view.display(this.board);
            if (this.isOver(this.board, this.currentPlayer, this.winCondition)) {
                interactionUtilisateur.announceWinner(this.currentPlayer);
                interactionUtilisateur.restartGame(this);
            } else {
                this.currentPlayer = (this.currentPlayer == players[0]) ? players[1] : players[0];
            }
        }
    }

    protected int[] getMoveFromPlayer() {
        int coordonneeX = -1, coordonneeY = -1;
        // Joueur humain
        if (this.currentPlayer.getClass().getSimpleName().equals("HumanPlayer")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            do {
                try {
                    view.messageNormal("Entrer une coordonnée [x][y]");
                    view.askCoordinate('x');
                    //On s'assure que c'est un nombre
                    coordonneeX = Integer.parseInt(br.readLine());
                    view.askCoordinate('y');
                    //On s'assure que c'est un nombre
                    coordonneeY = Integer.parseInt(br.readLine());
                    //Check case vide
                    if(board[coordonneeX][coordonneeY].getOwner() == null) {
                        return new int[]{coordonneeX, coordonneeY};
                    } else {
                        view.messageError("Erreur: Veuillez entrer une coordonnée qui n'est pas déjà utilisée");
                    }
                } catch (NumberFormatException e) {
                    view.messageError("Erreur: Veuillez entrer un nombre entier.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    view.messageError("Erreur: Veuillez rester dans la grille (min = 0 et max = " + (board.length - 1) + ")");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (true);
        // Joueur artificiel
        } else {
            do {
                coordonneeX = (int) (Math.random() * board.length);
                coordonneeY = (int) (Math.random() * board.length);
                //Transforme coordonnée 2D en 1D
                if(board[coordonneeX][coordonneeY].getOwner() == null) {
                    System.out.println("position jouée: [" + coordonneeX + "," + coordonneeY + "].");
                    return new int[]{coordonneeX, coordonneeY};
                }
            } while (true);
        }
    }

    // Initialiser les joueurs
    private Player[] initializePlayers(int gameType) {
        switch (gameType) {
            case 1:
                return new Player[]{ new HumanPlayer("X"), new HumanPlayer("O") };
            case 2:
                return new Player[]{ new HumanPlayer("X"), new ArtificialPlayer("O") };
            case 3:
                return new Player[]{ new ArtificialPlayer("X"), new ArtificialPlayer("O") };
            default:
                view.messageError("PAS COMPRIS");
                return new Player[]{};
        }
    }

    // Vérifie fin de partie
    private boolean isOver(Cell[][] board, Player player, int winCondition) {

        if(this.hasWinner(board, player,winCondition) ) {
            return true;
        } else if(this.isBoardFull()) {
            return true;
        }
        return false;
    }

    // Vérifie si plateau complet
    private boolean isBoardFull() {
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
    private boolean hasWinner(Cell[][] board, Player player, int winCondition) {
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


