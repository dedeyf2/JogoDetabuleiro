package tile;

import java.util.Scanner;
import board.Board;
import player.Player;

public class TileDontPlay extends Tile {
    @Override
    public void onStep(Player player, Board board) {
        if (!player.getImprisoned()) { // Só prende se o jogador ainda não estiver preso
            System.out.println("Jogador caiu em uma casa de prisão!");
            player.setImprisoned(true);
            System.out.println("Você ficará preso por 2 rodadas ou pode pagar 2 moedas na próxima rodada para sair.");
        }
    }
}