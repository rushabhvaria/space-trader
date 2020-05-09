import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Random;


public class Police extends NPC {

    private Item demand;


    public Police(Player player, Region r) {
        super(r);
        ArrayList<Item> inventory = player.getInventory();
        int index = new Random().nextInt(inventory.size());
        demand = inventory.get(index);
        setMessage("Stop! You appear to be carrying a stolen "
            + demand.getItemType().name()
            + ". Hand it over!");
    }

    public boolean flee(Player player) {
        player.badKarma();
        if (rollDice(player.getPilot(), player.getKarma())) {
            player.setNPCMessage("You have fled the police!");
            return true;
        } else {
            beatUpPlayer(player);
            return false;
        }
    }

    /*NOTE: Requirements aren't specified, so we could be more creative and send them to jail */
    public boolean fight(Player player) {
        player.badKarma();
        if (rollDice(player.getFighter(), player.getKarma())) {
            player.setNPCMessage("You have beaten up the police!");
            return true;
        } else {
            beatUpPlayer(player);
            return false;
        }
    }

    public boolean submit(Player player) {
        player.getInventory().remove(demand);
        player.setNPCMessage("You have forfeited " + demand.getName());
        player.goodKarma();
        return true;
    }

    private void beatUpPlayer(Player player) {
        player.setNPCMessage("Failed! You have been beaten up by police!");
        player.getInventory().remove(demand);
        player.setCredits(player.getCredits() >= 100 ? (player.getCredits() - 100) : 0);
        Game.getShip().setHealth(Game.getShip().getHealth() - 50);
        if (Game.getShip().getHealth() <= 0) {
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
        speechBubble.setPreferredSize(new Dimension(600, 50));
        panel.add(speechBubble, constraints);
        panel.add(new JLabel(new ImageIcon("npcImg/"
            + "police.jpg")), constraints);
        return panel;
    }
}
