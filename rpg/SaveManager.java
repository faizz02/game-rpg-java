import java.io.*;
import java.util.*;

public class SaveManager {
    private static final String FILE = "save.dat";

    public static void save(Player player, MapGridConsole map) {
        try (PrintWriter out = new PrintWriter(FILE)) {
            out.println(player.getRow() + " " + player.getCol());
            out.println(player.getHp());
            out.println(player.getMaxHp());
            out.println(player.getExp());
            out.println(player.getLevel());
            out.println(player.getGold());
            out.println(player.getDamage());
            out.println(player.getPotions());
            System.out.println("Game saved successfully!");
        } catch (Exception e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    public static void load(Player player, MapGridConsole map) {
        try (Scanner sc = new Scanner(new File(FILE))) {
            int r = sc.nextInt(), c = sc.nextInt();
            int hp = sc.nextInt();
            int maxHp = sc.nextInt();
            int exp = sc.nextInt();
            int level = sc.nextInt();
            int gold = sc.nextInt();
            int damage = sc.nextInt();
            int potions = sc.nextInt();
            
            player.setPosition(r, c);
            player.setHp(hp);
            player.setMaxHp(maxHp);
            player.setExp(exp);
            player.setLevel(level);
            player.setGold(gold);
            player.setDamage(damage);
            player.setPotions(potions);
            
            System.out.println("Game loaded successfully!");
        } catch (Exception e) {
            System.out.println("Failed to load game: " + e.getMessage());
        }
    }
}