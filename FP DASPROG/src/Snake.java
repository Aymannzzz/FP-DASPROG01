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
public class Snake{
    int headSnake;
    int tailSnake;

    Snake(int headSnake, int tailSnake){
        this.headSnake = headSnake;
        this.tailSnake = tailSnake;
    }

    void setTail(int tailSnake){
        this.tailSnake = tailSnake;
    }
    void setHead(int headSnake){
        this.headSnake = headSnake;
    }
    int getTail(){
        return this.tailSnake;
    }
    int getHead(){
        return this.headSnake;
    }
}