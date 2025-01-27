package player;

import game.Cell;
import game.TicTacToe;

public class ArtificialPlayer extends Player {

    private enum Level {EASY,MEDIUM,HARD };
    private final Level IADifficulty;
    private boolean earlyGame = true;

    public ArtificialPlayer(String symbol) {
        super(symbol);
        this.IADifficulty = Level.EASY;
    }

    @Override
    //Récupère le choix du joueur pour case vide
    public int[] getMoveFromPlayer(TicTacToe game) {
        int position[] = {-1, -1};
            switch(this.IADifficulty) {
                case EASY:
                    return this.generateRandomPosition(game.getBoard());
                case MEDIUM:
                    position = this.analyzeWinConditions(game.getBoard());
                    System.out.println("position jouée: [" + position[0] + "," + position[1] + "].");
                    if (position[0] == -1) {
                        position = this.generateRandomPosition(game.getBoard());
                    }
                    return position;
                case HARD:
                    this.countCellPlayed(game.getBoard());
                    if(this.earlyGame) {
                        position = this.playToWin(game.getBoard());
                    } else {
                        position = this.analyzeWinConditions(game.getBoard());
                        if (position[0] == -1) {
                            position = this.generateRandomPosition(game.getBoard());
                        }
                    }
                    return position;
                default:
                    view.messageError("Erreur: IA plantée !!! :o");
            }
        return position;
    }

