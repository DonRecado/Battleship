package battleship;

import java.util.Scanner;

public class GameController {
    private final Player[] gamePlayers = new Player[2];
    private final Scanner scanner;
    private final int shipsPerPlayer = 5;

    public GameController(Scanner scanner) {
        gamePlayers[0] = new Player();
        gamePlayers[1] = new Player();
        this.scanner = scanner;
        setOpponents();
    }

    public void startGame() {
        Player firstPlayer = gamePlayers[0];
        Player secondPlayer = gamePlayers[1];

        Player currentPlayer = firstPlayer;

//        Define Ships
        while (true) {
            System.out.println("Player " + currentPlayer.getPlayerNumber() + ", place your ships on the game field\n");
            defineShips(currentPlayer);

            int size = 0;

            for (Player player : this.gamePlayers) {
                size += player.getShipListSize();
            }

            if (size == this.shipsPerPlayer * this.gamePlayers.length) {
                break;
            } else {
                while (true) {
                    System.out.println("\nPress Enter and pass the move to another player ...");
                    String switchPlayer = scanner.nextLine();
                    if (switchPlayer.equals("")) {
                        currentPlayer = currentPlayer.getOpponent();
                        break;
                    }
                }

            }
        }

        while (true) {
            System.out.println("\nPress Enter and pass the move to another player ...");
            String switchPlayer = scanner.nextLine();
            if (switchPlayer.equals("")) {
                currentPlayer = currentPlayer.getOpponent();
                break;
            }
        }

//        Shoot

        boolean gameOver = false;

        while (!gameOver) {
            printGameField(currentPlayer);
            System.out.println("\n Payer " + currentPlayer.getPlayerNumber() + " it's your turn:\n");
            String coordinate = scanner.nextLine();
            gameOver = this.shoot(currentPlayer, coordinate);

            if (!gameOver) {
                while (true) {
                    System.out.println("\nPress Enter and pass the move to another player ...");
                    String switchPlayer = scanner.nextLine();
                    if (switchPlayer.equals("")) {
                        currentPlayer = currentPlayer.getOpponent();
                        break;
                    }
                }
            }
        }

    }

    private boolean shoot(Player currentPlayer, String coordinate) {
        int row;
        int col;
        boolean hasSank = false;
        boolean gameOver = false;
        boolean wrongCoordinate = false;

        while (true) {
            if (wrongCoordinate) {
                coordinate = scanner.next();
            }


            if (coordinate.length() == 2 || coordinate.length() == 3) {
                if (getCoordinate(coordinate.charAt(0)) != -1) {
                    row = getCoordinate(coordinate.charAt(0));
                    try {
                        col = Integer.parseInt(coordinate.substring(1)) - 1;
                        if (row >= 0 && row <= 9 && col >= 0 && col <= 9) {
                            boolean hit = currentPlayer.getOpponent().hitShip(row, col);
                            if (hit) {
                                Ship currentShip = currentPlayer.getOpponent().hasSank(row, col);
                                hasSank = currentShip != null;
                                gameOver = currentPlayer.getOpponent().hasLost(currentShip);
                                if (gameOver) {
                                    this.printGameField(currentPlayer);
                                    System.out.println("\nYou sank the last ship. You won. Congratulations!");
                                    hasSank = false;
                                }
                            }

                            if (hasSank) {
                                System.out.println("\nYou sank a ship!\n");
                            } else {
                                System.out.println(hit ? "\nYou hit a ship!" : "\nYou missed!");
                            }
                            break;
                        } else {
                            printErrors("wrongCoordinate", null);
                            wrongCoordinate = true;
                        }
                    } catch (NumberFormatException e) {
                        printErrors("wrongCoordinate", null);
                        wrongCoordinate = true;
                    }
                } else {
                    printErrors("wrongCoordinate", null);
                    wrongCoordinate = true;
                }

            } else {
                printErrors("wrongCoordinate", null);
                wrongCoordinate = true;
            }
        }

        return gameOver;


    }

