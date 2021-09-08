package battleship;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
//       Player player1 = new Player();
//       Player player2 = new Player();
        final Scanner scanner = new Scanner(System.in);

//        Player[] players = new Player[]{new Player(), new Player()};
//
        GameController game = new GameController(scanner);

        game.startGame();

    }


}
