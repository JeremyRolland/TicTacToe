public class Tableau {
/*
    private Cell[][] tableau;

    // Constructeur
    public Tableau(int size) {
        this.tableau = new Cell[size * size - 1][size];
    }

 */

    // Découpe tableau en lignes
    public Cell[][] cutTab(Cell[][] board) {

        Cell[][] newTab = new Cell[board.length * board.length - 1][board[0].length];
        int lineCount = 0;

        // Récupérer lignes
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                newTab[i][j] = board[i][j];
            }
            lineCount++;
        }

        // Récupérer colonnes
        for (int i = 0; i < board.length; i++) {
            newTab[lineCount] = columnToLine(board,i);
            lineCount++;
        }

        // Récupérer les diagonales
        newTab[lineCount] = diagonalToLine(board,0);
        newTab[lineCount + 1] = diagonalToLine(board,2);

        return newTab;
    }

    // Récupère une diagonale en ligne
    public Cell[] diagonalToLine(Cell[][] board, int diagIndex) {

        Cell[] line = new Cell[board[0].length];

        if(diagIndex == 0) {
            for(int i=0; i < board[0].length; i++) {
                line[i] = board[i][i];
            }
        } else if(diagIndex == 2) {
            for(int i=0; i < board[0].length; i++) {
                line[i] = board[2-i][i];
            }
        }
        return line;
    }

    // Récupère une colonne en ligne
    public Cell[] columnToLine(Cell[][] board, int columnIndex) {

        Cell[] line = new Cell[board[0].length];

        for(int i = 0; i < board[0].length; i++) {
            line[i] = board[i][columnIndex];
        }
        return line;
    }
/*
    // Affiche le tableau
    public void display() {
        for (int i = 0; i < this.tableau.length; i++) {
            for (int j = 0; j < this.tableau[i].length; j++) {
                System.out.print(this.tableau[i][j].getRepresentation() + " ");
            }
        }
        System.out.println();
    }

 */


}
