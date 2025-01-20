package tile;

import java.util.Scanner;

import player.Player;

public class TileSwitch extends Tile{

	@Override
	public void onStep(Player player, Board board) {
		int i;

		for(i = 0; i < board.getTiles().size(); i++){
			//pegar quais players tem em cada posição e pegar o primeiro?
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
	}
}