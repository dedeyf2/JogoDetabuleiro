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
        
        Scanner input = new Scanner(System.in);
        ArrayList<Player> playerList = Game.getPlayers();
        playerList = Game.definePlayerOrder(playerList, input);
        debugMode = debugMode(debugMode,input);
        
    
        boolean winCondition = false;
       
        while (!winCondition) {
            for (Player currentPlayer : playerList) {
                TurnController.executeTurn(currentPlayer, debugMode, input);
                board.stepOnTile(currentPlayer);
                if (currentPlayer.getPosition() >= 40) {
                    winCondition = true;
                    System.out.println("Jogador " + currentPlayer.getColor() + " ganhou!");
                    break;
                }
            }
            if (!winCondition) {
                Game.listPlayers(true);
                System.out.println("Pressione Enter para continuar...");
                input.nextLine();
            }
        }
        
    }
    public static boolean debugMode(boolean debugMode,Scanner input) {
    	System.out.println("vai ser no modo debug ou não? y/n?");
        char resposta;
        do {
            System.out.print("Digite 'y' para sim ou 'n' para não: ");
            String entrada = input.next().trim().toLowerCase();

            if (entrada.length() == 1) {
                resposta = entrada.charAt(0);
                if (resposta == 'y' || resposta == 'n') {
                    break;
                }
            }
            
            System.out.println("Entrada inválida. Tente novamente.");
        } while (true);

        if (resposta == 'y') {
            System.out.println("Você escolheu modo debug.");
            debugMode = true;
        } else {
            System.out.println("Você escolheu normal.");
            debugMode = false;
        }
        return debugMode;
    }
}
