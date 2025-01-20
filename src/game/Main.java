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
        Scanner input = new Scanner(System.in);
        ArrayList<Player> playerList = Game.getPlayers();
        playerList = definePlayerOrder(playerList, input);
        debugMode = debugMode(debugMode,input);
        
    
        boolean winCondition = false;
       
        while (!winCondition) {
            for (Player currentPlayer : playerList) {
                executeTurn(currentPlayer, debugMode, input);
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

    
    public static void executeTurn(Player player, boolean debugMode, Scanner input){
    	if (player.getStunned()) {
            System.out.println("Jogador " + player.getColor() + " está atordoado e não jogará nesta rodada.");
            player.setStunned(true);
            return;  
        }
    	
    	int diceResult = 0;
        if (debugMode) {//no modo DEBUG você manualmente insere o valor a se mover
           
            System.out.println("Insira um valor para o jogador " + player.getColor() + " mover");
            diceResult = input.nextInt();
            input.nextLine(); 
            player.movePosition(diceResult);
           
        }else{
            player.rollDice();
    }
        
}
    public static ArrayList<Player> definePlayerOrder(ArrayList<Player> playerList, Scanner input) {
        ArrayList<Player> orderedPlayers = new ArrayList<>();

        System.out.println("Escolha a ordem dos jogadores digitando as cores na sequência desejada:");

        for (int i = 0; i < playerList.size(); i++) {
            System.out.print("Digite a cor do jogador " + (i + 1) + ": ");
            String colorInput = input.nextLine().trim().toUpperCase();
            Player selectedPlayer = null;

            for (Player player : playerList) {
                if (player.getColor().name().equals(colorInput)) {
                    selectedPlayer = player;
                    break;
                }
            }

            if (selectedPlayer != null && !orderedPlayers.contains(selectedPlayer)) {
                orderedPlayers.add(selectedPlayer);
            } else {
                System.out.println("Cor inválida ou já selecionada, tente novamente.");
                i--; 
            }
        }

        System.out.println("Ordem dos jogadores definida:");
        for (Player player : orderedPlayers) {
            System.out.println(player.getColor().name());
        }
       
    
        return orderedPlayers;
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
