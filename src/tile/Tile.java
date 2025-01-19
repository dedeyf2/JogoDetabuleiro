package tile;

import player.Player;

public abstract class Tile {
    public abstract void onStep(Player player, Board board);
    	
}