package com.application;

import com.snakesandladders.model.Player;
import com.snakesandladders.service.SnakeAndLadderService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numOfDices = 0;
    System.out.println("=============== Welcome to Snakes and Ladders ===========");
    System.out.println("Enter your player name :");
    Player player = new Player(scanner.next());
    System.out.println("Choose number of dices(1/2):");
    try {
        numOfDices = scanner.nextInt();
        if (numOfDices != 1 && numOfDices != 2) {
            System.out.println("Invalid Entry, Please start game again");
            return;
        }
    }catch (Exception e){
        System.out.println("Invalid Entry, Please start game again");
        return;
    }
    SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setPlayer(player);
        snakeAndLadderService.setNoOfDices(numOfDices);
        snakeAndLadderService.startGame(scanner);
}
}
