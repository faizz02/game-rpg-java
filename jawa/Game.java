import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JFrame implements KeyListener {
    private MapGrid mapGrid;
    private Player player;
    private JTextArea output;
    private Random random = new Random();

    public Game() {
        setTitle("RPG GUI Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        player = new Player("Hero");
        mapGrid = new MapGrid(player, this::handleEvent);

        add(mapGrid.getPanel(), BorderLayout.CENTER);

        output = new JTextArea(6, 30);
        output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        JPanel controls = new JPanel();
        String[] cmds = {"W", "A", "S", "D", "Potion", "Shop", "Save", "Load", "Status", "Quit"};
        for (String c : cmds) {
            JButton b = new JButton(c);
            b.addActionListener(e -> handleCommand(c.toLowerCase()));
            controls.add(b);
        }
        add(controls, BorderLayout.NORTH);

        log("Welcome to the RPG Game!");
        mapGrid.initMap();
        mapGrid.render();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }

    private void handleCommand(String cmd) {
        if (cmd.equals("quit")) {
            log("Game ended.");
            System.exit(0);
        } else if (cmd.equals("potion")) {
            if (player.usePotion()) {
                log("Used potion!");
            } else {
                log("No potion or HP full.");
            }
        } else if (cmd.equals("shop")) {
            new ShopGUI(this, player);
            log("Keluar dari toko.");
            requestFocusInWindow();
        } else if (cmd.equals("save")) {
            SaveManager.save(player, mapGrid);
            log("Game saved.");
        } else if (cmd.equals("load")) {
            SaveManager.load(player, mapGrid);
            mapGrid.render();
            log("Game loaded.");
        } else if (cmd.equals("status")) {
            JOptionPane.showMessageDialog(this, player.getStats(), "Status Pemain", JOptionPane.INFORMATION_MESSAGE);
        } else {
            mapGrid.move(cmd);
        }

        mapGrid.render();
        log(player.getStats());
    }

    private void handleEvent(char tile) {
        if (tile == 'E' || tile == 'B') {
            Enemy enemy = new Enemy(tile == 'B');
            new Battle(this, player, enemy, output).start();

            if (player.isAlive()) {
                int expGain = 20 + random.nextInt(31);
                int goldGain = 10 + random.nextInt(21);
                player.gainExp(expGain);
                player.spendGold(-goldGain);
                log("Menang! Dapat " + expGain + " EXP dan " + goldGain + " gold.");
            }
            requestFocusInWindow();
        }
    }

    public void log(String text) {
        output.append(text + "\n");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> handleCommand("w");
            case KeyEvent.VK_A -> handleCommand("a");
            case KeyEvent.VK_S -> handleCommand("s");
            case KeyEvent.VK_D -> handleCommand("d");
            case KeyEvent.VK_P -> handleCommand("potion");
            case KeyEvent.VK_Q -> handleCommand("quit");
            case KeyEvent.VK_V -> handleCommand("save");
            case KeyEvent.VK_L -> handleCommand("load");
            case KeyEvent.VK_T -> handleCommand("status");
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
