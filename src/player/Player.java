package player;

import tools.View;

abstract public class Player {

    private String symbol = null;
    private String name = null;
    protected View view = new View();

    // Constructeur
    public Player(String symbol) {
        this.symbol = symbol;
        this.name = (symbol == "X") ? "joueur1" : "joueur2";
    }

    // Retourne nom
    public String getName() {
        return name;
    }

    // Instancie nom
    public void setName(String name) {
        this.name = name;
    }

    // Retourne symbole
    public String getSymbol() {
        return this.symbol;
    }

    // Instancie symbole
    protected void setSymbol(String symbol) {
        if (symbol.toUpperCase().equals("X") || symbol.toUpperCase().equals("O")) {
            this.symbol = symbol.toUpperCase();
        } else {
            view.messageError("Le symbole: \"" + symbol + "\" est inconnu.");
        }
    }

}
