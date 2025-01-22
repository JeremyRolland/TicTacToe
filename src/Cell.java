public class Cell {

    private String representation = "|   ";
    private Player owner = null;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        if(owner.getSymbol().equals("X")) {
            this.representation = "| X ";
        } else if(owner.getSymbol().equals("O")) {
            this.representation = "| O ";
        } else {
            this.representation = "|   ";
        }
    }

    public String getRepresentation() {

        return this.representation;
    }

    public void reset() {
        this.representation = "|   ";
        this.owner = null;
    }

}
