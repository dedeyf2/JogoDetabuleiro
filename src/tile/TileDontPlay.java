package tile;

import player.Player;

public class TileDontPlay extends Tile{
	@Override
	public void onStep(Player player, Board board) {
		player.setStunned(true);
		System.out.println("Jogador pisou no lugar errado! Ele não irá jogar a próxima rodada.");
	}

}