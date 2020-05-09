import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Random;

public class Bandit extends NPC {
    private int demand;
    private String name;


    public Bandit(Region r) {
        super(r);
        this.demand = setDemand(0);
        setMessage("Pay up $" + demand + " or I'mma beat you.");
        this.name = getBanditType();
    }

    private int setDemand(int difficulty) {
        Random rand = new Random();
        //Between 500 and 1000 for easy, 500 and 1500 for medium, and 500 and 3000 for Hard
        int range = (difficulty == 0 ? 500 : 2500);
        if (difficulty == 1) {
            range = 1000;
        }
        int ransomAmount = rand.nextInt(range);
        ransomAmount += 500;
        return ransomAmount;
    }

    private String getBanditType() {
        Random rand = new Random();
        int randNumber = rand.nextInt(100);
        if (randNumber < 5) {
            setMessage("Rawr, I'mma bandit! Now give me $"
                + demand + "!");
            return "austinBandit";
        }
        return "bandit7";

    }

    public boolean fight(Player player) {
        player.goodKarma();
        if (rollDice(player.getFighter(), player.getKarma())) {
            player.setNPCMessage("You fought the Bandit successfully!");
            return true;
        } else {
            beatUpPlayer(player);
            player.setNPCMessage("The Bandit beat you up! You lost your credit"
                + " he damaged your ship!");
            return false;
        }
    }

    public boolean flee(Player player) {
        player.goodKarma();
        if (rollDice(player.getPilot(), player.getKarma())) {
            player.setNPCMessage("You fled the Bandit successfully!");
            return true;
        } else {
            beatUpPlayer(player);
            player.setNPCMessage("You were not able to flee the Bandit!");
            return false;
        }
    }

    public boolean submit(Player player) {
        player.goodKarma();
        if (player.getCredits() < demand && player.getInventory().size() != 0) {
            player.setInventory(new ArrayList<Item>());
            player.setCredits(0);
            player.setNPCMessage("Insufficient Credits to pay Bandit"
                + ", you lost all your credits and all your inventory!");
            return false;
        } else if (player.getCredits() < demand && player.getInventory().size() == 0) {
            Game.getShip().setHealth(Game.getShip().getHealth() - 10);
            player.setNPCMessage("Insufficient Credits to pay Bandit"
                + ", he damaged your ship!");
            if (Game.getShip().getHealth() <= 0) {
                GameScreen.endGame(new LoseScreen());
            }
            return false;
        } else {
            player.setCredits(player.getCredits() - demand);
            player.setNPCMessage("The Bandit has taken"
                + demand
                + " credits from you");
        }
        return true;
    }

    private void beatUpPlayer(Player player) {
        player.setCredits(0);
        Game.getShip().setHealth(Game.getShip().getHealth() - 200);
        if (Game.getShip().getHealth() <= 0) {
            Game.lose();
            Game.win();
            GameScreen.endGame(new LoseScreen());

        }
    }

    @Override
    public JPanel display() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;
        JLabel text = new JLabel("<html> <font size='4' color='black'>"
            + getMessage()
            + "</font></html>");
        JPanel speechBubble = new JPanel();
        speechBubble.add(text);
        speechBubble.setBackground(Color.WHITE);
        speechBubble.setPreferredSize(new Dimension(320, 50));
        panel.add(speechBubble, constraints);
        panel.add(new JLabel(new ImageIcon("npcImg/"
            + name
            + ".jpg")), constraints);
        return panel;
    }


    public static void main(String[] args) {
        /*
        Bandit toDisplay = new Bandit();
        JFrame frame = new JFrame("Testing Bandit");
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(toDisplay.display());
        frame.setBackground(new Color(20, 20, 20));
        frame.pack();
        frame.setVisible(true);
        */

    }

}
