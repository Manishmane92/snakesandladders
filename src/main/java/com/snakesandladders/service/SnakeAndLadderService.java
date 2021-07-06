package com.snakesandladders.service;

import com.snakesandladders.model.Player;
import com.snakesandladders.model.SnakeAndLadderBoard;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private Player player;

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NO_OF_DICES = 1;
    private static final int MAX_NO_OF_ROLLS = 10;
    private int noOfDices;

    public SnakeAndLadderService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
    }

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
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


    private void movePlayer(Player player, int positions) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + positions;

        int boardSize = snakeAndLadderBoard.getSize();
        if (newPosition > boardSize) {
            newPosition = oldPosition;
        }

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);
    }

    private int getTotalValueAfterDiceRolls(int noOfDices) {
        int value =0;
        for(int i =1;i<=noOfDices;i++){
            value = value + DiceService.roll();
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
            int totalDiceValue = getTotalValueAfterDiceRolls(noOfDices);
            Player currentPlayer = player;
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                //snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
                return;
            }
            noOfRolls++;
        }
        System.out.println("Max chances are finished, Start Game and Play again");
    }
}
