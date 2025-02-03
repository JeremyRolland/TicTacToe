package model.players;

abstract public class Player {

    private String symbol = null;
    private String name = null;

    // Constructeur
    public Player(String symbol) {
        this.symbol = symbol;
        this.name = (symbol == "X") ? "joueur1" : "joueur2";
    }

    // Retourne nom
    public String getName() {
        return name;
    }

    // Retourne symbole
    public String getSymbol() {
        return this.symbol;
    }

}
