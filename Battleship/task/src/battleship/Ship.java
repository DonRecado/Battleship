package battleship;

public class Ship {
    private int[] firstCoordinate;
    private int[] secondCoordinate;
    private boolean horizontal;
    private ShipTypes type;
    private int lives;
    private int[][] allCoordinates;

    public Ship(int[] firstCoordinate, int[] secondCoordinate, boolean horizontal, ShipTypes type) {
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
        this.horizontal = horizontal;
        this.type = type;
        this.lives = type.getCells();
        this.allCoordinates = new int[type.getCells()][2];
        setAllCoordinates(firstCoordinate, secondCoordinate, horizontal, type);
    }

    public int[][] getAllCoordinates() {
        return allCoordinates;
    }

    public ShipTypes getType() {
        return type;
    }

    public int[] getFirstCoordinate() {
        return firstCoordinate;
    }

    public int[] getSecondCoordinate() {
        return secondCoordinate;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int increaseHits() {
        if (this.lives - 1 >= 0) {
            this.lives--;

            return lives;
        }
        return -1;
    }

    private void setAllCoordinates(int[] firstCoordinate, int[] secondCoordinate, boolean horizontal, ShipTypes type) {

        if (horizontal) {
            int row = firstCoordinate[0];
            int start = firstCoordinate[1];
            for (int i = 0; i < allCoordinates.length; i++) {
                allCoordinates[i] = new int[]{row, start};
                start++;
            }
        } else {
            int col = firstCoordinate[1];
            int start = firstCoordinate[0];

            for (int i = 0; i < allCoordinates.length; i++) {
                allCoordinates[i] = new int[]{start, col};
                start++;
            }
        }
    }

}
