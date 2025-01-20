package tile;

import java.util.Scanner;

import player.Player;

public class TileDontPlay extends Tile{
	@Override
	public void onStep(Player player, Board board) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
}

}