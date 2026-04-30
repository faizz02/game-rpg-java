import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MapGrid {
    private char[][] map = new char[10][10];
    private JPanel panel = new JPanel(new GridLayout(10, 10));
    private Player player;
    private java.util.function.Consumer<Character> eventCallback;

    public MapGrid(Player player, java.util.function.Consumer<Character> callback) {
        this.player = player;
        this.eventCallback = callback;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void initMap() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                map[i][j] = '.';

        int e = 10;
        while (e-- > 0) {
            int r = rand.nextInt(10), c = rand.nextInt(10);
            if (r == 0 && c == 0) continue;
            map[r][c] = 'E';
        }

        map[9][9] = 'B';
        map[0][0] = 'P';
        player.setPosition(0, 0);
    }

    public void render() {
        panel.removeAll();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                panel.add(new JLabel("" + map[i][j], SwingConstants.CENTER));
        panel.revalidate();
        panel.repaint();
    }

    public void move(String dir) {
        int r = player.getRow(), c = player.getCol(); // ✅ menggunakan getter
        int nr = r, nc = c;

        switch (dir) {
            case "w": nr--; break;
            case "s": nr++; break;
            case "a": nc--; break;
            case "d": nc++; break;
        }

        if (nr < 0 || nc < 0 || nr >= 10 || nc >= 10) return;

        char dest = map[nr][nc];
        map[r][c] = '.';
        player.setPosition(nr, nc);
        map[nr][nc] = 'P';

        if (dest == 'E' || dest == 'B') eventCallback.accept(dest);
    }
}
