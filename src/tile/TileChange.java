package tile;


import java.util.Scanner;

import board.Board;
import player.Player;
import game.Game;
import player.PlayerLucky;
import player.PlayerNormal;
import player.PlayerUnlucky;
import player.enums.Color;

public class TileChange extends Tile{

	@Override
	public void onStep(Player player, Board board) {
		// muda a sorte;
		System.out.println("você caiu na casa surpresa,sua sorte poderá ser mudada!");
		System.out.println("escolha as cartas de 1 a 3 e decida sua sorte!");
		System.out.println();
		System.out.println("┌─────────┐");
		System.out.println("│ J       │");
		System.out.println("│         │");
		System.out.println("│    ♠    │");
		System.out.println("│         │");
		System.out.println("│       J │");
		System.out.println("└─────────┘");
		System.out.println();
		System.out.println("┌─────────┐");
		System.out.println("│ Q       │");
		System.out.println("│         │");
		System.out.println("│    ♠    │");
		System.out.println("│         │");
		System.out.println("│       Q │");
		System.out.println("└─────────┘");
		System.out.println();
		System.out.println("┌─────────┐");
		System.out.println("│ K       │");
		System.out.println("│         │");
		System.out.println("│    ♠    │");
		System.out.println("│         │");
		System.out.println("│       K │");
		System.out.println("└─────────┘");
		System.out.println();
		Scanner scanner = new Scanner(System.in) ;
		int carta = 1;
		if (scanner.hasNextInt()){
			carta = scanner.nextInt();
			 scanner.nextLine();
		}
		
		 while (carta < 1 || carta > 3) {
	            System.out.println("Escolha inválida! Por favor, escolha um número entre 1 e 3.");
	            carta = scanner.nextInt();
	            scanner.nextLine();
	        }
		 switch(carta) {
		 case 1:
			 System.out.println("você escolheu o valete de espadas!");
			 break;
		 case 2:
			 System.out.println("você escolheu a rainha de espadas!");
			 break;	
		 case 3:
			 System.out.println("você escolheu o rei de espadas!");
			 break;
		 
		 }
		
		
	
		Game.generateRandomLucky(player.getColor());
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
		}
	
	}
	