package tile;

import java.util.Scanner;

import board.Board;
import player.NotEnoughCoinException;
import player.Player;
import player.PlayerNormal;
import player.PlayerUnlucky;

public class TileUnlucky {
	public void onStep(Player player, Board board) throws NotEnoughCoinException{
    	Scanner scanner = new Scanner(System.in);
    	System.out.println(player.getColor() + "caiu na casa azarada!");
    	 if(player instanceof PlayerUnlucky || player instanceof PlayerNormal) {
         	System.out.println(player.getColor() + " perde 3 moedas");
         	player.acquireCoin(-3);
    		System.out.println("digite enter para continuar ...");
     		scanner.nextLine();
     		
         	return;
         }
    	System.out.println("digite enter para continuar ...");
		scanner.nextLine();
    }
}
