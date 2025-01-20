package player;

import java.util.Random;

import player.enums.Color;
import java.util.Random;

public abstract class Player {
    protected Color color;
    protected int position = 0;
    protected int timesPlayed;
    protected boolean stunned;

    public Player(Color color){
        this.color = color;
        position = 0;
    }

    public Color getColor() {
        return color;
    }
    public int getPosition() {
        return position;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void movePosition(int value) {
        position += value;
        if (position > 40) {
            position = 40; 
        }
    }
    public boolean getStunned(){
        return stunned;
    }

    public void setStunned(boolean stunned){
        this.stunned = stunned;
    }

    public int[] rollDice() {
    	if (getPosition() >= 40) {
	        System.out.println("Jogador da cor " + getColor() + " já chegou à posição 40 e não precisa jogar novamente.");
	        return new int[] {0, 0}; 
	    }
    	Random random = new Random();
        int[] diceArray = {random.nextInt(6) + 1, random.nextInt(6) + 1};

        // Exibe o resultado de cada dado
        System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");

        // Calcula a soma e movimenta
        int sumDice = diceArray[0] + diceArray[1];
        
        movePosition(sumDice);

        // Exibe a movimentação
        System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);

        // Verifica se os valores são duplicados
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
    
    

    