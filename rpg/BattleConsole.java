import java.util.Scanner;

public class BattleConsole {
    private GameConsole game;
    private Player player;
    private Enemy enemy;
    private Scanner scanner;

    public BattleConsole(GameConsole game, Player player, Enemy enemy) {
        this.game = game;
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("\n=== BATTLE ===");
        System.out.println("You encountered a " + enemy.getName() + "!");

        while (enemy.isAlive() && player.isAlive()) {
            System.out.println("\n" + player.getStats());
            System.out.println(enemy.getName() + " HP: " + enemy.getHp());
            System.out.print("Choose [A]ttack, [D]efend, [P]otion: ");
            String act = scanner.nextLine().toLowerCase();

            if (act.equals("a")) {
                int dmg = player.attack();
                enemy.takeDamage(dmg);
                game.log("You hit " + enemy.getName() + " for " + dmg + " damage. (" + Math.max(0, enemy.getHp()) + " HP)");
            } else if (act.equals("d")) {
                int dmg = enemy.attack() / 2;
                player.takeDamage(dmg);
                game.log("You defend! Enemy hits for " + dmg + " damage.");
                continue;
            } else if (act.equals("p")) {
                if (player.usePotion()) game.log("Used potion!");
                else game.log("No potion!");
                continue;
            }

            if (enemy.isAlive()) {
                int dmg = enemy.attack();
                player.takeDamage(dmg);
                game.log(enemy.getName() + " hits you for " + dmg + " damage.");
            }
        }

        if (player.isAlive()) {
            game.log("You defeated " + enemy.getName() + "!");
            player.gainExp(30);
            if (Math.random() < 0.5) {
                player.addPotion();
                game.log("You found a potion!");
            }
        } else {
            game.log("You were defeated...");
            System.exit(0);
        }
    }
}