    private void countCellPlayed(Cell[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j].getOwner() != null) {
                    count++;
                    if(count == 1 && this.getSymbol().equals("O")) {
                        this.earlyGame = false;
                    }
                    if(count == 2 && this.getSymbol().equals("X")) {
                        this.earlyGame = false;
                    }
                }
            }
        }
    }

    private int[] playToWin(Cell[][] board) {
        if(board[1][1].getOwner() == null) {
            return new int[]{1, 1};
        }
        if(board[0][1].getOwner() != null) {
            if(board[2][0].getOwner() != null) {
                return new int[]{1, 2};
            } else {
                return new int[]{0, 2};
            }
        } else if(board[1][2].getOwner() != null) {
            if(board[2][0].getOwner() != null) {
                return new int[]{2, 1};
            } else {
                return new int[]{2, 2};
            }
        } else if(board[2][1].getOwner() != null) {
            if(board[2][0].getOwner() != null) {
                return new int[]{1, 0};
            } else {
                return new int[]{2, 0};
            }
        } else if(board[1][0].getOwner() != null) {
            if(board[2][0].getOwner() != null) {
                return new int[]{0, 1};
            } else {
                return new int[]{0, 0};
            }
        } else {
            return generateRandomPosition(board);
        }
    }

    private int[] analyzeWinConditions(Cell[][] board) {

        int[] result = {-1, -1};

        if(board[1][1].getOwner() == null) {
            return new int[]{1, 1};
        }

        // Check des lignes
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].getOwner() != null) {
                if (board[i][1].getOwner() != null) {
                    if (board[i][0].getOwner().equals(board[i][1].getOwner())) {
                        if (board[i][2].getOwner() == null) {
                            if (board[i][0].getOwner().toString().contains(this.getSymbol())) {
                                return new int[]{i, 2};
                            } else {
                                result[0] = i;
                                result[1] = 2;
                            }
                        }
                    }
                } else if (board[i][2].getOwner() != null) {
                    if (board[i][0].getOwner().equals(board[i][2].getOwner())) {
                        if (board[i][1].getOwner() == null) {
                            if (board[i][0].getOwner().toString().contains(this.getSymbol())) {
                                return new int[]{i, 1};
                            } else {
                                result[0] = i;
                                result[1] = 1;
                            }
                        }
                    }
                }
            } else if (board[i][1].getOwner() != null) {
                if (board[i][2].getOwner() != null) {
                    if (board[i][1].getOwner().equals(board[i][2].getOwner())) {
                        if (board[i][0].getOwner() == null) {
                            if (board[i][2].getOwner().toString().contains(this.getSymbol())) {
                                return new int[]{i, 0};
                            } else {
                                result[0] = i;
                                result[1] = 0;
                            }
                        }
                    }
                }
            }
            // Check des colonnes
            if (board[0][i].getOwner() != null) {
                if (board[1][i].getOwner() != null) {
                    if (board[0][i].getOwner().equals(board[1][i].getOwner())) {
                        if (board[2][i].getOwner() == null) {
                            if (board[0][i].getOwner().toString().contains(this.getSymbol())) {
                                return new int[]{2, i};
                            } else {
                                result[0] = 2;
                                result[1] = i;
                            }
                        }
                    }
                } else if (board[2][i].getOwner() != null) {
                    if (board[0][i].getOwner().equals(board[2][i].getOwner())) {
                        if (board[1][i].getOwner() == null) {
                            if (board[0][i].getOwner().toString().contains(this.getSymbol())) {
                                return new int[]{1, i};
                            } else {
                                result[0] = 1;
                                result[1] = i;
                            }
                        }
                    }
                }
            } else if (board[1][i].getOwner() != null) {
                if (board[2][i].getOwner() != null) {
                    if (board[1][i].getOwner().equals(board[2][i].getOwner())) {
                        if (board[0][i].getOwner() == null) {
                            if (board[2][i].getOwner().toString().contains(this.getSymbol())) {
                                return new int[]{0, i};
                            } else {
                                result[0] = 0;
                                result[1] = i;
                            }
                        }
                    }
                }
            }
        }
        // Check diagonales
        if (board[1][1].getOwner() != null) {
            if (board[0][0].getOwner() != null) {
                if (board[1][1].getOwner().equals(board[0][0].getOwner())) {
                    if (board[2][2].getOwner() == null) {
                        if (board[1][1].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{2, 2};
                        } else {
                            result[0] = 2;
                            result[1] = 2;
                        }
                    }
                }
            } else if (board[2][2].getOwner() != null) {
                if (board[1][1].getOwner().equals(board[2][2].getOwner())) {
                    if (board[0][0].getOwner() == null) {
                        if (board[1][1].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{0, 0};
                        } else {
                            result[0] = 0;
                            result[1] = 0;
                        }
                    }
                }
            } else if (board[2][0].getOwner() != null) {
                if (board[1][1].getOwner().equals(board[2][0].getOwner())) {
                    if (board[0][2].getOwner() == null) {
                        if (board[1][1].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{0, 2};
                        } else {
                            result[0] = 0;
                            result[1] = 2;
                        }
                    }
                }
            } else if (board[0][2].getOwner() != null) {
                if (board[1][1].getOwner().equals(board[0][2].getOwner())) {
                    if (board[2][0].getOwner() == null) {
                        if (board[1][1].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{2, 0};
                        } else {
                            result[0] = 2;
                            result[1] = 0;
                        }
                    }
                }
            }
        } else if (board[0][0].getOwner() != null) {
            if (board[2][2].getOwner() != null) {
                if (board[0][0].getOwner().equals(board[2][2].getOwner())) {
                    if (board[1][1].getOwner() == null) {
                        if (board[2][2].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{1, 1};
                        } else {
                            result[0] = 1;
                            result[1] = 1;
                        }
                    }
                }
            }
        } else if (board[2][0].getOwner() != null) {
            if (board[0][2].getOwner() != null) {
                if (board[2][0].getOwner().equals(board[0][2].getOwner())) {
                    if (board[1][1].getOwner() == null) {
                        if (board[0][2].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{1, 1};
                        } else {
                            result[0] = 1;
                            result[1] = 1;
                        }
                    }
                }
            }
        } else if (board[0][2].getOwner() != null) {
            if (board[2][0].getOwner() != null) {
                if (board[0][2].getOwner().equals(board[2][0].getOwner())) {
                    if (board[1][1].getOwner() == null) {
                        if (board[0][2].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{1, 1};
                        } else {
                            result[0] = 1;
                            result[1] = 1;
                        }
                    }
                }
            }
        } else if (board[2][2].getOwner() != null) {
            if (board[0][0].getOwner() != null) {
                if (board[2][2].getOwner().equals(board[0][0].getOwner())) {
                    if (board[1][1].getOwner() == null) {
                        if (board[2][2].getOwner().toString().contains(this.getSymbol())) {
                            return new int[]{1, 1};
                        } else {
                            result[0] = 1;
                            result[1] = 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    private int[] generateRandomPosition(Cell[][] board) {
        int position = -1, coordonneeX = -1, coordonneeY = -1;
        do {
            coordonneeX = (int) (Math.random() * 3);
            coordonneeY = (int) (Math.random() * 3);
            //Transforme coordonnée 2D en 1D
            if(board[coordonneeX][coordonneeY].getOwner() == null) {
                return new int[]{coordonneeX, coordonneeY};
            }
        } while (true);
    }

}
