import javax.swing.*;

public class Battle {
    private Game game;
    private Player player;
    private Enemy enemy;
    private JTextArea output;

    public Battle(Game game, Player player, Enemy enemy, JTextArea output) {
        this.game = game;
        this.player = player;
        this.enemy = enemy;
        this.output = output;
    }

    public void start() {
        while (enemy.isAlive() && player.isAlive()) {
            String act = JOptionPane.showInputDialog("Choose [A]ttack, [D]efend, [P]otion").toLowerCase();
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