    private void defineShips(Player currentPlayer) {
        int shipCounter = 1;
        boolean error = false;
        currentPlayer.printPlayField();

        while (shipCounter <= this.shipsPerPlayer) {
            if (!error) {
                String msg = "";
                switch (shipCounter) {
                    case 1:
                        msg = "\nEnter the coordinates of the Aircraft Carrier (5 cells):\n";
                        break;
                    case 2:
                        msg = "\nEnter the coordinates of the Battleship (4 cells):\n";
                        break;
                    case 3:
                        msg = "\nEnter the coordinates of the Submarine (3 cells):\n";
                        break;
                    case 4:
                        msg = "\nEnter the coordinates of the Cruiser (3 cells):\n";
                        break;
                    case 5:
                        msg = "\nEnter the coordinates of the Destroyer (2 cells):\n";
                        break;
                }

                System.out.println(msg);
            }

            String[] inputArray = this.scanner.nextLine().trim().split(" ");
            Ship ship = validateInput(inputArray, shipCounter);
            if (ship != null) {
                boolean isAdded;
                try {
                    isAdded = currentPlayer.addShip(ship);
                    if (isAdded) {
                        shipCounter++;
                        currentPlayer.printPlayField();
                    } else {
                        printErrors("toClose", ship.getType());
                        ship = null;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error");
                    ship = null;
                }


            }
            error = ship == null;
        }
    }

    private void setOpponents() {
        gamePlayers[0].setOpponent(gamePlayers[1]);
        gamePlayers[1].setOpponent(gamePlayers[0]);
    }

    private Ship validateInput(String[] strArray, int type) {

        Boolean horizontal = null;
        int[] firstCoordinate;
        int[] secondCoordinate;
        int first;
        int second;
        ShipTypes shipType = ShipTypes.findByType(type);

        if (strArray.length == 2 && shipType != null) {
            if (strArray[0].charAt(0) >= 65 && strArray[1].charAt(0) <= 74) { // Chars are right
                char firstChar = strArray[0].charAt(0);
                char secondChar = strArray[1].charAt(0);
                try {
                    first = Integer.parseInt(strArray[0].substring(1)) - 1;
                    second = Integer.parseInt(strArray[1].substring(1)) - 1;
                } catch (NumberFormatException e) {
                    return null;
                }

                firstCoordinate = new int[2];
                firstCoordinate[0] = getCoordinate(firstChar);
                firstCoordinate[1] = first;

                secondCoordinate = new int[2];
                secondCoordinate[0] = getCoordinate(secondChar);
                secondCoordinate[1] = second;

                if (firstChar == secondChar) {
                    horizontal = true;
                } else if (first == second) {
                    horizontal = false;
                } else {
                    printErrors("location", shipType);
                }

                if (horizontal != null) {
                    if (second - first == shipType.getCells() - 1 || secondChar - firstChar == shipType.getCells() - 1) {
                        return new Ship(firstCoordinate, secondCoordinate, horizontal, shipType);
                    } else if (first - second == shipType.getCells() - 1 || firstChar - secondChar == shipType.getCells() - 1) {
                        return new Ship(secondCoordinate, firstCoordinate, horizontal, shipType);
                    } else {
                        printErrors("length", shipType);
                    }
                }
            }
        } else {
            printErrors("location", shipType);
        }

        return null;
    }

    private int getCoordinate(char x) {
        int count = 0;
        for (char i = 'A'; i <= 'J'; i++) {
            if (x == i) {
                return count;
            }
            count++;
        }
        return -1;
    }

    private void printErrors(String err, ShipTypes type) {
        switch (err) {
            case "length":
                System.out.println("\nError! Wrong length of the " + type.getSlug() + "! Try again:\n");
                break;
            case "location":
                System.out.println("\nError! Wrong ship location! Try again:\n");
                break;
            case "toClose":
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
                break;
            case "wrongCoordinate":
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
        }
    }

    private void printGameField(Player currentPlayer) {
        currentPlayer.printShootField();
        System.out.println("---------------------");
        currentPlayer.printPlayField();
    }


}
