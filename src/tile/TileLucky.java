package tile;
import java.util.Scanner;

import board.Board;
import player.NotEnoughCoinException;
import player.Player;
import player.PlayerUnlucky;

public class TileLucky extends Tile{
	Scanner scanner = new Scanner(System.in);
	@Override
    public void onStep(Player player, Board board) throws NotEnoughCoinException{
        if(player instanceof PlayerUnlucky) {
        	System.out.println(player.getColor() +" é azarado,não ganhou moeda!");
        	System.out.println("digite enter para continuar ...");
    		scanner.nextLine();
        	return;
        }
        System.out.println("A sorte está com você! " + player.getColor() + " move 3 espaços a frente e ganha 3 moedas a mais.");
        player.movePosition(3);
        player.acquireCoin(3);
       
		System.out.println("digite enter para continuar ...");
		scanner.nextLine();
    }
}