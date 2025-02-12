package tile;

import player.NotEnoughCoinException;
import player.Player;
import board.Board;
public abstract class Tile {
    public abstract  void onStep(Player player, Board board) throws NotEnoughCoinException;

}

