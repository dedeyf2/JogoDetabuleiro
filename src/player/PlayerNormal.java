package player;

import java.util.Random;

import player.enums.Color;

public class PlayerNormal extends Player{
    public PlayerNormal(Color color){
        super(color);
    }
    @Override
    public int[] rollDice() {
    	if (getPosition() >= 40) {
	        System.out.println("Jogador da cor " + getColor() + " já chegou à posição 40 e não precisa jogar novamente.");
	        return new int[] {0, 0}; 
	    }
    	Random random = new Random();
        int[] diceArray = {random.nextInt(6) + 1, random.nextInt(6) + 1};

        
        System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");
        int sumDice = diceArray[0] + diceArray[1];
        
        movePosition(sumDice);

       
        System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);

        
        while (diceArray[0] == diceArray[1]) {
            System.out.println("Jogador da cor " + getColor() + " tirou valores duplicados e jogará novamente!");
            diceArray = new int[]{random.nextInt(6) + 1, random.nextInt(6) + 1};
            System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");
            sumDice = diceArray[0] + diceArray[1];
            movePosition(sumDice);
            System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);
        }

        timesPlayed++;
        return diceArray;
    }

}