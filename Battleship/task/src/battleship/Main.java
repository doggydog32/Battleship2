package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        // Write your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Battlefield battlefield1 = new Battlefield(10);
        Battlefield battlefield2 = new Battlefield(10);
        battlefield1.fillArrayListByShips();
        battlefield2.fillArrayListByShips();

        Battlefield hiddenField1 = new Battlefield(10);
        Battlefield hiddenField2 = new Battlefield(10);

        Player player1 = new Player("Player 1", true, battlefield1);
        Player player2 = new Player("Player 2", false, battlefield2);
        battlefield1.fillTheField();
        battlefield2.fillTheField();
        hiddenField1.fillTheField();
        hiddenField2.fillTheField();

        //Fill the field by ships of each player
        System.out.println(player1.getName() + ", place your ships on the game field");
        battlefield1.printCurrentField();
        fillTheFieldByShips(player1, battlefield1);
        promptEnterKey();
        System.out.println("...");
        System.out.println(player2.getName() + ", place your ships on the game field");
        battlefield2.printCurrentField();
        fillTheFieldByShips(player2, battlefield2);
        promptEnterKey();
        System.out.println("...");

        while(!battlefield1.ships.isEmpty() || !battlefield2.ships.isEmpty()) {
            if (player1.isYourTurn()) {
                printPlayField(battlefield1, hiddenField2);
                System.out.println("Player 1, it's your turn:");
                startGame(player2, battlefield2, hiddenField2);
                player1.setYourTurn(false);
                player2.setYourTurn(true);
            } else {
                printPlayField(battlefield2, hiddenField1);
                System.out.println("Player 2, it's your turn:");
                startGame(player1, battlefield1, hiddenField1);
                player2.setYourTurn(false);
                player1.setYourTurn(true);
            }
        }

        System.out.println("You sank the last ship. You won. Congratulations!");
        /*
        if (battlefield1.ships.isEmpty()){
            System.out.println("Player 2 sank the last ship. Player 2 won. Congratulations!");
        } else {
            System.out.println("Player 1 sank the last ship. Player 1 won. Congratulations!");
        }
        */





    }

    /***
     * First stage of game: fill the field by ships and printing that field.
     * @param player Player that makes the turn;
     * @param battlefield Object of Battlefield to manipulate field;
     */
    public static void fillTheFieldByShips(Player player, Battlefield battlefield) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (Ship x: battlefield.ships) {
            System.out.println("Enter the coordinates of the " + x.getName() + " (" + x.getLength() + " cells):");
            String input;
            boolean isCorrect;

            do {
                input = reader.readLine();
                isCorrect = player.isCorrectMove(input, battlefield.field, x);
                player.putShipIntoField(input, x, isCorrect);
            }
            while (!isCorrect);

            battlefield.printCurrentField();
        }

    }

    public static void startGame(Player player, Battlefield battlefield, Battlefield hiddenField) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isCorrect;
        String input;
        boolean isSank = false;

        do {
            input = reader.readLine();
            isCorrect = player.shotIsCorrect(input);
            if (!isCorrect) continue;

            boolean isHit = player.hitOrMissShot(input, battlefield.field, true);
            if (isHit) {
                hiddenField.fillOneCell(input, 'X');

                for (Ship x : battlefield.ships) {
                    if (battlefield.isShipSank(x)) {
                        battlefield.ships.remove(x);

                        if(battlefield.ships.isEmpty()) {
                            isSank = true;
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            break;
                        }

                        isSank = true;
                        System.out.println("You sank a ship!");
                        promptEnterKey();
                        System.out.println("...");
                        break;
                    }
                }
                if (!isSank) {
                    System.out.println("You hit a ship!");
                    promptEnterKey();
                    System.out.println("...");
                }

                } else {
                    hiddenField.fillOneCell(input, 'M');
                    System.out.println("You missed.");
                    promptEnterKey();
                    System.out.println("...");
                }
            } while (!isCorrect);

    }

    public static void printPlayField(Battlefield battlefield, Battlefield hiddenField) {
        hiddenField.printCurrentField();
        System.out.println("---------------------");
        battlefield.printCurrentField();
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
