package board;
import java.lang.reflect.Array;
import java.util.ArrayList;

import player.Player;
import tile.Tile;
import tile.TileBasic;
import tile.TileBeginning;
import tile.TileChange;
import tile.TileDontPlay;
import tile.TileFinish;
import tile.TileLucky;
import tile.TileStart;
import tile.TileSwitch;

public class Board {
    ArrayList<Tile> tileList = new ArrayList<>();
    public Board() {
        for (int i = 0; i < 41; i++) {
            int tilePosition = i ; //para que possamos inserir tiles como a gente normalmente pensa neles, 
            //começando no 1 e terminando no 40, mesmo que sejam do 0 ao 39 no código
            if (tilePosition == 0) {
                tileList.add(new TileStart());
            }else if (tilePosition == 10 || tilePosition == 25 || tilePosition == 38) {
                tileList.add(new TileDontPlay());
            }else  if (tilePosition == 13) {
            	tileList.add(new TileChange());
            }else if( tilePosition == 5 ||tilePosition == 15 ||tilePosition == 30 ) {
            	tileList.add(new TileLucky());
            }else if( tilePosition == 17 ||tilePosition == 27) {
            	tileList.add(new TileBeginning());
            }else if(tilePosition == 20 ||tilePosition == 35) {
            	tileList.add(new TileSwitch());
            }else if(tilePosition == 40 ) {
            	tileList.add(new TileFinish());
            }else{
                tileList.add(new TileBasic());
            }
        }
    }
    public ArrayList<Tile> getTiles(){
        return tileList;
    }

    public void stepOnTile(Player player) {
        int playerPosition = player.getPosition();
        if (playerPosition < 0 || playerPosition > 40) {
            
            return;
        }
        if (playerPosition > 40) {
            player.setPosition(40); 
        }
        System.out.println("jogador " + player.getColor() + " pisou na posição " + (playerPosition ));
        Tile tile = tileList.get(playerPosition);

        tile.onStep(player, this);
    }
}