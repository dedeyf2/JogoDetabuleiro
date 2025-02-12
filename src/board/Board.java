package board;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import player.NotEnoughCoinException;
import player.Player;
import tile.Tile;
import tile.TileBasic;
import tile.TileChange;
import tile.TileDontPlay;
import tile.TileFinish;
import tile.TileJogaDeNovo;
import tile.TileLucky;
import tile.TileStart;
import tile.TileSwitch;
import tile.TileTroca;

public class Board {
    ArrayList<Tile> tileList = new ArrayList<>();
    int numCasas;
    
    public Board(int numCasas) {
    	this.numCasas = numCasas;
        this.tileList = new ArrayList<>();
        setupBoardBase();
    }
    private void setupBoardBase() {
        for (int i = 0; i < numCasas; i++) {
            tileList.add(new TileBasic());
        }

        tileList.set(0, new TileStart());
        tileList.set(numCasas - 1, new TileFinish());
    }
    public void initializeBoard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Agora você vai configurar as casas especiais do tabuleiro.");
        
        configureTiles(scanner, "TileDontPlay", TileDontPlay.class);
        configureTiles(scanner, "TileChange", TileChange.class);
        configureTiles(scanner, "TileLucky", TileLucky.class);
        configureTiles(scanner, "TileSwitch", TileSwitch.class);
        configureTiles(scanner, "TileJogaDeNovo", TileJogaDeNovo.class);
        configureTiles(scanner, "TileTroca", TileTroca.class);
        
       
        printBoard(); // Exibe o tabuleiro após a configuração
    }
    
    
    
    private void configureTiles(Scanner scanner, String tileName, Class<? extends Tile> tileClass) {
        System.out.println("Digite as posições para " + tileName + " (separadas por espaço), ou pressione Enter para pular:");
        String input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            String[] positions = input.split(" ");
            for (String pos : positions) {
                try {
                    int position = Integer.parseInt(pos);
                    if (position > 0 && position < numCasas - 1) {
                        tileList.set(position, tileClass.getDeclaredConstructor().newInstance());
                    } else {
                        System.out.println("Posição inválida: " + position);
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao configurar " + tileName + " na posição " + pos);
                }
            }
        }
    }
    
    public void printBoard() {
        System.out.println("\nEstado atual do tabuleiro:");
        for (int i = 0; i < tileList.size(); i++) {
            Tile tile = tileList.get(i);
            String tileType = tile.getClass().getSimpleName();
            System.out.println("Casa " + i + ": " + tileType);
        }
    }
    
    
    public ArrayList<Tile> getTiles(){
        return tileList;
    }

    public void stepOnTile(Player player,int numCasas) throws NotEnoughCoinException {
        int playerPosition = player.getPosition();
        if (playerPosition < 0 || playerPosition > numCasas) {
        	 
            return;
        }
        if (playerPosition >= numCasas) {
            player.setPosition(numCasas - 1); 
        }
        
        System.out.println("jogador " + player.getColor() + " pisou na posição " + (playerPosition ));
        Tile tile = tileList.get(playerPosition);

        tile.onStep(player, this);
        
    }
}