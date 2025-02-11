package game;

import board.Board;
import java.util.ArrayList;
import java.util.Scanner;

import player.Player;

public class Main {
    public static void main(String[] args) {
        int numCasas;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de casas no tabuleiro: ");
        numCasas = scanner.nextInt();
        Game game = new Game(numCasas);
        scanner.nextLine();
        game.configTabuleiro(numCasas);
        game.start();
        
        
        
        
        
		/* Board board = new Board(numCasas);
        
        game.setupPlayers();
        Game.listPlayers(false);
        
       
        ArrayList<Player> playerList = Game.getPlayers();
        playerList = Game.definePlayerOrder(playerList, scanner);
       */
        
    }
    
}
