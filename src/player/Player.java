package player;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import player.enums.Color;
import player.enums.PlayerItem;
import game.Game;
public abstract class Player {
    protected Color color;
    protected int position = 0;
    protected int coin;
    protected int timesPlayed;
    protected boolean imprisoned;
    protected int imprisonedRounds;
    protected Set<PlayerItem> items = new HashSet<>();
    
    public Player(Color color){
        this.color = color;
        position = 0;
        imprisoned = false;
        this.imprisonedRounds = 0;
    }

    
    public int getImprisonedRounds() {
		return imprisonedRounds;
	}


	public void setImprisonedRounds(int imprisonedRounds) {
	 int atual = getImprisonedRounds();
	 this.imprisonedRounds = imprisonedRounds + atual; 
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
        int numCasa = Game.getNumCasas();
        if (position >= numCasa) {
            
        	position = numCasa - 1; // Limita a posição ao número máximo de casas
        }
    }

    public boolean getImprisoned() {
        return imprisoned;
    }

    public void setImprisoned(boolean imprisoned) {
        this.imprisoned = imprisoned;
        if (!imprisoned) {
            imprisonedRounds = 0;  // Reseta o contador ao sair da prisão
        }
    }
    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void acquireCoin(int value) throws NotEnoughCoinException{
        if (coin + value < 0) {
            coin = 0;
        }else{
            coin = coin + value;
        }
      
      
    }
    public void setTimesPlayed(int jogada) {
  	  	timesPlayed = timesPlayed + jogada;
  	  	
    }
    public int getTimesPlayed() {
    	return timesPlayed;
    }
    public void acquireItem(PlayerItem item) {
        if (!items.contains(item)) {
            items.add(item);
            System.out.println("Jogador " + color + " adquiriu " + item + "!");
            try {
                acquireCoin(item.getCoinBonus()); // Adiciona moedas pelo item adquirido
            } catch (NotEnoughCoinException e) {
                System.out.println("Erro ao adicionar moedas.");
            }
        } else {
            System.out.println("Jogador " + color + " já possui " + item + ".");
        }
    }

    public PlayerItem getBestItem() {
        return items.stream()
            .max((i1, i2) -> Integer.compare(i1.getMoveBonus(), i2.getMoveBonus()))
            .orElse(null);
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
        

        // Exibe a movimentação
        System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);

        // Verifica se os valores são duplicados
        while (diceArray[0] == diceArray[1]) {
            System.out.println("Jogador da cor " + getColor() + " tirou valores duplicados e jogará novamente!");
            diceArray = rollDice();
            System.out.println("Jogador da cor " + getColor() + " girou " + diceArray[0] + " e " + diceArray[1] + " nos dados");
            sumDice = diceArray[0] + diceArray[1];
            movePosition(sumDice);
            
            System.out.println("Jogador da cor " + getColor() + " avançou " + sumDice + " casas e está na posição " + position);
        }

        
    }

    public boolean checkImprisoment() {
        if (imprisoned) {
            imprisonedRounds++; // Incrementa as rodadas preso corretamente

            if (imprisonedRounds > 2) {  // Libera o jogador automaticamente após duas rodadas presas
                System.out.println("Jogador " + getColor() + " ficou preso por duas rodadas e agora está livre.");
                setImprisoned(false);  
                imprisonedRounds = 0; // Reseta o contador de rodadas presas
                return false;  // O jogador não está mais preso
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Jogador " + getColor() + " está preso. Pode pagar 2 moedas para sair.");
            System.out.println("Pagar fiança? (S/N)");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("s")) {
                if (coin >= 2) {
                    try {
                        acquireCoin(-2);  // O jogador paga 2 moedas
                        setImprisoned(false);  // Libera o jogador
                        imprisonedRounds = 0; // Reseta o contador
                        System.out.println("Jogador pagou a fiança e está livre!");
                        return false;  // Sai da prisão
                    } catch (NotEnoughCoinException e) {
                        System.out.println("Moedas insuficientes para pagar a fiança.");
                    }
                } else {
                    System.out.println("Jogador não tem moedas suficientes para pagar a fiança.");
                }
            } else {
                System.out.println("Jogador optou por ficar preso. Ele perderá esta rodada.");
            }

            return true;  // O jogador ainda está preso
        }
        return false;  // O jogador não está preso
    }

}
    
    

    