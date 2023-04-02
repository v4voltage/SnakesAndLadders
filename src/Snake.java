public class Snake {
    private int snakeID; //the serial number of the snake
    private int headID;  //the tile number that the head of the snake is on
    private int tailID;  //the tile number that the tail of the snake is on

    /**
     * The empty constructor of the class
     */
    public Snake() {
        //empty
    }

    /**
     * This function creates a snake with the given values
     *
     * @param snakeID the serial number of the snake
     * @param headID  the tile number that the head of the snake is on
     * @param tailID  the tile number that the tail of the snake is on
     */
    public Snake(int snakeID, int headID, int tailID) {
        this.snakeID = snakeID;
        this.headID = headID;
        this.tailID = tailID;
    }

    /**
     * This function creates a snake that is a copy of the given snake
     *
     * @param snake is an object of type Snake
     */
    public Snake(Snake snake) {
        this.snakeID = snake.getSnakeID();
        this.headID = snake.getHeadID();
        this.tailID = snake.getTailID();
    }


    /**
     * Getters and Setters for the variables of each snake
     */
    public int getHeadID() { return headID; }
    public int getSnakeID() { return snakeID; }
    public int getTailID() { return tailID; }
    public void setHeadID(int headID) { this.headID = headID; }
    public void setSnakeID(int snakeID) { this.snakeID = snakeID; }
    public void setTailID(int tailID) { this.tailID = tailID; }
}
