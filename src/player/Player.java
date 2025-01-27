package player;

import tools.View;
import game.TicTacToe;

abstract public class Player {

    private String symbol = null;
    private String name = null;
    protected View view = new View();

    // Constructeur
    public Player(String symbol) {
        this.symbol = symbol;
        this.name = (symbol == "X") ? "joueur1" : "joueur2";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    protected void setSymbol(String symbol) {
        if (symbol.toUpperCase().equals("X") || symbol.toUpperCase().equals("O")) {
            this.symbol = symbol.toUpperCase();
        } else {
            view.messageError("Le symbole: \"" + symbol + "\" est inconnu.");
        }
    }

    abstract public int[] getMoveFromPlayer(TicTacToe game);

}
