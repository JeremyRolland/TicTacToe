abstract public class Player {

    private String representation;
    private String name = null;
    protected View view = new View();

    public String getName() {
        return name;
    }

    public Player(String representation) {
        this.representation = representation;
        this.name = (representation == "| X ") ? "joueur1" : "joueur2";
    }

    public String getRepresentation() {
        return this.representation;
    }

    protected void setRepresentation(String representation) {
        if (representation == "| X " || representation == "| O ") {
            this.representation = representation;
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
