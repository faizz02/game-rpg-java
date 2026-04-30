import java.io.*;
import java.util.*;

public class SaveManager {
    private static final String FILE = "save.dat";

    public static void save(Player player, MapGrid map) {
        try (PrintWriter out = new PrintWriter(FILE)) {
            out.println(player.getRow() + " " + player.getCol());
            out.println(player.getHp());
            // Tambahkan penyimpanan lain jika dibutuhkan (level, exp, gold, dst.)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(Player player, MapGrid map) {
        try (Scanner sc = new Scanner(new File(FILE))) {
            int r = sc.nextInt(), c = sc.nextInt();
            int hp = sc.nextInt();
            player.setPosition(r, c);
            // Set lebih banyak state pemain jika kamu menyimpan lainnya
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
