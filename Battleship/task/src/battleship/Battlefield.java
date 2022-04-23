package battleship;

import java.util.ArrayList;

public class Battlefield {
    private int size;
    char[][] field;
    public ArrayList<Ship> ships = new ArrayList<>();

    public Battlefield(int size) {
        this.size = size;
        this.field = new char[size + 1][size + 1];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /***
     * Fill empty field by a '~'.
     */
    public void fillTheField() {
        for (int i = 1; i < field.length; i++) {
            field[0][i] = (char)((i - 1) + '0');
        }

        for (int i = 1; i < field.length; i++) {
            field[i][0] = (char)('A' + (i - 1));
        }

        for (int i = 1; i < field.length; i++) {
            for (int j = 1; j < field.length; j++) {
                field[i][j] = '~';
            }
        }

        field[0][0] = ' ';
    }

    public void printCurrentField() {
        String firstRow = "  1 2 3 4 5 6 7 8 9 10";
        System.out.println(firstRow);
        for (int i = 1; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void fillOneCell(String coordinate, char missOrHit) {
        int row = coordinate.charAt(0) - 'A' + 1;
        int column = Integer.parseInt(coordinate.substring(1));

        this.field[row][column] = missOrHit;
    }

    /***
     * Check ship for sank.
     * @return true if ship is sank, false if not.
     */
    public boolean isShipSank(Ship ships) {

        String[] splitCoordinates = ships.getCoordinates().split(" ");
        int number1 = Integer.parseInt(splitCoordinates[0].substring(1));
        int number2 = Integer.parseInt(splitCoordinates[1].substring(1));
        char character1 = splitCoordinates[0].charAt(0);
        char character2 = splitCoordinates[1].charAt(0);

        if (character1 == character2) {
            if (Math.abs(number2 - number1) == ships.getLength() - 1) {
                for (int i = Math.min(number1, number2); i <= Math.max(number2, number1); i++) {
                    if (field[character1 - 'A' + 1][i] == 'O') return false;
                }
            }
        } else if (number1 == number2) {
            if (Math.abs(character1 - character2) == ships.getLength() - 1) {
                for (int i = Math.min(character1, character2) - 'A' + 1; i <= Math.max(character2, character1) - 'A' + 1; i++) {
                    if (field[i][number1] == 'O') return false;
                }
            }
        }

        return true;
    }

    public void fillArrayListByShips() {
        ships.add(new AircraftCarrier());
        ships.add(new Battleship());
        ships.add(new Submarine());
        ships.add(new Cruiser());
        ships.add(new Destroyer());
    }
}

