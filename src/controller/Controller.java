package controller;

import exceptions.InvalidRangeException;
import exceptions.InvalidStringException;
import model.*;
import vue.*;

import java.io.InvalidClassException;

public class Controller {

    private final View view;
    private final InteractionUtilisateur user;

    public Controller() {
        this.view = new View();
        this.user = new InteractionUtilisateur();
    }

    // Initialise le type de partie
    public BoardGame initGame() {

        int maxPLayers = 3;
        int maxGames = 3;
        try {
            int userChoiceGame = user.askInt(view.getMessageChoiceGame());
            this.validateIntChoice(userChoiceGame, maxGames);
            int userChoicePlayer = user.askInt(view.getMesaggeChoicePlayer());

            this.validateIntChoice(userChoicePlayer, maxPLayers);
            switch (userChoiceGame) {
                case 1:
                    return new TicTacToe(userChoicePlayer);
                case 2:
                    return new Gomoku(userChoicePlayer);
                case 3:
                    return new Puissance4(userChoicePlayer);
                default:
                    throw new IllegalArgumentException("Choix de jeu inconnu");
            }
        } catch (InvalidClassException e) {
            view.messageError(e.getMessage());
            initGame();
        } catch (NumberFormatException e) {
            view.messageError("Erreur: Veuillez entrer un nombre entier.");
            initGame();
        } catch (Exception e) {
            view.messageError(e.getMessage());
            initGame();
        }
        return null;
    }

    public void playGame(BoardGame game) {

        int[] position;
        Player currentPlayer = game.getPlayers()[0];

        view.display(game.boardToString());

        while (true) {
            position = game.getMoveFromPlayer(currentPlayer);
            game.getBoard()[position[0]][position[1]].setOwner(currentPlayer);
            view.display(game.boardToString());
            if (game.isOver(game.getBoard(), currentPlayer)) {
                view.announceWinner(currentPlayer.getName());
                this.restartGame();
            } else {
                currentPlayer = (currentPlayer == game.getPlayers()[0]) ? game.getPlayers()[1] : game.getPlayers()[0];
            }
        }
    }

    public void restartGame() {

        try{
            String choixUser = user.askString(view.messageRestart());
            if(choixUser.equals("oui")) {
                this.playGame(initGame());
            } else if (choixUser.equals("non")) {
                this.quitGame();
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
    }

    //Quitter le jeu
    public void quitGame() {
        view.messageQuitGame();
        System.exit(0);
    }

    // Vérifie int compris dans intervale
    private void validateIntChoice(int choice, int rangeMax) throws InvalidRangeException {
        if(choice < 1 || choice > rangeMax) {
            throw new InvalidRangeException(1, rangeMax);
        }
    }

}
