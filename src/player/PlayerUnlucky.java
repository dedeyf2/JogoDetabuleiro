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

        System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");
        int sumDice = diceArray[0] + diceArray[1];
        movePosition(sumDice);
        System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);
        
        // Verifica se os valores são duplicados
        while (diceArray[0] == diceArray[1]) {
            System.out.println("Jogador da cor " + getColor() + " tirou valores duplicados e jogará novamente!");
            do {
                diceArray = new int[]{random.nextInt(6) + 1, random.nextInt(6) + 1};
            } while (diceArray[0] + diceArray[1] > 6); // Garante soma > 6
            System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");
            sumDice = diceArray[0] + diceArray[1];
            movePosition(sumDice);
            System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);
        }
        timesPlayed++;
    return diceArray;
	}

}