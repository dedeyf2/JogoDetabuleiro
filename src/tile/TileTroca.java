package tile;

import player.Player;
import player.enums.PlayerItem;
import board.Board;
import java.util.Scanner;

public class TileTroca extends Tile {
    @Override
    public void onStep(Player player, Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jogador " + player.getColor() + " caiu na casa de Troca!");
        System.out.println("Escolha um item para equipar:");
        System.out.println("1 - Boné (+3 moedas, +1 casa)");
        System.out.println("2 - Moletom (+4 moedas, +2 casas)");
        System.out.println("3 - Óculos Escuros (+5 moedas, +3 casas)");
        System.out.print("Digite o número do item: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        switch (choice) {
            case 1 -> player.acquireItem(PlayerItem.BONE);
            case 2 -> player.acquireItem(PlayerItem.MOLETON);
            case 3 -> player.acquireItem(PlayerItem.OCULOS);
            default -> System.out.println("Escolha inválida! Nenhum item foi adquirido.");
        }
    }
}
