package player;

import java.util.Random;
import java.util.Scanner;

import player.enums.Color;

public abstract class Player {
    protected Color color;
    protected int position = 0;
    protected int coin;
    protected int timesPlayed;
    protected boolean imprisoned;

    public Player(Color color){
        this.color = color;
        position = 0;
        imprisoned = false;
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

    public boolean getImprisoned() {
        return imprisoned;
    }

    public void setImprisoned(boolean imprisoned) {
        this.imprisoned = imprisoned;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void acquireCoin(int value) throws NotEnoughCoinException{
        if (coin + value < 0) {
            throw new NotEnoughCoinException();
        }else{
            coin = coin + value;
        }
    }

    public abstract int[] rollDice();
    

    public void doTurn(){
        checkImprisoment();

        int[] diceArray = rollDice();
        // Exibe o resultado de cada dado
        System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");

        // Calcula a soma e movimenta
        int sumDice = diceArray[0] + diceArray[1];
        movePosition(sumDice);
        if(getPosition() >= 40) {
        	setPosition(40);
        }

        // Exibe a movimentação
        System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);

        // Verifica se os valores são duplicados
        while (diceArray[0] == diceArray[1]) {
            System.out.println("Jogador da cor " + getColor() + " tirou valores duplicados e jogará novamente!");
            diceArray = rollDice();
            System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");
            sumDice = diceArray[0] + diceArray[1];
            movePosition(sumDice);
            if(getPosition() >= 40) {
            	setPosition(40);
            }
            System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);
        }

        timesPlayed++;
    }

    protected void checkImprisoment(){
        if(imprisoned) { 
            Scanner scanner = new Scanner(System.in);
            System.out.println("Jogador " + getColor() + "está preso, mas pode pagar a fiança de 3 moedas para sair agora.");
            System.out.println("Pagar fiança? (S/N)");
            String answer = scanner.nextLine();
            if (answer.toLowerCase().matches("s")){
                try {
                    acquireCoin(-3);
                } catch (NotEnoughCoinException e) {
                    // TODO: handle exception
                }
            }
            
        }
    }
}
    
    

    