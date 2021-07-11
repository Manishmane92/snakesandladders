package com.snakesandladders.service;

import com.snakesandladders.model.Player;
import com.snakesandladders.model.Snake;
import com.snakesandladders.model.SnakeAndLadderBoard;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private Player player;

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NO_OF_DICES = 1;
    private static final int MAX_NO_OF_ROLLS = 10;
    private int noOfDices;
    private boolean isCrookedDice;

    public SnakeAndLadderService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        addSnakesToTheBoard();
    }

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
        addSnakesToTheBoard();
    }

    public void addSnakesToTheBoard(){
        List<Snake> snakes = new ArrayList<Snake>();
        Snake snake = new Snake(27,5);
        Snake snake1 = new Snake(40,3);
        Snake snake2 = new Snake(43,18);
        Snake snake3 = new Snake(66,45);
        Snake snake4 = new Snake(76,58);
        Snake snake5 = new Snake(91,53);
        snakes.add(snake);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);
        snakes.add(snake4);
        snakes.add(snake5);
        this.snakeAndLadderBoard.setSnakes(snakes);
    }

    public SnakeAndLadderBoard getSnakeAndLadderBoard(){
        return this.snakeAndLadderBoard;
    }


    public void setPlayer(Player player) {
        this.player = player;
        Map<String, Integer> playerPieces = new HashMap<String, Integer>();
            playerPieces.put(player.getId(), 0);
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakes(snakes); // Add snakes to board
    }

    public void setCrookedDice(boolean crookedDice) {
        isCrookedDice = crookedDice;
    }

    public void movePlayer(Player player, int positions) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions;

        int boardSize = snakeAndLadderBoard.getSize();
        if (newPosition > boardSize) {
            newPosition = oldPosition;
        }else {
            newPosition = getNewPositionAfterGoingThroughSnakes(newPosition);
        }

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);
    }

    public int getTotalValueAfterDiceRolls() {
        int value =0;

            for (int i = 1; i <= this.noOfDices; i++) {
                value = value + DiceService.roll();
            }

            if(this.isCrookedDice && value%2!=0){
                value = getTotalValueAfterDiceRolls();
            }

        return value;
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeAndLadderBoard.getSize();
        return playerPosition == winningPosition;
    }


    public void startGame(Scanner in) {
        int noOfRolls = 0;
        while (noOfRolls<=MAX_NO_OF_ROLLS) {
            System.out.println("==========================");
            System.out.println("Press any key and press enter to roll Dice");
            String key = in.next();
            int totalDiceValue = getTotalValueAfterDiceRolls();
            Player currentPlayer = player;
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                return;
            }
            noOfRolls++;
        }
        System.out.println("Max chances are finished, Start Game and Play again");
    }

    private int getNewPositionAfterGoingThroughSnakes(int newPosition) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd();
                    System.out.println("Ohhhh!!!  Your piece is eaten by Snake");
                }
            }
        } while (newPosition != previousPosition);
        return newPosition;
    }
}
