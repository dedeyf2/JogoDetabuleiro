package tile;

import java.util.Scanner;

import board.Board;
import player.NotEnoughCoinException;
import player.Player;

public class TileBasic extends Tile {
    public void onStep(Player player, Board board) throws NotEnoughCoinException{
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("uma moeda adicionada ao player " + player.getColor());
    	player.acquireCoin(1);
    	System.out.println("digite enter para continuar ...");
		scanner.nextLine();
    }
}