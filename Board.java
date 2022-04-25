import java.util.Random;

public class Board {
    private static int pile = 0;
    private static Random rand = new Random();

    // Chooses a random number to populate the board.
    public static int populate() {
        pile = rand.nextInt(10, 51);
        System.out.println("The pile has " + pile + " pieces");
        return pile;
    }
    // Reduces the pile size based on the parameter passed in.
    public static void reducePileSize(int num){
        pile -= num;
    }
    // Returns the pile size.
    public static int getPileSize(){
        return pile;
    }
}
