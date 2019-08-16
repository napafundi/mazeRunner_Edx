import java.util.Scanner;

public class mazeRunner {
    public static Maze myMap = new Maze();
    public static Scanner scan = new Scanner(System.in);
    public static int movesMade = 0;
    public static String wallHit = "You've hit a wall! Try again";

    public static void intro() {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        myMap.printMap();
    }

    public static void userMove() {
        System.out.println("Where would you like to move? (R, L, U, D) ");
        String move = scan.nextLine();
        while (!"RLUD".contains(move)) {
            System.out.println("Where would you like to move? (R, L, U, D) ");
            move = scan.nextLine();
        }
        if (myMap.isThereAPit(move)) {
            navigatePit(move);
        } else if (move.equals("R")) {
            if (myMap.canIMoveRight()) {
                myMap.moveRight();
            } else {
                System.out.println(wallHit);
            }
        } else if (move.equals("L")) {
            if (myMap.canIMoveLeft()) {
                myMap.moveLeft();
            } else {
                System.out.println(wallHit);
            }
        } else if (move.equals("D")) {
            if (myMap.canIMoveDown()) {
                myMap.moveDown();
            } else {
                System.out.println(wallHit);
            }
        } else if (move.equals("U")) {
            if (myMap.canIMoveUp()) {
                myMap.moveUp();
            } else {
                System.out.println(wallHit);
            }
        }
        movesMade++;
        System.out.println(movesLeft(movesMade));
        myMap.printMap();
    }

    public static void navigatePit(String move) {
        System.out.println("Watch out! There's a pit ahead, jump it? (start your sentence with 'y' for yes) ");
        String jump = scan.nextLine();
        if (jump.charAt(0) == 'y') {
            myMap.jumpOverPit(move);
        }
    }

    public static String movesLeft(int moves) {
        if (moves == 50) {
            return "Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.";
        } else if (moves == 75) {
            return "Alert! You have made 75 moves, you only have 25 moves left to escape.";
        } else if (moves == 90) {
            return "DANGER! You have made 90 moves, you only ave 10 moves left to escape.";
        } else if (moves == 100) {
            return "You're really getting up there in moves, good luck!";
        }
        return "";
    }

    public static void main(String[] args) {
        intro();
        while (!myMap.didIWin()) {
            userMove();
        }
        scan.close();
        System.out.println("Congratulations! You won in " + movesMade + " moves!");
    }
}
