package com.snakesandladders.service;

import com.snakesandladders.model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SnakesAndLadderServiceTest {

    SnakeAndLadderService snakeAndLadderService;

    Player player;

    @Before
    public void Setup(){
        snakeAndLadderService = new SnakeAndLadderService();
        player = new Player("Manish");
        snakeAndLadderService.setPlayer(player);
        snakeAndLadderService.setNoOfDices(2);
    }

    @Test
    public void playerPieceShouldMoveOnTheBoardAsPerTheDiceValue(){
        snakeAndLadderService.movePlayer(player,6);

        int newPosition = snakeAndLadderService.getSnakeAndLadderBoard().getPlayerPieces().get(player.getId());

        Assert.assertEquals(newPosition,6);

    }

    @Test
    public void playerPieceShouldMoveToSnakeTailWhenSnakeBites(){
        snakeAndLadderService.movePlayer(player,27);

        int newPosition = snakeAndLadderService.getSnakeAndLadderBoard().getPlayerPieces().get(player.getId());

        Assert.assertEquals(newPosition,5);

    }

    @Test
    public void diceRollShouldReturnEvenNumberWhenCrookedDiceIsSelected(){
        snakeAndLadderService.setCrookedDice(true);
        int diceValue = snakeAndLadderService.getTotalValueAfterDiceRolls();


        Assert.assertTrue(diceValue%2==0);

    }
}
