package tile;

import player.Player;
import board.Board;
import player.NotEnoughCoinException;

public class TileJogaDeNovo extends Tile {
    @Override
    public void onStep(Player player, Board board) throws NotEnoughCoinException {
        System.out.println("Jogador " + player.getColor() + " caiu na casa Joga De Novo!");
        
        int[] diceArray = player.rollDice();
        int sumDice = diceArray[0] + diceArray[1];

        System.out.println("Jogador " + player.getColor() + " rolou os dados e obteve " + diceArray[0] + " e " + diceArray[1] + ".");
        System.out.println("Avançará " + sumDice + " casas e receberá " + sumDice + " moedas!");

        
        player.movePosition(sumDice);

        
        player.acquireCoin(sumDice);
    }
}
