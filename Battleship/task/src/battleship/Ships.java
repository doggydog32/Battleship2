package battleship;

public enum Ships {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER_2("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private String name;
    private int length;
    private String coordinates;


    Ships(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public String getCoordinates() { return coordinates;}

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
