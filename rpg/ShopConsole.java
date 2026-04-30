import java.util.Scanner;

public class ShopConsole {
    private Player player;
    private Scanner scanner;

    public ShopConsole(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
        open();
    }

    public void open() {
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n=== SHOP ===");
            System.out.println("Gold: " + player.getGold());
            System.out.println("1. Potion (10 gold)");
            System.out.println("2. Armor (+20 Max HP) (30 gold)");
            System.out.println("3. Strong Weapon (+20 Damage) (50 gold)");
            System.out.println("4. Exit Shop");
            System.out.print("Choose item: ");
            
            String input = scanner.nextLine();
            if (!input.matches("[1-4]")) {
                System.out.println("Invalid choice!");
                continue;
            }
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    if (player.getGold() >= 10) {
                        player.addPotion();
                        player.spendGold(10);
                        System.out.println("You bought 1 potion.");
                    } else {
                        System.out.println("Not enough gold.");
                    }
                    break;
                case 2:
                    if (player.getGold() >= 30) {
                        player.increaseMaxHP(20);
                        player.spendGold(30);
                        System.out.println("You bought armor.");
                    } else {
                        System.out.println("Not enough gold.");
                    }
                    break;
                case 3:
                    if (player.getGold() >= 50) {
                        player.increaseDamage(20);
                        player.spendGold(50);
                        System.out.println("You bought a strong weapon.");
                    } else {
                        System.out.println("Not enough gold.");
                    }
                    break;
                case 4:
                    shopping = false;
                    break;
            }
        }
    }
}