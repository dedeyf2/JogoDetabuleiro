package tile;

import player.Player;

public class TileDontPlay extends Tile{
	@Override
	public void onStep(Player player, Board board) {
	// não joga o proximo;
}

}