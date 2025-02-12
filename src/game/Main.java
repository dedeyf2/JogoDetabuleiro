package game;

import board.Board;
import java.util.ArrayList;
import java.util.Scanner;

import player.NotEnoughCoinException;
import player.Player;

public class Main {
    public static void main(String[] args) throws NotEnoughCoinException {
        int numCasas;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de casas no tabuleiro: ");
        numCasas = scanner.nextInt();
        scanner.nextLine();
        Game game = new Game(numCasas);
        
        game.configTabuleiro(numCasas);
        game.start();
   
    }
    
}
