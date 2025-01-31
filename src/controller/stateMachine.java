package controller;

import vue.View;

public class stateMachine {

    private final GameController gameController = new GameController();
    private final View view = new View();
    private State currentState;

    public stateMachine() {
        this.currentState = State.INITIAL;
    }

    public void start() throws Exception {
        this.pickGame();
        this.pickPlayer();
        this.playGame();
        if(this.restartGame()){
            this.start();
        } else {
            this.stop();
        }
    }

    private void pickGame() throws Exception {
        if(currentState == State.INITIAL || currentState == State.RESTART_GAME) {
            currentState = State.PICK_GAME;
            view.messageState("Current state: " + currentState);
            gameController.pickGame();
        } else {
            view.messageError("Cannot pickGame. Current state: " + currentState);
        }
    }

    private void pickPlayer() throws Exception {
        if(currentState == State.PICK_GAME) {
            currentState = State.PICK_PLAYER;
            view.messageState("Current state: " + currentState);
            gameController.pickPlayers();
        } else {
            view.messageError("Cannot pickPlayer. Current state: " + currentState);
        }
    }

    private void playGame() {
        if(currentState == State.PICK_PLAYER) {
            currentState = State.PLAY_GAME;
            view.messageState("Current state: " + currentState);
            gameController.playGame();
        } else {
            view.messageError("Cannot startGame. Current state: " + currentState);
        }
    }

    private boolean restartGame() {
        if(currentState == State.PLAY_GAME) {
            currentState = State.RESTART_GAME;
            view.messageState("Current state: " + currentState);
            return gameController.restartGame();
        } else {
            view.messageError("Cannot finishGame. Current state: " + currentState);
        }
        return false;
    }

    private void stop() {
        if(currentState == State.RESTART_GAME) {
            currentState = State.STOPPED;
            view.messageState("Current state: " + currentState);
            gameController.quitGame();
        } else {
            view.messageError("Cannot stop. Current state: " + currentState);
        }
    }
}
