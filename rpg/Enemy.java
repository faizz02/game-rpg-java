public class Enemy extends CharacterGame { 
    private boolean isBoss;

    public Enemy(boolean isBoss, int playerLevel) {
        super(
            isBoss ? "Boss" : "Enemy",
            (isBoss ? 80 : 40) + (playerLevel * 5),      // HP meningkat per level
            (isBoss ? 20 : 10) + (playerLevel * 2)       // Damage meningkat per level
        );
        this.isBoss = isBoss;
    }

    @Override
    public int attack() {
        return this.baseDamage + (int)(Math.random() * 5);
    }

    public boolean isBoss() {
        return isBoss;
    }
}
