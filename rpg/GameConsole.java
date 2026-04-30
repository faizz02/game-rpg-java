import java.util.Scanner;
import java.util.function.Consumer;

public class GameConsole {
    private Player player;
    private MapGridConsole mapGrid;
    private Scanner scanner;
    
    public GameConsole() {
        scanner = new Scanner(System.in);
        player = new Player("Hero");
        mapGrid = new MapGridConsole(player, this::handleEvent);
        
        System.out.println("=== RPG GAME ===");
        System.out.println("Welcome, " + player.getName() + "!");
        System.out.println("Your adventure begins...\n");
        
        mapGrid.initMap();
        showGameState();
        gameLoop();
    }
    
    private void showGameState() {
        mapGrid.render();
        System.out.println(player.getStats());
        System.out.println("Commands: [W]Up [A]Left [S]Down [D]Right [P]Potion [O]Shop [I]Save [L]Load [K]Status [Q]Quit");
        System.out.print("Enter command: ");
    }
    
    private void gameLoop() {
        while (true) {
            String cmd = scanner.nextLine().toLowerCase();
            
            if (cmd.equals("q")) {
                System.out.println("Game ended.");
                break;
            } else if (cmd.equals("p")) {
                if (player.usePotion()) {
                    System.out.println("Used potion!");
                } else {
                    System.out.println("No potion or HP full.");
                }
            } else if (cmd.equals("o") && cmd.length() == 1) {
                new ShopConsole(player);
            } else if (cmd.equals("i")) {
                SaveManager.save(player, mapGrid);
                System.out.println("Game saved.");
            } else if (cmd.equals("l")) {
                SaveManager.load(player, mapGrid);
                System.out.println("Game loaded.");
            } else if (cmd.equals("k")) {
                System.out.println("\n=== PLAYER STATUS ===");
                System.out.println(player.getStats());
            } else if ("wasd".contains(cmd) && cmd.length() == 1) {
                mapGrid.move(cmd);
            } else {
                System.out.println("Invalid command!");
            }
            
            showGameState();
        }
    }
    
    private void handleEvent(Character tile) {
        if (tile.equals('E') || tile.equals('B')) {
            Enemy enemy = new Enemy(tile.equals('B'), player.getLevel());
            new BattleConsole(this, player, enemy).start();

            if (player.isAlive()) {
                int expGain = 20 + (int)(Math.random() * 31);
                int goldGain = 10 + (int)(Math.random() * 21);
                player.gainExp(expGain);
                player.spendGold(-goldGain);
                System.out.println("Victory! Gained " + expGain + " EXP and " + goldGain + " gold.");
            }
        }
    }
    
    public void log(String text) {
        System.out.println(text);
    }
}