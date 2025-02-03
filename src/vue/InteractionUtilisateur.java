package vue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;


public class InteractionUtilisateur {

    private final View view = new View();
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner = new Scanner(System.in);

    public int askInt(String message) {
        int answer = -1;
        try{
            view.messageNormal(message);
            answer = scanner.nextInt();
        } catch(InputMismatchException e) {
            view.messageError("Erreur: Veuiller entrer un nombre entier");
            scanner.next();
        }
        return answer;
    }

    public String askString(String message) {
        view.messageNormal(message);
        return scanner.next();
    }

}
