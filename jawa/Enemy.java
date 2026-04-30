public class Enemy {
    private int hp;
    private String name;

    public Enemy(boolean isBoss) {
        if (isBoss) {
            hp = 100;
            name = "Boss";
        } else {
            hp = 70;
            name = "Enemy";
        }
    }

    public int attack() {
        return (int)(Math.random() * 10) + 3;
    }

    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }
}
