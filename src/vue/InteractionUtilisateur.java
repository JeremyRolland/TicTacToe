package vue;

import exceptions.InvalidRangeException;
import model.BoardGame;
import model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InteractionUtilisateur {

    private final View view = new View();
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Constructeur
    public InteractionUtilisateur() {
    }

    // Demande un entier à l'utilisateur
    public int askInt(String message) throws Exception {
        view.messageNormal(message);
        return Integer.parseInt(br.readLine());
    }

    public String askString(String message) throws Exception {
        view.messageNormal(message);
        return br.readLine();
    }

}
