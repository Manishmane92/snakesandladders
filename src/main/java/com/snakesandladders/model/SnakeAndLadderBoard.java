package com.snakesandladders.model;

import java.util.HashMap;
import java.util.Map;

public class SnakeAndLadderBoard {

    private int size;
    private Map<String, Integer> playerPieces;

    public SnakeAndLadderBoard(int size) {
        this.size = size;
        this.playerPieces = new HashMap<String, Integer>();
    }

    public int getSize() {
        return size;
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
