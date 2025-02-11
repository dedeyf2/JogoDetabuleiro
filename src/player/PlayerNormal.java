package player;

import player.Player;
import player.enums.Color;

import java.util.Random;

public class PlayerNormal extends Player{
    public PlayerNormal(Color color){
        super(color);
    }
    @Override
    public int[] rollDice() {
        Random random = new Random();
        int[] diceArray = {random.nextInt(6) + 1, random.nextInt(6) + 1};
        return diceArray;
    }
    
}