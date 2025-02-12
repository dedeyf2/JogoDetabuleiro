package game;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import board.Board;
import player.NotEnoughCoinException;
import player.Player;
import player.PlayerLucky;
import player.PlayerNormal;
import player.PlayerUnlucky;
import player.enums.Color;

public class Game {
	
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	Scanner scanner = new Scanner(System.in);
	private static final int MAX_PLAYERS = 6;
	private Board board;
	private boolean debugMode;
	private static int numCasas;
	
	
	public Game(int numCasas) {
		this.numCasas = numCasas;
        setNumCasas(numCasas);
		this.board = new Board(numCasas);
		board.initializeBoard();
	}
	
	 public static int getNumCasas() {
		return numCasas;
	}

	public void setNumCasas(int numCasas) {
		this.numCasas = numCasas;
	}

	public void configTabuleiro(int numCasas) {
	        this.board = new Board(numCasas); // Ajuste para permitir diferentes tamanhos de tabuleiro
	    }
	 
	public void setupPlayers(){
        
        Random random = new Random();
        System.out.print("Digite a cor de cada jogador(RED, BLUE, GREEN, YELLOW, BLACK, WHITE) seguido pela tecla ENTER. \n");
       
        
        while (true) {
        	 if (playerList.size() >= MAX_PLAYERS) {
                 System.out.println("Número máximo de jogadores alcançado.");
                 break;
        	 }
        	
        	
            String input = scanner.nextLine();
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
	 public void printTabuleiro() {
	       // board.printBoard();
	    }
	 public void setDebugMode(Scanner input) {
	        System.out.println("Vai ser no modo debug ou não? (y/n)");

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
	            this.debugMode = true;
	        } else {
	            System.out.println("Você escolheu modo normal.");
	            this.debugMode = false;
	        }
	    }

	    public boolean isDebugMode() {
	        return debugMode;
	    }
	
	 public void start() throws NotEnoughCoinException {
		 setupPlayers();
		 definePlayerOrder(playerList, scanner);
		 setDebugMode(scanner);
		 boolean winCondition = false;
		 while (!winCondition) {
	            for (Player currentPlayer : playerList) {
	                TurnController.executeTurn(currentPlayer, debugMode, scanner);
	                if(!currentPlayer.getImprisoned()) {
	                	currentPlayer.setTimesPlayed(1);
	                }
	                
	                board.stepOnTile(currentPlayer,numCasas);
	                if (currentPlayer.getPosition() >= numCasas -1 ) {
	                    winCondition = true;
	                    break;
	                }
	            }
	            if (!winCondition) {
	                Game.listPlayers(true);
	                System.out.println("Pressione Enter para continuar...");
	                scanner.nextLine();
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

	 public static void listPlayers(boolean listPosition) {
		    System.out.println("\nPosições atuais:");

		    for (int i = 0; i < playerList.size(); i++) {
		        Player player = playerList.get(i);
		        System.out.print("\nJogador " + player.getColor());

		        if (listPosition) {
		            System.out.print(" está na posição " + player.getPosition());
		        }

		        System.out.println(" | Moedas: " + player.getCoin());

		        if (i == playerList.size() - 1) {
		            System.out.print("\n");
		        }
		    }
		}
    
    public static ArrayList<Player> getPlayers() {
        return playerList;
    }
    public static void definePlayerOrder(ArrayList<Player> playerList, Scanner input) {
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

        // Atualiza playerList com a nova ordem
        playerList.clear();
        playerList.addAll(orderedPlayers);

        System.out.println("Ordem dos jogadores definida:");
        for (Player player : playerList) {
            System.out.println(player.getColor().name());
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

