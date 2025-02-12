package tile;



import java.text.BreakIterator;

import board.Board;
import game.Game;
import player.Player;

public class TileFinish extends Tile{

	@Override
	public void onStep(Player player, Board board) {
		System.out.println("Jogador " + player.getColor() + " ganhou!");
		listPlayersStats();
		
	}
	public static void listPlayersStats() {
	    System.out.println("\nEstatísticas dos jogadores:");

	    for (int i = 0; i < Game.playerList.size(); i++) {
	        Player player = Game.playerList.get(i);
	        System.out.println("Jogador " + player.getColor() + 
	                           " | Posição: " + player.getPosition() + 
	                           " | Moedas: " + player.getCoin() + 
	                           " | Rodadas jogadas: " + player.getTimesPlayed());
	    }

	    System.out.println();
	}
}