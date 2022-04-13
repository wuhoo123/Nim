import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner sn = new Scanner(System.in);
    private Scanner scan = new Scanner(System.in);
    private Random rand = new Random();
    private Player p1 = new Player();
    private Player p2 = new Player();
    private boolean gameOver = false;
    private int starting = 0;
    private int pileSize = 20;
    
    // Constructor
    public Game() {
        start();
        pileSize = Board.populate();
        play();
        
    }
    // Select the winner and display scores
    public void Winner(int player){
        if (player == 1){
            System.out.println(p1.getName() + " wins");
            p1.addScore();
            System.out.println(p1.getName() + " has won " + p1.getScore() + " times");
            System.out.println(p2.getName() + " has won " + p2.getScore() + " times");
        }
        else {
            System.out.println(p2.getName() + " wins");
            p2.addScore();
            System.out.println(p2.getName() + " has won " + p2.getScore() + " times");
            System.out.println(p1.getName() + " has won " + p1.getScore() + " times");
        }

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
                removePieces(sn.nextInt(), starting);
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
        if (num <= (Board.getPileSize() / 2)){
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
        System.out.println("Player 2 enter your name:");
        p2.setName(scan.nextLine());
    }

}
