package battleship;

enum ShipTypes {
    AIRCRAFT_CARRIER(1, 5, "Aircraft Carrier"),
    BATTLESHIP(2, 4, "Battleship"),
    SUBMARINE(3, 3, "Submarine"),
    CRUISER(4, 3, "Cruiser"),
    DESTROYER(5, 2, "Destroyer ");

    int type;
    int cells;
    String slug;

    ShipTypes(int type, int cells, String slug) {
        this.type = type;
        this.cells = cells;
        this.slug = slug;
    }

    public int getCells() {
        return cells;
    }

    public String getSlug() {
        return slug;
    }

    public static ShipTypes findByType(int type) {
        for (ShipTypes s : ShipTypes.values()) {
            if (s.type == type) {
                return s;
            }
        }
        return null;
    }

}
