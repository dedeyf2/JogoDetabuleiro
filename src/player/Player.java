package player;

import java.util.Random;

import player.enums.Color;
import java.util.Random;

public abstract class Player {
    protected Color color;
    protected int position = 0;
    protected int timesPlayed;
    protected boolean stunned;

    public Player(Color color){
        this.color = color;
        position = 0;
        stunned = false;
    }

    public Color getColor() {
        return color;
    }
    public int getPosition() {
        return position;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void movePosition(int value) {
        position += value;
        if (position > 40) {
            position = 40; 
        }
    }
    public boolean getStunned(){
        return stunned;
    }

    public void setStunned(boolean stunned){
        this.stunned = stunned;
    }

    public abstract int[] rollDice();
}
    
    

    