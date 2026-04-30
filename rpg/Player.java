public class Player extends CharacterGame { //Inheritance memungkinkan sebuah class  untuk mewarisi atribut dan method dari class lain
    private int exp;                        //Karena Player dan Enemy adalah sama-sama karakter yang memiliki atribut dasar yang mirip, seperti HP, serangan, dll.
    private int level;
    private int gold;
    private int potions;
    private int row;
    private int col;

    public Player(String name) {
        super(name, 70, 10);
        this.exp = 0;
        this.level = 1;
        this.gold = 100;
        this.potions = 3;
        this.row = 0;
        this.col = 0;
    }

    // Position methods
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Battle methods
    @Override
    public int attack() {       //Polimorfisme memungkinkan objek yang memiliki parent class yang sama untuk merespons secara berbeda terhadap method yang sama.
        return baseDamage;      //Supaya kita bisa menulis kode seperti attack() tanpa peduli apakah itu Player, Enemy, atau jenis karakter lainnya.
    }

    public boolean usePotion() {
        if (potions > 0 && hp < maxHp) {
            heal(30);
            potions--;
            return true;
        }
        return false;
    }

    public void addPotion() {
        potions++;
    }

    // Experience and level methods
    public void gainExp(int amount) {
        exp += amount;
        if (exp >= level * 100) {
            levelUp();
        }
    }

    private void levelUp() {
        exp -= level * 100;
        level++;
        setMaxHp(maxHp + 10);
        baseDamage += 5;
        hp = maxHp; // Full heal on level up
        gold += 20;
    }

    // Gold methods
    public int getGold() {
        return gold;
    }

    public void spendGold(int amount) {
        gold -= amount;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    // Shop methods
    public void increaseMaxHP(int amount) {
        setMaxHp(maxHp + amount);
    }

    public void increaseDamage(int amount) {
        baseDamage += amount;
    }

    // Getters and setters
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public int getDamage() {
        return baseDamage;
    }

    public void setDamage(int damage) {
        this.baseDamage = damage;
    }

    public String getStats() {
        return String.format("HP: %d/%d | Level: %d | EXP: %d | Gold: %d | Potion: %d", 
            hp, maxHp, level, exp, gold, potions);
    }
}