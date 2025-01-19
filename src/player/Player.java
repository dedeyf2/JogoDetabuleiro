package player;

import java.util.Random;

import player.enums.Color;
import java.util.Random;

public abstract class Player {
    protected Color color;
    protected int position;
    protected int timesPlayed;

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
        position = position + value;
    }

    public int[] rollDice() {
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
        if (diceArray[0] == diceArray[1]) {
            System.out.println("Jogador da cor " + getColor() + " tirou valores duplicados e jogará novamente!");
            int[] extraRoll = rollDice(); // Rola os dados novamente
            System.out.println("Jogador da cor " + getColor() + " avançou mais " + (extraRoll[0] + extraRoll[1]) + " casas!");
        }

        timesPlayed++;
        return diceArray;
    }
}
    
    

    