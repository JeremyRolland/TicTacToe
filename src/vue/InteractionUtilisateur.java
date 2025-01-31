package vue;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class InteractionUtilisateur {

    private final View view = new View();
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Demande un int à l'utilisateur
    public int askInt(String message) throws Exception {
        view.messageNormal(message);
        return Integer.parseInt(br.readLine());
    }

    // Demande un String à l'utilisateur
    public String askString(String message) throws Exception {
        view.messageNormal(message);
        return br.readLine();
    }

}
