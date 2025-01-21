package game;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import player.Player;
import player.PlayerLucky;
import player.PlayerNormal;
import player.PlayerUnlucky;
import player.enums.Color;

public class Game {
	
	protected static ArrayList<Player> playerList = new ArrayList<Player>();
	Scanner scanner = new Scanner(System.in);
	private static final int MAX_PLAYERS = 6;
	
	public void setupPlayers(){
        String input = "Você não deveria estar vendo isso!";
        Random random = new Random();
        System.out.print("Digite a cor de cada jogador(RED, BLUE, GREEN, YELLOW, BLACK, WHITE) seguido pela tecla ENTER. \n");
       
        
        while (true) {
        	 if (playerList.size() >= MAX_PLAYERS) {
                 System.out.println("Número máximo de jogadores alcançado.");
                 break;
        	 }
        	 
            input = scanner.nextLine();
            if(input.isEmpty()){
                if ( !validateMinimumRequirements()) {
                    System.out.println("O jogo precisa de pelo menos dois jogadores e dois tipos diferentes de jogadores. Adicione mais jogadores.");
                    continue;
                }
                break;
            }
            try {
            Color playerColor = Color.valueOf(input.toUpperCase());
            
            Player newPlayer = generatePlayerDiversity(playerColor, random);
            
            playerList.add(newPlayer);
            System.out.println("jogador adicionado com cor "+ playerColor);
            }catch (IllegalArgumentException e){
            	System.out.println("Cor inválida! Tente novamente com uma cor válida.");
            }
            }
       
        }
	
	private Player generatePlayerDiversity(Color playerColor, Random random) {
        Player newPlayer;
        if (playerList.isEmpty()) {
            newPlayer = generateRandomPlayer(playerColor, random);
            
        } else if (playerList.size() == 1) {
       
            do {
                newPlayer = generateRandomPlayer(playerColor, random);
            } while (newPlayer.getClass().equals(playerList.get(0).getClass()));
        } else {
            newPlayer = generateRandomPlayer(playerColor, random);
        }
        return newPlayer;
    }
	
	private Player generateRandomPlayer(Color playerColor, Random random) {
        switch (random.nextInt(3)) {
            case 0:
            	System.out.println("jogador " + playerColor + " é azarado\n");
                return new PlayerUnlucky(playerColor);
            case 1:
            	System.out.println("jogador " + playerColor + " é normal\n");
                return new PlayerNormal(playerColor);
            case 2:
            	System.out.println("jogador " + playerColor + " é sortudo\n");

                return new PlayerLucky(playerColor);
            default:
                return new PlayerNormal(playerColor);
        }
    }
	public static Player generateRandomLucky(Color playerColor) {
        Player newPlayer = null;
        Random random = new Random();
		switch (random.nextInt(3)) {
            case 0:
            	for(Player p : getPlayers()) {
    				if (p.getColor() == playerColor) {
    			        newPlayer = new PlayerLucky(playerColor);
    			        newPlayer.setPosition(p.getPosition());
    			        System.out.println("Jogador " + playerColor + " teve a sorte mudada para sortudo!");
    			       break;
    			       
    			    }
    			}
                break;
            case 1:
            	for(Player p : getPlayers()) {
            		if (p.getColor() == playerColor) {
    			        newPlayer = new PlayerUnlucky(playerColor);
    			        newPlayer.setPosition(p.getPosition());
    			        System.out.println("Jogador " + playerColor + " teve a sorte mudada para azarado!");
    			       break;
    			       
    			    }
    			}
            	 break;
            case 2:
            	for(Player p : getPlayers()) {
            		if (p.getColor() == playerColor) {
    			        newPlayer = new PlayerNormal(playerColor);
    			        newPlayer.setPosition(p.getPosition());
    			        System.out.println("Jogador " + playerColor + " teve a sorte mudada para normal!");
    			       break;
    			       
    			    }
    			}
           break;
    			    }
       if(newPlayer != null) {
			for(int i = 0;i< getPlayers().size(); i++ ) {
				 if (getPlayers().get(i).getColor() == playerColor) {
		                getPlayers().set(i, newPlayer);
		                break;
		            }
			}
		}
       for(Player p : getPlayers()) {
           if (p.getColor() == playerColor) {
               return p;
           }
           }
       return null;
    			}
       
	 private boolean validateMinimumRequirements() {
	        if (playerList.size() < 2) {
	            return false; 
	        }

	        HashSet<Class<? extends Player>> playerTypes = new HashSet<>();
	        for (Player player : playerList) {
	            playerTypes.add(player.getClass());
	        }
	        return playerTypes.size() >= 2; 
	    }

    public static void listPlayers(boolean listPosition){
        System.out.println("\nPosições atuais:");
    	for(int i = 0; i < playerList.size(); i++){
            System.out.print("\nJogador " + playerList.get(i).getColor());
           
                System.out.println(" Na posição " + playerList.get(i).getPosition());
                if(i == playerList.size()-1 ) {
                	System.out.print("\n");
                
            }
        }
    }
    
    public static ArrayList<Player> getPlayers() {
        return playerList;
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
}