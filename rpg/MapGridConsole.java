import java.util.Random;
import java.util.function.Consumer;
import java.lang.Character;

public class MapGridConsole {
    private char[][] map = new char[10][10];
    private Player player;
    private Consumer<Character> eventCallback;

    public MapGridConsole(Player player, Consumer<Character> callback) {
        this.player = player;
        this.eventCallback = callback;
    }

    public void initMap() {
        Random rand = new Random();
        // Initialize empty map
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = '.';
            }
        }

        // Place 10 enemies randomly
        int enemies = 10;
        while (enemies-- > 0) {
            int r = rand.nextInt(10), c = rand.nextInt(10);
            if (r == 0 && c == 0) continue; // Skip player starting position
            map[r][c] = 'E';
        }

        // Place boss and player
        map[9][9] = 'B'; // Boss at bottom-right corner
        map[0][0] = 'P'; // Player at top-left corner
        player.setPosition(0, 0);
    }

    public void render() {
        System.out.println("\nMAP LEGEND:");
        System.out.println("P = You");
        System.out.println("E = Enemy");
        System.out.println("B = Boss");
        System.out.println(". = Empty space\n");
        
        System.out.println("CURRENT MAP:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void move(String dir) {
        int r = player.getRow(), c = player.getCol();
        int nr = r, nc = c;

        switch (dir) {
            case "w": nr--; break;
            case "s": nr++; break;
            case "a": nc--; break;
            case "d": nc++; break;
        }

        if (nr < 0 || nc < 0 || nr >= 10 || nc >= 10) {
            System.out.println("Can't move there - you've reached the edge of the map!");
            return;
        }

        char dest = map[nr][nc];
        map[r][c] = '.';
        player.setPosition(nr, nc);
        map[nr][nc] = 'P';

        if (dest == 'E' || dest == 'B') {
            eventCallback.accept(Character.valueOf(dest));
        }
    }
}