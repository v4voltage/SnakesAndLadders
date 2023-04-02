import java.util.Random;
import java.util.Scanner;

public class Game {
    private int round; //the current round of the game

    /**
     * The empty constructor of the class
     */
    public Game() {
        this.round = 0;
    }

    /**
     * Getters and Setters for the round of the game
     */
    public int getRound() { return round; }

    public void setRound(int round) { this.round = round; }


    /**
     * The main function of the program
     */
    public static void main(String[] args) {
        //Initialize the die variable
        int die;

        //Create a new board
        int N = 4; //rows of the board
        int M = 4; //columns of the board
        Board gameBoard = new Board(N, M, 3, 3, 6);

        //Initialize the total number of the players
        System.out.print("Give number of players: ");
        Scanner scanner = new Scanner(System. in);
        int numberOfPlayers = scanner.nextInt();

        //Create the players
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Give name of player: ");
            Scanner input = new Scanner(System. in);
            String name = input.nextLine();
            players[i] = new Player(i+1, name, gameBoard);
        }

        //Initialize the starting position of the players to be outside the board
        int[][] playersState = new int[numberOfPlayers][4];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersState[i][0] = 0;
        }

        //Set the current player to the first one
        int currentPlayer = numberOfPlayers - 1;

        //Choose randomly who plays first, second etc
        int[] dice = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            Random r = new Random();
            die = r.nextInt(6) + 1;
            dice[i] = die;
        }
        for (int i = 0; i < numberOfPlayers - 1; i++) {
            int min = dice[i];
            Player minPlayer = players[i];
            for (int j = i + 1; j < numberOfPlayers; j++) {
                if (dice[j] < min) {
                    int temp = dice[j];
                    dice[j] = min;
                    min = temp;
                    dice[i] = min;

                    Player tempPlayer = players[j];
                    players[j] = minPlayer;
                    minPlayer = tempPlayer;
                    players[i] = minPlayer;
                }
            }
        }


        /*
        //The player who finishes first wins
        //Uncomment if you want to apply this rule
        //Uncomment from HERE(1)
        //Also don't forget to comment the other rule
        do {
            //Set the current player to the next player (if the last player is playing then the next is the first one)
            currentPlayer = (currentPlayer + 1) % numberOfPlayers;
            //Throw the die (create a random number between 1-6)
            Random r = new Random();
            die = r.nextInt((6 - 1) + 1) + 1;
            //Print the status of the player and move the player according to the number of the die
            System.out.println();
            System.out.println("Player: " + players[currentPlayer].getName());
            System.out.println("Roll: " + die);
            playersState[currentPlayer] = players[currentPlayer].move(playersState[currentPlayer][0], die);
            for (int i = 0; i < playersState[currentPlayer].length; i++) {
                System.out.print(" " + playersState[currentPlayer][i]);
            }
            System.out.println(" Score:" + players[currentPlayer].getScore());
        } while (playersState[currentPlayer][0] < N * M);

        //Print the winner's name
        System.out.println();
        System.out.println(players[currentPlayer].getName() + " won!");
        //to HERE(1)
        */


        //Every player finishes and then the player with the highest score wins
        //Uncomment if you want to apply this rule
        //Uncomment from HERE(2)
        //Also don't forget to comment the other rule
        int numberOfPlayersFinished = 0;
        Player[] finishedPlayers = new Player[numberOfPlayers];
        do {
            //Set the current player to the next player (if the last player is playing then the next is the first one)
            currentPlayer = (currentPlayer + 1) % numberOfPlayers;
            //Check if the current player is still in the board or if he has finished
            //If he has finished do nothing and wait for the rest of the players to finish
            if (playersState[currentPlayer][0] < N * M) {
                //Throw the die (create a random number between 1-6)
                Random r = new Random();
                die = r.nextInt(6) + 1;
                //Print the status of the player and move the player according to the number of the die
                System.out.println();
                System.out.println("Player: " + players[currentPlayer].getName());
                System.out.println("Roll: " + die);
                playersState[currentPlayer] = players[currentPlayer].move(playersState[currentPlayer][0], die);
                for (int i = 0; i < playersState[currentPlayer].length; i++) {
                    System.out.print(" " + playersState[currentPlayer][i]);
                }
                System.out.println(" Score:" + players[currentPlayer].getScore());
                if (playersState[currentPlayer][0] >= N * M) {
                    finishedPlayers[numberOfPlayersFinished] = players[currentPlayer];
                    numberOfPlayersFinished++;
                }
            }
        } while (numberOfPlayersFinished < numberOfPlayers);

        //Find the player with the highest score
        String winnerName = finishedPlayers[0].getName();
        int maxScore = finishedPlayers[0].getScore();
        boolean winner = false;
        for (int i = 0; i < numberOfPlayers - 1 && !winner; i++) {
            winner = true;
            maxScore = finishedPlayers[i].getScore();
            for (int j = i + 1; j < numberOfPlayers; j++) {
                if (finishedPlayers[i].getScore() < finishedPlayers[j].getScore()) {
                    winner = false;
                }
            }
            if (winner) {
                winnerName = finishedPlayers[i].getName();
                maxScore = finishedPlayers[i].getScore();
            }
        }
        //Print the winner's name and his score
        System.out.println();
        System.out.println(winnerName + " won with a score of " + maxScore);
        //to HERE(2)
    }
}
