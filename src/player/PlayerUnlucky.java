package player;
import java.util.Random;

import player.enums.Color;

public class PlayerUnlucky extends Player {

 public PlayerUnlucky(Color color) {
        super(color);
    }
    @Override
    public int[] rollDice() {
    	Random random = new Random();
        int[] diceArray;

        do {
            diceArray = new int[]{random.nextInt(6) + 1, random.nextInt(6) + 1};
        } while (diceArray[0] + diceArray[1] > 6); // Garante limite = 6
        timesPlayed++;
        return diceArray;
	}

}