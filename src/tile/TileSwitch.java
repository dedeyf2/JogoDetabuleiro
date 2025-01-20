package tile;

import java.util.ArrayList;

import game.Game;
import player.Player;

public class TileSwitch extends Tile{

	@Override
	public void onStep(Player player, Board board) {
        int i;
        Player earliestPlayer = player;
        Player selectedPlayer;
        ArrayList<Player> playerList = Game.getPlayers();
		for(i = 0; i < playerList.size(); i++){
            selectedPlayer = playerList.get(i);
            if (earliestPlayer.getPosition() >= player.getPosition()) {
                earliestPlayer = selectedPlayer;
            }
		}
        i = player.getPosition();
        player.setPosition(earliestPlayer.getPosition());
        earliestPlayer.setPosition(i);
        System.out.println("Jogador " + player.getColor() + " na posição " + player.getPosition() + " trocou de lugar com " + earliestPlayer.getColor() + "na posição " + earliestPlayer.getPosition() + "\n");
	}
}
