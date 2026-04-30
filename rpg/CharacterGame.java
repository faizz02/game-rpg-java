public abstract class CharacterGame { 
    protected String name;             
    protected int hp;           
    protected int maxHp;              
    protected int baseDamage;         
    
    public CharacterGame(String name, int maxHp, int baseDamage) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseDamage = baseDamage;
    }

    public abstract int attack(); //Abstraksi adalah menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas penting ke pengguna
                //semua karakter bisa attack(), tapi detailnya beda-beda

    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() { //Encapsulation menyembunyikan data class dari akses luar dan hanya membolehkan manipulasi melalui method tertentu.
        return hp;       //Karena ingin melindungi data penting seperti HP, level, dan damage dari perubahan sembarangan.
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setHp(int hp) {
        this.hp = Math.min(maxHp, Math.max(0, hp));
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.hp = Math.min(this.hp, maxHp);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }
}
