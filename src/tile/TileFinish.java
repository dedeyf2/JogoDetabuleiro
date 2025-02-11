package tile;



import board.Board;
import player.Player;

public class TileFinish extends Tile{

	@Override
	public void onStep(Player player, Board board) {
		System.out.println("Jogador " + player.getColor() + " ganhou!");
		
		
	}

}