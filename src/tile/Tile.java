package tile;

import player.Player;
import board.Board;
public abstract class Tile {
    public abstract  void onStep(Player player, Board board);

}

