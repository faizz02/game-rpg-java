import java.util.Scanner;

public class Shop {
    public static void open(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n=== TOKO ===");
            System.out.println("1. Potion (10 gold)");
            System.out.println("2. Armor (+20 Max HP) (30 gold)");
            System.out.println("3. Senjata Kuat (+20 Damage) (50 gold)");
            System.out.println("4. Keluar Toko");
            System.out.print("Pilih item: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (player.getGold() >= 10) {
                        player.addPotion();
                        player.spendGold(10);
                        System.out.println("Kamu membeli 1 potion.");
                    } else {
                        System.out.println("Uangmu tidak cukup.");
                    }
                    break;
                case 2:
                    if (player.getGold() >= 30) {
                        player.increaseMaxHP(20);
                        player.spendGold(30);
                        System.out.println("Kamu membeli armor.");
                    } else {
                        System.out.println("Uangmu tidak cukup.");
                    }
                    break;
                case 3:
                    if (player.getGold() >= 50) {
                        player.increaseDamage(20);
                        player.spendGold(50);
                        System.out.println("Kamu membeli senjata kuat.");
                    } else {
                        System.out.println("Uangmu tidak cukup.");
                    }
                    break;
                case 4:
                    shopping = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
