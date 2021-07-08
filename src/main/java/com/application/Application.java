package com.application;

import com.snakesandladders.model.Player;
import com.snakesandladders.model.Snake;
import com.snakesandladders.service.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
    System.out.println("=============== Welcome to Snakes and Ladders ===========");
    System.out.println("Enter your player name :");
    Player player = new Player(scanner.next());

    System.out.println("Choose number of dices(1/2):");
    int numOfDices = scanner.nextInt();
    if(!validateNoOfDicesInput(numOfDices)){
        return;
    }

    System.out.println("Do you want to play with Crooked dice(Y/N):");
    String diceOptionInput = scanner.next();
    validateCrookedDiceInput(diceOptionInput,snakeAndLadderService);

    if (!addSnakesOnTheBoard(scanner, snakeAndLadderService)) return;

    snakeAndLadderService.setPlayer(player);
    snakeAndLadderService.setNoOfDices(numOfDices);
    snakeAndLadderService.startGame(scanner);
    }

    private static boolean addSnakesOnTheBoard(Scanner scanner, SnakeAndLadderService snakeAndLadderService) {
        System.out.println("Do you want to add snakes on the board(Y/N)");
        try {
            String input = scanner.next();
            if ("Y".equalsIgnoreCase(input)) {
                System.out.println("How many snakes you want to add, please enter value between 1 to 5 : ");
                int numOfSnakes = scanner.nextInt();
                if(numOfSnakes>0 && numOfSnakes<6){
                    List<Snake> snakes = new ArrayList<Snake>();
                    for(int i =0;i<numOfSnakes;i++){
                        System.out.println("Snake start position should be greater than end position(Example : start position = 13, end position = 6)");
                        System.out.println("Enter start position of snake no "+ i + ":");
                        int start = scanner.nextInt();
                        System.out.println("Enter end position of snake no "+ i + ":");
                        int end = scanner.nextInt();
                        Snake snake = new Snake(start,end);
                        snakes.add(snake);
                    }
                    snakeAndLadderService.setSnakes(snakes);
                }else {
                    System.out.println("Invalid Entry, Please start the game again");
                    return false;
                }
            }else if("N".equalsIgnoreCase(input)){
                System.out.println("Default number of snakes will be added to board");
            }else{
                System.out.println("Invalid Entry, Please start the game again");
                return false;
            }
        }catch (Exception e){
            System.out.println("Invalid Entry, Please start the game again");
            return false;
        }
        return true;
    }

    public static boolean validateNoOfDicesInput(int numOfDices){
        try {
             if (numOfDices != 1 && numOfDices != 2) {
                 System.out.println("Invalid Entry, Please start the game again");
                 return false;
             }
        }catch (Exception e){
             System.out.println("Invalid Entry, Please start the game again");
             return false;
        }
            return true;
    }

    public static boolean validateCrookedDiceInput(String crookedDiceOption, SnakeAndLadderService snakeAndLadderService){
        try {
            if ("Y".equalsIgnoreCase(crookedDiceOption)) {
                snakeAndLadderService.setCrookedDice(true);
            }
        }catch (Exception e){
            System.out.println("Invalid Entry, Please start the game again");
            return false;
        }
        return true;
    }
}
