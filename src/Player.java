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
        if (symbol.toUpperCase().equals('X') || symbol.toUpperCase().equals('O')) {
            this.symbol = symbol;
        } else {
            view.messageError("Le symbole: \"" + symbol + "\" est inconnu.");
        }
    }

    //Passe 2D à 1D
    protected int getCoordinate(int x, int y) {
        int[][] key = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
        };
        return key[x][y];
    }

    abstract public int getMoveFromPlayer(TicTacToe game);

}
