package game;

import tile.Board;



import java.util.ArrayList;
import java.util.Scanner;

import player.Player;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Board board = new Board();
        boolean debugMode = false;
        game.setupPlayers();
        Game.listPlayers(false);
        String continueConfirmation;

        ArrayList<Player> playerList = Game.getPlayers();
        int i;
        boolean winCondition = false;
        Scanner input = new Scanner(System.in);
        while (!winCondition) {
            for(i = 0; i < playerList.size(); i++){
                Player currentPlayer = playerList.get(i);
                executeTurn(currentPlayer, debugMode, input);
                board.stepOnTile(currentPlayer);
                if(currentPlayer.getPosition() >= 40) {
                	winCondition = true;
                	System.out.println("jogador " + currentPlayer.getColor() + "ganhou!");
                	break;
                }
            }
            Game.listPlayers(true);
            //adicione uma pausa
        }
        input.close();
    }

    public static void executeTurn(Player player, boolean debugMode, Scanner input){
    	int diceResult = 0;
        if (debugMode) {//no modo DEBUG você manualmente insere o valor a se mover
           
            System.out.println("Insira um valor para o jogador " + player.getColor() + " mover");
            diceResult = input.nextInt();
            input.nextLine(); 
           
        }else{
            player.rollDice();
    }
        
}
   
    }