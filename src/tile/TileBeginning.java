package tile;

import java.util.Scanner;

import game.Game;
import player.Player;
import player.enums.Color;

public class TileBeginning extends Tile {
	Scanner scanner = new Scanner(System.in);
	@Override
	public void onStep(Player player, Board board) {
		// escolhe o player para ir ao inicio;
		System.out.println("digite a cor do Jogador que você gostaria de mandar para o inicio!");
		String inputBeginning = scanner.nextLine();
		Color playerBeginning = Color.valueOf(inputBeginning.toUpperCase());
		try {
			for(Player p : Game.getPlayers()) {
				if (p.getColor() == playerBeginning) {
			        p.setPosition(0);
			        System.out.println("Jogador " + playerBeginning + " foi movido para a posição inicial!");
			        break;
			    }
			}
		} catch (IllegalArgumentException e) {
			System.out.println("cor não existe!");
		}
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
	}

}