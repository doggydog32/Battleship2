package battleship;

public class Player {
    private String name;
    private boolean isYourTurn;
    public Battlefield playField;

    public Player(String name, boolean isYourTurn, Battlefield playField1) {
        this.name = name;
        this.isYourTurn = isYourTurn;
        playField = playField1;
    }

    public String getName() {
        return name;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        isYourTurn = yourTurn;
    }

    /***
     * Put new ship on field if turn is correct.
     * @param input User enter two coordinates separated by space;
     * @param ships
     * @param isCorrect
     */
    public void putShipIntoField(String input, Ship ships, boolean isCorrect) {
        if (!isCorrect) return;

        ships.setCoordinates(input);

        String[] inputSplit = input.split(" ");
        int number1 = Integer.parseInt(inputSplit[0].substring(1));
        int number2 = Integer.parseInt(inputSplit[1].substring(1));
        char character1 = inputSplit[0].charAt(0);
        char character2 = inputSplit[1].charAt(0);

        if (character1 == character2) {
            if (Math.abs(number2 - number1) == ships.getLength() - 1) {
                for (int i = Math.min(number1, number2); i <= Math.max(number2, number1); i++) {
                    playField.field[character1 - 'A' + 1][i] = 'O';
                }
            }
        } else if (number1 == number2) {
            if (Math.abs(character1 - character2) == ships.getLength() - 1) {
                for (int i = Math.min(character1, character2) - 'A' + 1; i <= Math.max(character2, character1) - 'A' + 1; i++) {
                    playField.field[i][number1] = 'O';
                }
            }
        }
    }

    /***
     * Checking for a correct turn according the rules.
     * @param input User enter two coordinates separated by space;
     * @param field Enter the GameField;
     * @param ships
     * @return true if entered coordinates is correct.
     */
    public boolean isCorrectMove(String input,char[][] field, Ship ships) {
        String[] inputSplit = input.split(" ");
        int number1 = Integer.parseInt(inputSplit[0].substring(1));
        int number2 = Integer.parseInt(inputSplit[1].substring(1));
        char character1 = inputSplit[0].charAt(0);
        char character2 = inputSplit[1].charAt(0);

        if (inputSplit.length != 2) {
            System.out.println("Error! Input is incorrect! Try again:");
            return false;
        }

        if (character1 != character2 && number1 != number2) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        if (Math.abs(number2 - number1) != ships.getLength() - 1 && (Math.abs(character1 - character2) != ships.getLength() - 1)) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        }

        for (int i = Math.min(character1, character2) - 'A' + 1; i <= Math.max(character1, character2) - 'A' + 1; i++) {
            for (int j = Math.min(number1, number2); j <= Math.max(number1, number2); j++) {
                String index = String.valueOf(i) + String.valueOf(j);

                //Check corners
                switch(index) {
                    case "11":
                        if (field[i + 1][j] == 'O' || field[i][j+1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                        return true;
                    case "110":
                        if (field[i + 1][j] == 'O' || field[i][j-1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                        return true;
                    case "101":
                        if (field[i - 1][j] == 'O' || field[i][j+1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                        return true;
                    case "1010":
                        if (field[i - 1][j] == 'O' || field[i][j-1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                        return true;
                    default:
                        switch (j) {
                            case 1:
                                if (field[i + 1][j] == 'O' || field[i-1][j] == 'O' || field[i][j+1] =='O') {
                                    System.out.println("Error! You placed it too close to another one. Try again:");
                                    return false;
                                }
                                return true;
                            case 10:
                                if (field[i + 1][j] == 'O' || field[i-1][j] == 'O' || field[i][j-1] =='O') {
                                    System.out.println("Error! You placed it too close to another one. Try again:");
                                    return false;
                                }
                                return true;
                        }

                        switch(i) {
                            case 1:
                                if (field[i + 1][j] == 'O' || field[i][j+1] == 'O' || field[i][j-1] =='O') {
                                    System.out.println("Error! You placed it too close to another one. Try again:");
                                    return false;
                                }
                                return true;
                            case 10:
                                if (field[i - 1][j] == 'O' || field[i][j+1] == 'O' || field[i][j-1] =='O') {
                                    System.out.println("Error! You placed it too close to another one. Try again:");
                                    return false;
                                }
                                return true;
                        }

                        if (field[i + 1][j] == 'O' || field[i][j+1] =='O' || field[i-1][j] == 'O' || field[i][j-1] =='O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            return false;
                        }
                }


            }
        }

        return true;
    }

    /***
     * Player choose a cell and make a shot on it.
     * @param coordinate User enter coordinate of a cell;
     * @param field current game field;
     * @param isCorrect make shot if true;
     * @return true if shot hit the ship and false if shot was missed.
     */
    public boolean hitOrMissShot(String coordinate, char[][] field, boolean isCorrect) {
        if(!isCorrect) {
            return false;
        }

        char row = coordinate.charAt(0);
        int column = Integer.parseInt(coordinate.substring(1));

        if (field[row - 'A' + 1][column] == 'O' || field[row - 'A' + 1][column] == 'X') {
            field[row - 'A' + 1][column] = 'X';
            return true;
        } else {
            field[row - 'A' + 1][column] = 'M';
            return false;
        }
    }

    /***
     * Check for a correct coordinate to make shot.
     * @param coordinate coordinate to check;
     * @return True or false.
     */
    public boolean shotIsCorrect(String coordinate) {
        char row = coordinate.charAt(0);
        int column = Integer.parseInt(coordinate.substring(1));

        if ((row - 'A' + 1 < 1 || row  - 'A' + 1 > 10)) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }

        if(column < 1 || column > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        }

        return true;
    }
}
