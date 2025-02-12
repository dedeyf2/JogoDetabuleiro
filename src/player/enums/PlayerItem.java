package player.enums;

public enum PlayerItem {
    BONE(3, 1), 
    MOLETON(4, 2), 
    OCULOS(5, 3);

    private final int coinBonus;
    private final int moveBonus;

    PlayerItem(int coinBonus, int moveBonus) {
        this.coinBonus = coinBonus;
        this.moveBonus = moveBonus;
    }

    public int getCoinBonus() {
        return coinBonus;
    }

    public int getMoveBonus() {
        return moveBonus;
    }
}