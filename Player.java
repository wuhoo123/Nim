public class Player {
    private int pile = 0;
    private String name = "";
    private int score = 0;
    
    // Sets the name of the player
    public void setName(String playerName){
        name = playerName;
    }
    // Returns the name of the player
    public String getName(){
        return name;
    }
    // Adds a number of stiks to the player's pile
    public void addPile(int pileSize){
        pile += pileSize;
    }
    // Returns the number of sticks in the player's pile.
    public int getPile(){
        return pile;
    }
    //  Sets a new pile size
    public void setPile(int newPile){
        pile = newPile;
    }
    // Adds 1 to the score of the player
    public void addScore(){
        score += 1;
    }
    // Returns the score of the player
    public int getScore(){
        return score;
    }
    public void CPU(){
    
    }
}

    