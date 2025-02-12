package game;

import java.util.Scanner;

import player.Player;

public class TurnController {
    public static void executeTurn(Player player, boolean debugMode, Scanner input) {
    	 if (player.checkImprisoment()) {
    	        return;  // Se o jogador estiver preso, ele perde o turno
    	    }

        int diceResult = 0;
        if (debugMode) { // Modo DEBUG: usuário escolhe o valor do dado
            System.out.println("Insira um valor para o jogador " + player.getColor() + " mover:");
            diceResult = input.nextInt();
            input.nextLine(); 
            player.movePosition(diceResult);
        } else {
            player.doTurn();
        }
    }
}