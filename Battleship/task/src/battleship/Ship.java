package battleship;

public abstract class Ship {
    int length;
    String coordinates;
    String name;

    public Ship(String name, int length) {
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
