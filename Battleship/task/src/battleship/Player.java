package battleship;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private static int increasePlayerNumber = 1;
    private Player opponent;
    private final int playerNumber;
    private final ArrayList<Ship> shipList = new ArrayList<>();
    private final char[][] playField;
    private final char[][] shootField;

    public Player() {
        this.playerNumber = increasePlayerNumber++;
        this.playField = createPlayField();
        this.shootField = createPlayField();
    }

    public Player getOpponent() {
        return opponent;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getShipListSize() {
        return this.shipList.size();
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public boolean addShip(Ship ship) {

//        Check if there are ships nearby
        if (this.shipList.size() > 0) {
            int startRow = (ship.getFirstCoordinate()[0] - 1 < 0) ? ship.getFirstCoordinate()[0] : ship.getFirstCoordinate()[0] - 1;
            int startCol = (ship.getFirstCoordinate()[1] - 1 < 0) ? ship.getFirstCoordinate()[1] : ship.getFirstCoordinate()[1] - 1;
            int endRow = (ship.getSecondCoordinate()[0] + 1 > 9) ? ship.getSecondCoordinate()[0] : ship.getSecondCoordinate()[0] + 1;
            int endCol = (ship.getSecondCoordinate()[1] + 1 > 9) ? ship.getSecondCoordinate()[1] : ship.getSecondCoordinate()[1] + 1;

            for (int i = startRow; i <= endRow; i++) {
                for (int j = startCol; j <= endCol; j++) {
                    if (this.playField[i][j] == 'O') {
                        return false;
                    }
                }
            }
        }

        if (ship.isHorizontal()) {
            for (int i = ship.getFirstCoordinate()[1]; i <= ship.getSecondCoordinate()[1]; i++) {
                this.playField[ship.getSecondCoordinate()[0]][i] = 'O';
            }
        } else {
            for (int i = ship.getFirstCoordinate()[0]; i <= ship.getSecondCoordinate()[0]; i++) {
                this.playField[i][ship.getFirstCoordinate()[1]] = 'O';
            }
        }
        this.shipList.add(ship);
        return true;
    }

    /***************** PRINT OUTS *****************/
    public void printPlayField() {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : playField) {
            System.out.print(letter + " ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
            letter++;
        }
    }

    public void printShootField() {
        char letter = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] chars : shootField) {
            System.out.print(letter + " ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
            letter++;
        }
    }

    private char[][] createPlayField() {
        char[][] battleField = new char[10][10];
        for (char[] chars : battleField) {
            Arrays.fill(chars, '~');
        }
        return battleField;
    }

    private void sankShip() {
        System.out.println("You sank a ship! Specify a new target:");
    }

    /***************** Validation *****************/

    public boolean hitShip(int row, int col) {
        if (this.playField[row][col] == 'O') {
            int[] coordinates = new int[]{row, col};
            for (Ship ship : this.shipList) {
                int[][] allCoordinates = ship.getAllCoordinates();
                for (int[] allCoordinate : allCoordinates) {
                    if (allCoordinate == coordinates) {
                        if (ship.increaseHits() == 0) {
                            sankShip();
                        }
                    }
                }
            }


            this.opponent.shootField[row][col] = 'X';
            this.playField[row][col] = 'X';
            return true;
        } else if (this.playField[row][col] == 'X') {
            return true;
        }
        this.playField[row][col] = 'M';
        this.opponent.shootField[row][col] = 'M';
        return false;
    }

    public Ship hasSank(int row, int col) {
        for (Ship ship : this.shipList) {
            for (int i = 0; i < ship.getAllCoordinates().length; i++) {
                if (Arrays.equals(ship.getAllCoordinates()[i], new int[]{row, col})) {
                    int sank = ship.increaseHits();
                    if (sank == 0) {
                        return ship;
                    }
                    return null;
                }
            }
        }
        return null;
    }

    public boolean hasLost(Ship ship) {
        if (ship != null) {
            this.shipList.remove(ship);
            return this.shipList.size() == 0;
        }
        return false;
    }


}
