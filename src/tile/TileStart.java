package tile;

import java.util.Scanner;

import player.Player;

public class TileStart extends Tile {

	@Override
	public void onStep(Player player, Board board) {
		System.out.println("você esta no começo,posição 0");
		Scanner scanner = new Scanner(System.in);
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
	}

}