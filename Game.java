import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner sn = new Scanner(System.in);
    private Scanner scan = new Scanner(System.in);
    private Random rand = new Random();
    private Player p1 = new Player();
    private Player p2 = new Player();
    private boolean gameOver = false;
    private boolean computer = false;
    private int starting = 0;
    private int pileSize = 20;
    
    // Constructor
    public Game() {
        start();
        pileSize = Board.getPileSize();
        play();
        
    }
    // Select the winner and display scores
    public void Winner(int player){
        if (player == 1){
            System.out.println(p1.getName() + " wins");
            p1.addScore();
        }
        else {
            System.out.println(p2.getName() + " wins");
            p2.addScore();
        }
        System.out.println(p1.getName() + " has won " + p1.getScore() + " time(s)");
        System.out.println(p2.getName() + " has won " + p2.getScore() + " time(s)");
        // Receive user input to decide if they want to play again
        System.out.println("Do you guys want to replay? (Y/N)");
        String in = scan.nextLine();
        in = in.toUpperCase();
        if (in.equals("Y")){
            pileSize = Board.populate();
            play();
        } else if (in.equals("N")) {
            System.out.println("Thank you for playing");
            gameOver = true;
        }
    }
    
    // While loop that runs the game until the pilesize is 0 and the player no longer wants to play anymore. Main loop
    public void play() {
        System.out.println("Welcome to the game of Nim. Nim is a game of strategy where either yourself and a player or a computer take turns taking sticks away from a pile. "+ 
        "The goal of the game is to be the last person to pickup a stick. Follow the instructions diplayed on the terminal as you play. Commands from the terminal will include: " + 
        "Choose an amount of sticks to remove, Choose too many pieces, please try again");
        goFirst();
        while (pileSize > 0 && gameOver == false){
            // Player 1 Moves
            if (starting == 1) {
                System.out.println(p1.getName() + " choose an amount of sticks to remove");
                removePieces(sn.nextInt(), starting);
            } 
            // Player 2 Moves
            else {
                System.out.println(p2.getName() + " choose an amount of sticks to remove");
                if (computer) {
                    removePieces(p2.dumbCPUMove(pileSize), starting);
                }
                else {
                    removePieces(sn.nextInt(), starting);
                }

            }

        }

    }
    // Select who gets the first turn of the game.
    public void goFirst() {
        int start = rand.nextInt(0, 2);
        if (start == 1){
            System.out.println(p1.getName() + " goes first");
            starting = 1;
        } else {
            System.out.println(p2.getName() + " goes first");
            starting = 2;
        }
    }

    // Remove pieces from pile based on user input and show error message if number is invalid.
    public void removePieces(int num, int player){
        if ((num <= (pileSize / 2)) && num > 0){
            pileSize -= num;
            if (pileSize == 0) {
                Winner(player);
            }
            else if (player == 1){
                p1.addPile(num);
                starting = 2;
                displayBoard();
            }
            else {
                p2.addPile(num);
                starting = 1;
                displayBoard();
            }
        }
        else if (pileSize == 1 && num == 1){
            pileSize -= num;
            Winner(player);
        }
            

        else{
            System.out.println("Choose too many pieces, please try again");
            removePieces(sn.nextInt(), player);
        }
    }
    // Show pile size and amount of sticks in each players pile.
    public void displayBoard() {
        System.out.println("There are " + pileSize + " sticks left in the pile");
        System.out.println(p1.getName() + " has " + p1.getPile() + " sticks");
        System.out.println(p2.getName() + " has " + p2.getPile() + " sticks");
    }

    // Enter in names - begin the game.
    private void start() {
        System.out.println("Player 1 enter your name:");
        p1.setName(scan.nextLine());
        if (playerOrComputer()) {
            p2.dumbCPU();
        } 
        else {
            System.out.println("Player 2 enter your name:");
            p2.setName(scan.nextLine());
        }

    }
    // Loop to recieve player input to decide whether they want to play against another player or a computer
    private boolean playerOrComputer() {
        System.out.println("Do you want to play against a player or a computer (P/C)");
        String input = scan.nextLine().toUpperCase();
        if (input.equals("P")){
            return false;
        } 
        else if (input.equals("C")) {
            computer = true;
            return true;
        }
        else {
            System.out.println("Invalid input try again!");
            playerOrComputer();
        }
        return false;
    }

}
