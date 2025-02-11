package game;

import java.util.Scanner;

import player.Player;

public class TurnController extends Game{
	   public static void executeTurn(Player player, boolean debugMode, Scanner input){
	    	if (player.getImprisoned()) {
	            System.out.println("Jogador " + player.getColor() + " está atordoado e não jogará nesta rodada.");
	            player.setImprisoned(true);
	            return;  
	        }
	    	
	    	int diceResult = 0;
	        if (debugMode) {//no modo DEBUG você manualmente insere o valor a se mover
	           
	            System.out.println("Insira um valor para o jogador " + player.getColor() + " mover");
	            diceResult = input.nextInt();
	            input.nextLine(); 
	            player.movePosition(diceResult);
	           
	        }else{
	            player.doTurn();
	    }
	        
	}	

}