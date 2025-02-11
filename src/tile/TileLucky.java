package tile;
import java.util.Scanner;

import board.Board;
import player.Player;
import player.PlayerUnlucky;

public class TileLucky extends Tile{
	Scanner scanner = new Scanner(System.in);
	@Override
    public void onStep(Player player, Board board){
        if(player instanceof PlayerUnlucky) {
        	System.out.println("digite enter para continuar ...");
    		scanner.nextLine();
        	return;
        }
        System.out.println("A sorte está com você! " + player.getColor() + " move 3 espaços a frente.");
        player.movePosition(3);
       
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
    }
}