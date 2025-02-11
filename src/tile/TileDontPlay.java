package tile;

import java.util.Scanner;

import board.Board;
import player.Player;

public class TileDontPlay extends Tile{
	@Override
	public void onStep(Player player, Board board) {
		Scanner scanner = new Scanner(System.in);
		if (player.getImprisoned()) {
			player.setImprisoned(false);;
			return;
		}
		player.setImprisoned(true);
		System.out.println("Jogador pisou no lugar errado! Ele não irá jogar a próxima rodada.");
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
}

}