package controller;

import exceptions.InvalidPlayerType;
import exceptions.InvalidRangeException;
import exceptions.InvalidStringException;
import model.*;
import vue.*;

import java.io.InvalidClassException;

public class GameController {

    private final View view;
    private final InteractionUtilisateur user;
    private Player[] players = new Player[2];
    private BoardGame boardGame;

    // Constructeur
    public GameController() {
        this.view = new View();
        this.user = new InteractionUtilisateur();
    }

    public void pickGame() throws Exception {
        int maxGame = 3;
        int userChoiceGame = user.askInt(view.getMessageChoiceGame());
        if(this.validateIntChoice(userChoiceGame, maxGame)) {
            switch (userChoiceGame) {
                case 1:
                    boardGame = new TicTacToe();
                    break;
                case 2:
                    boardGame = new Gomoku();
                    break;
                case 3:
                    boardGame = new Puissance4();
                    break;
            }
        } else {
            pickGame();
        }
    }

    public void pickPlayers() throws Exception {
        int maxPLayersType = 3;
        int userChoicePlayer = user.askInt(view.getMesaggeChoicePlayer());
        if(this.validateIntChoice(userChoicePlayer, maxPLayersType)) {
            this.initPlayers(userChoicePlayer);
        } else {
            pickPlayers();
        }
    }

    // Controle le déroulement d'une partie
    public void playGame() {

        int[] position;
        Player currentPlayer = this.players[0];

        view.display(boardGame.boardToString());

        while (true) {
            position = boardGame.getMoveFromPlayer(currentPlayer);
            boardGame.getBoard()[position[0]][position[1]].setOwner(currentPlayer);
            view.display(boardGame.boardToString());
            if (boardGame.isOver(boardGame.getBoard(), currentPlayer)) {
                view.announceWinner(currentPlayer.getName());
                break;
            } else {
                currentPlayer = (currentPlayer == this.players[0] ? this.players[1] : this.players[0]);
            }
        }
    }

    // Gère fin de partie
    public boolean restartGame() {

        try{
            String choixUser = user.askString(view.messageRestart());
            if(choixUser.equals("oui")) {
                return true;
            } else if (choixUser.equals("non")) {
                return false;
            } else {
                throw new InvalidStringException();
            }
        } catch (InvalidStringException e){
            view.messageError(e.getMessage());
            this.restartGame();
        }
        catch (Exception e){
            e.printStackTrace();
            this.restartGame();
        }
        return false;
    }

    //Quitter le jeu
    public void quitGame() {
        view.messageQuitGame();
        System.exit(0);
    }

    // Vérifie int compris dans intervale
    private boolean validateIntChoice(int choice, int rangeMax) {
        return (choice >= 1 && choice <= rangeMax);
    }

    // Initialiser les joueurs
    private void initPlayers(int choice) throws InvalidPlayerType {
        switch (choice) {
            case 1:
                this.players = new Player[]{new HumanPlayer("X"), new HumanPlayer("O")};
                break;
            case 2:
                this.players = new Player[]{new HumanPlayer("X"), new ArtificialPlayer("O")};
                break;
            case 3:
                this.players = new Player[]{new ArtificialPlayer("X"), new ArtificialPlayer("O")};
                break;
        }
    }

}
