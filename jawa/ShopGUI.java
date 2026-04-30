import javax.swing.*;
import java.awt.*;

public class ShopGUI extends JDialog {
    public ShopGUI(JFrame parent, Player player) {
        super(parent, "Toko", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel goldLabel = new JLabel("Goldmu: " + player.getGold(), SwingConstants.CENTER);
        add(goldLabel);

        JButton potionBtn = new JButton("Beli Potion (10 gold)");
        potionBtn.addActionListener(e -> {
            if (player.getGold() >= 10) {
                player.addPotion();
                player.spendGold(10);
                goldLabel.setText("Goldmu: " + player.getGold());
                JOptionPane.showMessageDialog(this, "Potion dibeli!");
            } else {
                JOptionPane.showMessageDialog(this, "Uang tidak cukup!");
            }
        });

        JButton armorBtn = new JButton("Beli Armor (+20 HP) (30 gold)");
        armorBtn.addActionListener(e -> {
            if (player.getGold() >= 30) {
                player.increaseMaxHP(20);
                player.spendGold(30);
                goldLabel.setText("Goldmu: " + player.getGold());
                JOptionPane.showMessageDialog(this, "Armor dibeli!");
            } else {
                JOptionPane.showMessageDialog(this, "Uang tidak cukup!");
            }
        });

        JButton weaponBtn = new JButton("Beli Senjata (+20 DMG) (50 gold)");
        weaponBtn.addActionListener(e -> {
            if (player.getGold() >= 50) {
                player.increaseDamage(20);
                player.spendGold(50);
                goldLabel.setText("Goldmu: " + player.getGold());
                JOptionPane.showMessageDialog(this, "Senjata dibeli!");
            } else {
                JOptionPane.showMessageDialog(this, "Uang tidak cukup!");
            }
        });

        JButton closeBtn = new JButton("Tutup Toko");
        closeBtn.addActionListener(e -> dispose());

        add(potionBtn);
        add(armorBtn);
        add(weaponBtn);
        add(closeBtn);

        setVisible(true);
    }
}
