package model;

import model.players.Player;

public class Cell {

    private String representation = " ";
    private Player owner = null;

    // Retourne le Player propriétaire
    public Player getOwner() {return owner;}

    // Instancie le Player propriétaire
    public void setOwner(Player owner) {
        this.owner = owner;
        this.representation = owner.getSymbol();
    }

    // Retourne la représentation
    public String getRepresentation() {return this.representation;}

}
