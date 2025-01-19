package tile;
import player.Player;
import player.PlayerUnlucky;

public class TileLucky extends Tile{
	@Override
    public void onStep(Player player, Board board){
        if(player instanceof PlayerUnlucky) {
            return;
        }
        System.out.println("A sorte está com você! " + player.getColor() + " move 3 espaços a frente.");
        player.movePosition(3);
    }
}