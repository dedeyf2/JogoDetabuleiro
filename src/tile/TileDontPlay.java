package tile;

import java.util.Scanner;

import player.Player;

public class TileDontPlay extends Tile{
	@Override
	public void onStep(Player player, Board board) {
		Scanner scanner = new Scanner(System.in);
		player.setStunned(true);
		System.out.println("Jogador pisou no lugar errado! Ele não irá jogar a próxima rodada.");
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
}

}