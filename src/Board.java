import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int N;            //the number of  rows   of the board
    private int M;            //the number of columns of the board
    private int[][] tiles;    //array with the tile numbers of the board
    private Snake[] snakes;   //array with the snakes of the board
    private Ladder[] ladders; //array with the ladders of the board
    private Apple[] apples;   //array with the apples of the board

    /**
     * The empty constructor of the class
     */
    public Board() {
        //empty
    }

    /**
     * This function creates a board with the given values
     *
     * @param N             the number of rows    of the board
     * @param M             the number of columns of the board
     * @param snakesNumber  the total number of snakes  that the board has
     * @param laddersNumber the total number of ladders that the board has
     * @param applesNumber  the total number of apples  that the board has
     */
    public Board(int N, int M, int snakesNumber, int laddersNumber, int applesNumber) {
        this.N = N;
        this.M = M;
        this.snakes = new Snake[snakesNumber];
        this.ladders = new Ladder[laddersNumber];
        this.apples = new Apple[applesNumber];
        tiles = new int[N][M];
        createBoard();
    }

    /**
     * This function creates a board that is a copy of the given board
     *
     * @param board is an object of type Board
     */
    public Board(Board board) {
        this.N = board.getN();
        this.M = board.getM();
        setSnakes(board.getSnakes());
        setLadders(board.getLadders());
        setApples(board.getApples());
        setTiles(board.getTiles());
        createBoard();
    }

    /**
     * Getters and Setters for the variables of each board
     */
    public int getM() { return M; }
    public int getN() { return N; }
    public Apple[] getApples() { return apples; }
    public int[][] getTiles() { return tiles; }
    public Ladder[] getLadders() { return ladders; }
    public Snake[] getSnakes() { return snakes; }

    public void setM(int M) { this.M = M; }
    public void setN(int N) { this.N = N; }
    public void setApples(Apple[] apples) { this.apples = apples; }
    public void setTiles(int[][] tiles) { this.tiles = tiles; }
    public void setLadders(Ladder[] ladders) { this.ladders = ladders; }
    public void setSnakes(Snake[] snakes) { this.snakes = snakes; }

    /**
     * This function creates the board with all the snakes, the ladders and the apples
     * according to all the restrictions given
     * 1) For snake's tail and head a random tile is chosen, but the head must be in higher
     * tile than the tail.
     * 2) There can be no tail or head of a snake in the same tile as a tail or head of another snake.
     * 3) For ladder's down step and up step a random tile is chosen, but the up step must
     * be in higher tile that the down step.
     * 4) There can be no down or up step of a ladder in the same tile as a down or up step of
     * another ladder.
     * 5) For apple's position a random tile is chosen.
     * 6) There can be no apple in the same tile as a head of any snake.
     * 7) There can be no up step in the same tile as a head of any snake.
     * 8) There can be no down step in the same tile as a head of any snake.
     * 9) There can be no apple in the same tile as another apple.
     */
    public void createBoard() {
        //Initialize the tile numbers according to the game rules. Example:
        // 11 12 13 14 15
        // 10  9  8  7  6
        //  1  2  3  4  5
        boolean reverse = false;
        int num = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (reverse) {
                for (int j = M - 1; j >= 0; j--) {
                    this.tiles[i][j] = num++;
                }
            } else {
                for (int j = 0; j < M; j++) {
                    this.tiles[i][j] = num++;
                }
            }
            reverse = !reverse;
        }

        //These ArrayLists store the number of tiles that has already been chosen for something
        //so later there will be check so the restrictions will apply
        ArrayList<Integer> alreadySelectedSnakeNumbers = new ArrayList<>();
        ArrayList<Integer> alreadySelectedLadderUpNumbers = new ArrayList<>();
        ArrayList<Integer> alreadySelectedLadderDownNumbers = new ArrayList<>();
        ArrayList<Integer> alreadySelectedAppleNumbers = new ArrayList<>();

        //This loop creates the snakes while checking the restrictions
        for (int i = 0; i < this.snakes.length; i++) {
            //Choose a random number for the head
            int head = getRandomNumberInRange(this.M + 1, this.M * this.N - 1);
            //repeat this process while the number can be chosen according to the restrictions
            while (alreadySelectedSnakeNumbers.contains(head)) {
                head = getRandomNumberInRange(this.M + 1, this.M * this.N - 1);
            }
            //Add the selected tile number to the necessary ArrayLists so that the number will not be
            //chosen again if there is a restriction for it
            alreadySelectedSnakeNumbers.add(head);
            alreadySelectedLadderUpNumbers.add(head);
            alreadySelectedLadderDownNumbers.add(head);
            alreadySelectedAppleNumbers.add(head);
            //Choose a random number for the tail
            //temp is used make sure that the head and tail are in different rows
            int temp = head % M;
            if (temp == 0) { temp = M; }
            int tail = getRandomNumberInRange(1, head - temp);
            //repeat this process while the number can be chosen according to the restrictions
            while (alreadySelectedSnakeNumbers.contains(tail)) {
                tail = getRandomNumberInRange(1, head - temp);
            }
            //Add the selected tile number to the necessary ArrayLists so that the number will not be
            //chosen again if there is a restriction for it
            alreadySelectedSnakeNumbers.add(tail);
            //Create a new snake with the chosen numbers for serial number, head tile and tail tile
            this.snakes[i] = new Snake(i, head, tail);
        }

        //This loop creates the ladders while checking the restrictions
        for (int i=0; i<this.ladders.length; i++) {
            //Choose a random number for the up step
            int up = getRandomNumberInRange(this.M + 1, this.M * this.N - 1);
            //repeat this process while the number can be chosen according to the restrictions
            while (alreadySelectedLadderUpNumbers.contains(up)) {
                up = getRandomNumberInRange(this.M + 1, this.M * this.N - 1);
            }
            //Add the selected tile number to the necessary ArrayLists so that the number will not be
            //chosen again if there is a restriction for it
            alreadySelectedLadderUpNumbers.add(up);
            alreadySelectedLadderDownNumbers.add(up);
            //Choose a random number for the tail
            //temp is used make sure that the up and down steps are in different rows
            int temp = up % M;
            if (temp == 0) { temp = M; }
            int down = getRandomNumberInRange(1, up - temp);
            //repeat this process while the number can be chosen according to the restrictions
            while (alreadySelectedLadderDownNumbers.contains(down)) {
                down = getRandomNumberInRange(1, up - temp);
            }
            //Add the selected tile number to the necessary ArrayLists so that the number will not be
            //chosen again if there is a restriction for it
            alreadySelectedLadderUpNumbers.add(down);
            alreadySelectedLadderDownNumbers.add(down);
            //Create a new ladder with the chosen numbers for serial number, up step and down step tile
            this.ladders[i] = new Ladder(i, up, down);
        }

        //This loop creates the apples while checking the restrictions
        for (int i=0; i<this.apples.length; i++) {
            //The colors that can be chosen for the apples
            String colorBlack = "Black";
            String colorRed = "Red";
            //Choose a random number for the apple
            int applePos = getRandomNumberInRange(1, this.M * this.N);
            //repeat this process while the number can be chosen according to the restrictions
            while (alreadySelectedAppleNumbers.contains(applePos)) {
                applePos = getRandomNumberInRange(1, this.M * this.N);
            }
            //Add the selected tile number to the necessary ArrayLists so that the number will not be
            //chosen again if there is a restriction for it
            alreadySelectedAppleNumbers.add(applePos);
            //Choose randomly the color of the apple (50-50 chance)
            //then create a new apple with the chosen numbers for serial number, tile number and color
            int rand = getRandomNumberInRange(0, 100);
            if (rand > 50) {
                this.apples[i] = new Apple(i, applePos, colorBlack);
            } else {
                this.apples[i] = new Apple(i, applePos, colorRed);
            }
        }

        //Now that the arrays for snakes, ladders and apples are complete, create the board of the game
        createElementBoard();
    }

    /**
     * This function creates three boards and prints them in the same row.
     * The three boards are for the position of snakes, ladders and apples.
     * For snakes, if there is a snake head the board must show SH# (# is the serial number of the snake),
     * if there is a snake tail the board must show ST# (# is the serial number of the snake).
     * For ladders, if there is an up step the board must show LU# (# is the serial number of the ladder),
     * if there is a down step the board must show LD# (# is the serial number of the ladder).
     * For apples, if there is a red apple the board must show AR# (# is the serial number of the apple),
     * if there is a black apple the board must show AB# (# is the serial number of the apple).
     * For all the boards, if the tile is empty the board must show ___.
     * Example:
     * ___ ___ ___ SH0 ___     ___ LU0 ___ ___ ___     ___ AR1 ___ ___ ___
     * ___ ___ ST0 ___ SH1     ___ ___ LD0 ___ LU1     ___ ___ ___ AB0 ___
     * ___ ___ ___ ST1 ___     ___ ___ ___ ___ LD1     ___ ___ AR2 ___ ___
     */
    public void createElementBoard() {
        //Initialize the three boards
        String[][] elementBoardSnakes = new String[N][M];
        String[][] elementBoardLadders = new String[N][M];
        String[][] elementBoardApples = new String[N][M];

        //This loop creates the snakes board row by row
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < snakes.length; k++) {
                    if (tiles[i][j] == snakes[k].getHeadID()) { //If there is a head
                        elementBoardSnakes[i][j] = "SH" + snakes[k].getSnakeID();
                    } else if (tiles[i][j] == snakes[k].getTailID()) { //If there is a tail
                        elementBoardSnakes[i][j] = "ST" + snakes[k].getSnakeID();
                    } else if (elementBoardSnakes[i][j] == null){ //If the tile is empty
                        elementBoardSnakes[i][j] = "___";
                    }
                }
            }
        }

        //This loop creates the ladders board row by row
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < ladders.length; k++) {
                    if (tiles[i][j] == ladders[k].getUpStepID()) { //If there is an up step
                        elementBoardLadders[i][j] = "LU" + ladders[k].getLadderID();
                    } else if (tiles[i][j] == ladders[k].getDownStepID()) { //If there is a down step
                        elementBoardLadders[i][j] = "LD" + ladders[k].getLadderID();
                    } else if (elementBoardLadders[i][j] == null){ //If the tile is empty
                        elementBoardLadders[i][j] = "___";
                    }
                }
            }
        }

        //This loop creates the apples board row by row
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < apples.length; k++) {
                    if (tiles[i][j] == apples[k].getAppleTileID()) { //If there is an apple
                        if (apples[k].getColor() == "Red") { //if it is red
                            elementBoardApples[i][j] = "AR" + apples[k].getAppleID();
                        } else { //if it is black
                            elementBoardApples[i][j] = "AB" + apples[k].getAppleID();
                        }
                    } else if (elementBoardApples[i][j] == null){ //If the tile is empty
                        elementBoardApples[i][j] = "___";
                    }
                }
            }
        }

        //Print the three boards in the same row, first the snake board, then the ladder and
        //last the apple board.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(" " + elementBoardSnakes[i][j]);
            }
            System.out.print("     ");
            for (int j = 0; j < M; j++) {
                System.out.print(" " + elementBoardLadders[i][j]);
            }
            System.out.print("     ");
            for (int j = 0; j < M; j++) {
                System.out.print(" " + elementBoardApples[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This function creates and returns a random integer between the given values of max and min
     *
     * @param min is the lowest  number possible
     * @param max is the highest number possible
     * @return the random number
     */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
