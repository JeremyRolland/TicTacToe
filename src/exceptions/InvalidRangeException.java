package exceptions;

public class InvalidRangeException extends Exception {

    public InvalidRangeException(int rangeMin, int rangeMax) {super("Erreur: Veuillez entrer un nombre compris entre " + rangeMin + " et " + rangeMax);}
}
