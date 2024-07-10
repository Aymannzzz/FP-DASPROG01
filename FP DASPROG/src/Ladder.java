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
public class Ladder{
    int fromPosition;
    int toPosition;

    Ladder(int from, int to){
        this.fromPosition = from;
        this.toPosition = to;
    }

    void setFromPosition(int from){
        this.fromPosition =  from;

    }

    void setToPosition(int to){
        this.toPosition = to;

    }

    int getFromPosition(){
        return fromPosition;

    }
    int getToPosition(){
        return toPosition;

    }
}