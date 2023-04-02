public class Player {
    private int playerID; //the serial number of the player
    private String name;  //the name of the player
    private int score;    //the score of the player
    private Board board;  //the board that the player is playing on

    /**
     * The empty constructor of the class
     */
    public Player() {
        //empty
    }

    /**
     * This function creates a player with the given values
     *
     * @param playerID the serial number of the player
     * @param name     the name of the player
     * @param board    the board that the player is playing on
     */
    public Player(int playerID, String name, Board board) {
        this.playerID = playerID;
        this.name = name;
        this.board = board;
        this.score = 0;
    }

    /**
     * Getters and Setters for the variables of each player
     */
    public Board getBoard() { return board; }
    public int getPlayerID() { return playerID; }
    public int getScore() { return score; }
    public String getName() { return name; }

    public void setBoard(Board board) { this.board = board; }
    public void setName(String name) { this.name = name; }
    public void setPlayerID(int playerID) { this.playerID = playerID; }
    public void setScore(int score) { this.score = score; }

    /**
     * This function creates and returns an array with the tile that the player is on after the throw of the die,
     * the number of snakes from which the player has been bitten during this turn,
     * the number of ladders that the player has used during this turn,
     * the number of apples that the player has eaten during this turn.
     *
     * @param id  the number of the tile that the player was on before the throw of the die
     * @param die the number of the die (1-6) - the number that the player will move
     * @return    an array with the new state of the player
     */
    public int[] move(int id, int die) {
        //Initialize the array that will be returned
        int[] state = new int[4]; //[new tile id, snakes, ladders, apples]

        //Initialize the values for calculating the new state of the player
        //tempID is used for storing the new tile ID before all the checks for snakes/ladders/apples
        int newID = id + die;
        int tempID = newID;
        int snakeBites = 0;
        int laddersUsed = 0;
        int applesBitten = 0;

        //With this variable there is a check if the player will have to make more than one move
        //i.e. the player has landed on a snake, but at the tail of the snake there is a ladder
        //     which the player will climb
        boolean repeat;

        //Get the tables of the snakes, ladders and apples to know if the player has landed on
        //a tile that has a snake, a ladder or/and an apple
        Snake[] snakes = board.getSnakes();
        Ladder[] ladders = board.getLadders();
        Apple[] apples = board.getApples();

        //Repeat this process while the player has been bitten by a snake or has used a ladder
        do {
            repeat = false;
            //Check if the player has landed on a tile that has a snake
            for (int i = 0; i < snakes.length; i++) {
                if (tempID == snakes[i].getHeadID()) {
                    newID = snakes[i].getTailID();
                    snakeBites++;
                    repeat = true;
                    break;
                }
            }
            //Check if the player has landed on a tile that has a ladder that can be used
            for (int i = 0; i < ladders.length; i++) {
                if (tempID == ladders[i].getDownStepID() && !ladders[i].isBroken()) {
                    newID = ladders[i].getUpStepID();
                    ladders[i].setBroken(true);
                    laddersUsed++;
                    repeat = true;
                    break;
                }
            }
            //Check if the player has landed on a tile that has an apple that can be eaten
            for (int i = 0; i < apples.length; i++) {
                if (tempID == apples[i].getAppleTileID() && apples[i].getPoints() != 0) {
                    score += apples[i].getPoints();
                    apples[i].setPoints(0);
                    applesBitten++;
                    break;
                }
            }
            tempID = newID;
        } while (repeat);

        //[new tile id, snakes, ladders, apples]
        state[0] = newID;
        state[1] = snakeBites;
        state[2] = laddersUsed;
        state[3] = applesBitten;
        return state;
    }
}
