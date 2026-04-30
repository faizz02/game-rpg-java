public class Player {
    private String name;
    private int hp = 70;
    private int maxHp = 70;
    private int exp = 0;
    private int level = 1;
    private int gold = 100;
    private int baseDamage = 10;
    private int potions = 3;
    private int row = 0, col = 0;

    public Player(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int attack() {
        return baseDamage;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public boolean usePotion() {
        if (potions > 0 && hp < maxHp) {
            hp = Math.min(hp + 30, maxHp);
            potions--;
            return true;
        }
        return false;
    }

    public void gainExp(int amount) {
        exp += amount;
        if (exp >= level * 100) {
            exp -= level * 100;
            level++;
            maxHp += 10;
            baseDamage += 5;
            hp = maxHp;
            gold += 20;
        }
    }

    public int getGold() {
        return gold;
    }

    public void spendGold(int amount) {
        gold -= amount;
    }

    public void addPotion() {
        potions++;
    }

    public void increaseMaxHP(int amount) {
        maxHp += amount;
        hp = maxHp;
    }

    public void increaseDamage(int amount) {
        baseDamage += amount;
    }

    public int getDamage() {
        return baseDamage;
    }

    public String getStats() {
        return String.format("HP: %d/%d | Level: %d | EXP: %d | Gold: %d | Potion: %d", 
            hp, maxHp, level, exp, gold, potions);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getHp() {
        return hp;
    }
}
