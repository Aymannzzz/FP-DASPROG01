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
public class Player {
    // States
    private String name;
    private int position;
    private int canPlay;

    // Constructor method
    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.canPlay = 0;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setCanPlay(int canPlay) {
        this.canPlay = canPlay;
    }

    // Getter methods
    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return this.position;
    }

    public int getCanPlay() {
        return this.canPlay;
    }

    // Other methods
    public int rollDice() {
        return (int) ((Math.random() * 6) + 1);
    }

    public void moveAround(int moves, int boardSize) {
        if (this.position + moves > boardSize) {
            this.position = (boardSize - this.position) + (boardSize - moves);
        } else {
            this.position = this.position + moves;
        }
    }
}
