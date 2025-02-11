package tile;

import java.util.Scanner;

import board.Board;
import player.Player;

public class TileBasic extends Tile {
    public void onStep(Player player, Board board){
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("digite enter para continuar ...");
		scanner.nextLine();
    }
}