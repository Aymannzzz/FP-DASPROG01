/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 1
 * Members  :
 * 1. 5026231047 - Muhammad Rafly Ayman Masagung
 * 2. 5026231120 - Ida Bagus Adhiraga Yudhistira
 * 3. 5999232023 - Abel, Pierre, Philippe Chartier
 * ------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class SnL {

    // States, variables, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;
    private Random random;
    private Scanner sc;
    private int playerNumber;
    private boolean isFinished;

    // Constructor
    public SnL(int size) {
        this.boardSize = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.players = new ArrayList<>();
        this.gameStatus = 0;
        this.random = new Random();
        this.sc = new Scanner(System.in);
        this.isFinished = false;
        this.currentTurn = 0; // Initialize to the first player
    }

    public void initiateGame() {
        int[][] ladders = {
                {2, 23}, {8, 34},
                {20, 77}, {32, 68},
                {41, 79}, {74, 88},
                {82, 100}, {85, 95}
        };
        setLadders(ladders);
        int[][] snakes = {
                {47, 5}, {29, 9},
                {38, 15}, {97, 25},
                {53, 33}, {92, 70},
                {86, 54}, {97, 25}
        };
        setSnakes(snakes);
    }

    public Player getTurn() {
        Player currentPlayer = players.get(currentTurn);
        currentTurn = (currentTurn + 1) % playerNumber; // Cycle to the next player
        return currentPlayer;
    }

    // Setter methods
    public void setSizeBoard(int size) {
        this.boardSize = size;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public void setLadders(int[][] ladders) {
        int s = ladders.length;
        for (int i = 0; i < s; i++) {
            this.ladders.add(new Ladder(ladders[i][0], ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes) {
        int s = snakes.length;
        for (int i = 0; i < s; i++) {
            this.snakes.add(new Snake(snakes[i][0], snakes[i][1]));
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        initiateGame();

        System.out.println("Enter the number of players (2 to 6):");
        playerNumber = getPlayerNumber();

        if (playerNumber < 2) {
            System.out.println("The game cannot be played with less than 2 players.");
            return; // Exit the play method
        }

        // Instantiate players
        Player[] allPlayers = instantiatePlayers();
        for (int i = 0; i < playerNumber; i++) {
            players.add(allPlayers[i]);
        }

        // Get player names
        getPlayerNames();

        // Get starting player
        getPlayerOrder();

        Player nowPlaying;
        do {
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            if (nowPlaying.getCanPlay() > 0) {
                System.out.println(nowPlaying.getName() + " is in detention for " + nowPlaying.getCanPlay() + " more turn(s).");
                nowPlaying.setCanPlay(nowPlaying.getCanPlay() - 1);
            } else {
                System.out.println("Now Playing: " + nowPlaying.getName() + ", current position: " + nowPlaying.getPosition());
                System.out.println(nowPlaying.getName() + ", press enter to roll dice.");

                String input = sc.nextLine();
                int x = 0;
                if (input.isEmpty()) {
                    x = nowPlaying.rollDice();
                }

                System.out.println(nowPlaying.getName() + " rolls dice and gets: " + x);
                movePlayer(nowPlaying, x);
                System.out.println(nowPlaying.getName() + "'s new position is " + nowPlaying.getPosition());
            }
        } while (!isFinished);

        System.out.println("Game Over! The winner is: " + nowPlaying.getName());
    }

    public void movePlayer(Player p, int x) {
        this.gameStatus = 1;
        p.moveAround(x, this.boardSize);
        for (Ladder l : this.ladders) {
            if (l.getFromPosition() == p.getPosition()) {
                p.setPosition(l.getToPosition());
                System.out.println(p.getName() + " climbs a ladder and moves to " + p.getPosition());
            }
        }

        for (Snake s : this.snakes) {
            if (s.getHead() == p.getPosition()) {
                p.setPosition(s.getTail());
                System.out.println(p.getName() + " encounters a snake and slides down to " + p.getPosition());
            }
        }

        // Special cases
        if (p.getPosition() % 10 == 0) {
            int a = random.nextInt(6) + 1;
            int b = random.nextInt(6) + 1;
            System.out.println(p.getName() + ", answer this question: What is " + a + " + " + b + "?");
            int answer = sc.nextInt();
            if (answer == a + b) {
                System.out.println("Correct! Roll the dice again.");
                movePlayer(p, p.rollDice());
            } else {
                System.out.println("Incorrect. The correct answer is " + (a + b) + ".");
            }
        } else if (p.getPosition() % 5 == 0) {
            System.out.println("Bad luck! " + p.getName() + " cannot play on the next turn.");
            p.setCanPlay(1);
        } else if (p.getPosition() % 7 == 0) {
            System.out.println(p.getName() + " can assign one turn of detention to another player. Enter the player's name:");
            String targetName = sc.next();
            for (Player player : players) {
                if (player.getName().equals(targetName)) {
                    player.setCanPlay(player.getCanPlay() + 1);
                    System.out.println(player.getName() + " is now in detention for " + player.getCanPlay() + " turn(s).");
                    break;
                }
            }
        }

        if (p.getPosition() == this.boardSize) {
            this.isFinished = true;
        }
    }

    private int getPlayerNumber() {
        int n;
        do {
            String s = sc.next();
            try {
                n = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                n = 0;
            }
            if (n > 6 || n < 2) {
                System.out.println("Please enter a number between 2 and 6");
            }
        } while (n > 6 || n < 2);
        System.out.println();
        return n;
    }

    private Player[] instantiatePlayers() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");
        Player player5 = new Player("Player 5");
        Player player6 = new Player("Player 6");

        return new Player[]{player1, player2, player3, player4, player5, player6};
    }

    private void getPlayerNames() {
        for (int i = 0; i < playerNumber; i++) {
            System.out.println("Enter name for Player " + (i + 1) + ":");
            String name;
            do {
                name = sc.next();
                if (!Objects.equals(name, "random") && !name.isEmpty()) {
                    players.get(i).setName(name);
                    players.get(i).setPosition(1);
                } else {
                    System.out.println("Please enter a valid name.");
                }
            } while (players.get(i).getPosition() == -1);
        }
        System.out.println();
    }

    private void getPlayerOrder() {
        System.out.println("Who will start first?\nEnter the name of the starting player or \"random\" to choose randomly:");
        int startPosition = -1;
        Random r = new Random();
        do {
            String first = sc.next();
            if (first.equals("random")) {
                startPosition = r.nextInt(playerNumber);
            } else {
                for (int i = 0; i < playerNumber; i++) {
                    if (Objects.equals(first, players.get(i).getName())) {
                        startPosition = i;
                    }
                }
            }
            if (startPosition == -1) {
                System.out.println("Please enter a valid name from the list or \"random\".");
            }
        } while (startPosition == -1);

        System.out.println("Player " + players.get(startPosition).getName() + " will start the game.");
        System.out.println();
    }

    public static void main(String[] args) {
        SnL game = new SnL(100); // Adjust board size if needed
        game.play();
    }
}
