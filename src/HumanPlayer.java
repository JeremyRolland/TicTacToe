import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player{

    public HumanPlayer(String representation) {
        super(representation);
    }

    @Override
    //Récupère le choix du joueur pour case vide
    public int getMoveFromPlayer(TicTacToe game) {
        int coordonneeX = -1, coordonneeY = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                view.messageNormal("Entrer une coordonnée [x][y]");
                view.askCoordinate('x');
                //On s'assure que c'est un nombre
                coordonneeX = Integer.parseInt(br.readLine());
                view.askCoordinate('y');
                //On s'assure que c'est un nombre
                coordonneeY = Integer.parseInt(br.readLine());
                //Transforme coordonnée 2D en 1D
                int position = super.getCoordinate(coordonneeX, coordonneeY);
                //Check case vide
                if(game.board[position].getRepresentation().equals("|   ")) {
                    return position;
                } else {
                    view.messageError("Erreur: Veuillez entrer une coordonnée qui n'est pas déjà utilisée");
                }
            } catch (NumberFormatException e) {
                view.messageError("Erreur: Veuillez entrer un nombre entier.");
            } catch (ArrayIndexOutOfBoundsException e) {
                view.messageError("Erreur: Veuillez rester dans la grille (min = 0 et max = 2)");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }



}
