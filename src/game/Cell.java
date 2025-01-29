package game;

import player.Player;

public class Cell {

    private String representation = "|   ";
    private Player owner = null;

    // Retourne le Player propriétaire
    public Player getOwner() {
        return owner;
    }

    // Instancie le Player propriétaire
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

    // Retourne la représentation
    public String getRepresentation() {

        return this.representation;
    }

}
