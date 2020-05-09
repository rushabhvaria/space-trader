import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.Random;

public class Trader extends NPC {
    private Item item;
    private String name;
    private ArrayList<Item> goods = new ArrayList<Item>();


    public Trader(Region r) {
        super(r);
        item = Item.getRandomItem();
        Random quantity = new Random();
        int randQuantity = quantity.nextInt(5) + 1;
        for (int i = 0; i < randQuantity; i++) {
            goods.add(item);
        }
        calculatePrice(5, false);
        setMessage("Yo!, tryna buy "
                + goods.size() + " cheap "
                + item.getName() + " ? <br> for just "
                + item.getPrice() * goods.size() + " creds ?");
        System.out.println(item.getPrice());
        System.out.println(goods.size());
        this.name = getTraderType();
    }

    public void calculatePrice(int merchSkill, boolean negotiated) {
        if (!negotiated) {
            if ((item.getPrice() - merchSkill) <= 0.0) {
                item.setPrice(1.0);
            } else {
                item.setPrice((item.getPrice() - merchSkill));
            }
        } else {
            Random neg = new Random();
            int randInt = neg.nextInt(10) + 10;
            if ((item.getPrice() - randInt) <= 0.0) {
                item.setPrice(1.0);
            } else {
                item.setPrice((item.getPrice() - randInt));
                System.out.println(item.getPrice());
            }
        }
    }

    private String getTraderType() {
        Random rand = new Random();
        int randNumber = rand.nextInt(100);
        if (randNumber < 5) {
            calculatePrice(30, false);
            setMessage("BURP!<br>Hey Morty.... Oh... You're not.. Morty..<br>You want "
                    + goods.size() + " "
                    + item + " ?<br>I'll give it to ya for "
                    + item.getPrice() * goods.size() + " schmeckles..");
            return "Rick Sanchez";
        }
        return "Trader Joe";

    }

    public boolean rob(Player player) {
        player.badKarma();
        if (rollDice(player.getFighter(), player.getKarma())) {
            Random rand = new Random();
            if (Game.getShip().getCargoSpace() > 0) {
                boolean oneItem = false;
                for (Item item : goods) {
                    if (!oneItem && Game.getShip().getCargoSpace() > 0) {
                        player.getInventory().add(item);
                        Game.getShip().setCargoSpace(Game.getShip().getCargoSpace() - 1);
                        player.getShip().setCargoSpace(player.getShip().getCargoSpace() - 1);
                        oneItem = true;
                    } else if (rand.nextInt(1) == 1 && Game.getShip().getCargoSpace() > 0) {
                        player.getInventory().add(item);
                        Game.getShip().setCargoSpace(Game.getShip().getCargoSpace() - 1);
                        player.getShip().setCargoSpace(player.getShip().getCargoSpace() - 1);
                    }
                }
                System.out.println(Game.getShip().getCargoSpace());
                player.setNPCMessage("You robbed the Trader successfully!");
                GameScreen.refreshInventory();
                return true;
            } else {
                player.setNPCMessage("You failed to rob the Trader!");
                GameScreen.refreshInventory();
                return false;
            }
        } else {
            hurtPlayer(player);
            return false;
        }
    }

    public boolean buy(Player player) {
        if (player.getCredits() < item.getPrice() * goods.size()
            && Game.getShip().getCargoSpace() < goods.size()) {
            JOptionPane.showMessageDialog(
                    MainClass.getFrame(), "Not enough space or money to buy!!");
            player.setNPCMessage("You failed to buy from the Trader!");
            return false;
        } else if (player.getCredits() >= item.getPrice() * goods.size()
            && Game.getShip().getCargoSpace() > goods.size()) {
            for (Item item : goods) {
                player.purchaseItem(item);
            }
            player.setNPCMessage("You bought from the Trader successfully!");
            return true;
        }
        player.setNPCMessage("You failed to buy from the Trader!");
        return false;
    }

    private void hurtPlayer(Player player) {
        Game.getShip().setHealth(Game.getShip().getHealth() - 50);
        if (Game.getShip().getHealth() <= 0) {
            GameScreen.endGame(new LoseScreen());
        }
    }

    public boolean ignore(Player player) {
        player.setNPCMessage("You fly away....");
        return true;
    }

    public boolean negotiate(Player player) {
        if (rollDice(player.getMerchant(), player.getKarma())) {
            calculatePrice(player.getMerchant(), true);
            setMessage("Hmm... I guess you could have "
                    + goods.size() + " "
                    + item.getName() + " <br> for "
                    + item.getPrice() * goods.size() + " creds, Last offer!");
            player.setNPCMessage("Negotiated with the Trader successfully!");
            return true;
        } else {
            setMessage("No, you can't have "
                    + goods.size() + " "
                    + item.getName() + " for cheaper!<br>It's "
                    + item.getPrice() * goods.size() + " creds, Take it or Leave!");
            player.setNPCMessage("You failed to negotiate with the Trader!");
            return false;
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
        speechBubble.setPreferredSize(new Dimension(600, 80));
        panel.add(speechBubble, constraints);
        panel.add(new JLabel(new ImageIcon("npcImg/"
                + name
                + ".jpg")), constraints);
        return panel;
    }

}
