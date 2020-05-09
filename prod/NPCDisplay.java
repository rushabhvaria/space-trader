import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.io.IOException;
//import java.util.HashMap;
import java.util.*;

public class NPCDisplay {
    private NPC npc;
    private Player player;
    private boolean negotiated = false;
    private static JPanel root;
    private JButton bt1;
    private JButton bt2;
    private JButton bt3;
    private JButton bt4;
    private boolean continueToRegion = true;

    public NPCDisplay(NPC npc, Player player) {
        this.npc = npc;
        this.player = player;
        root = new JPanel(new GridBagLayout());

        createDisplay();

        if (npc instanceof Bandit) {
            banditAction();
        } else if (npc instanceof Trader) {
            traderAction();
        } else {
            policeAction();
        }

    }

    /* This constructor would require a bit more coding and isn't needed as of this current stage
    public NPCDisplay(int i, Player player) {
        this.player = player;

        if (i == 0) {
            npc = new Bandit();
        } else if (i == 1) {
            npc = new Trader();
        } else {
            //npc = new Police();
        }
    }
    */

    public boolean getContinue() {
        return continueToRegion;
    }

    private void createDisplay() {
        root.removeAll();
        GridBagConstraints mainCon = new GridBagConstraints();
        mainCon.gridy = GridBagConstraints.RELATIVE;


        JPanel content = new JPanel();
        bt1 = new JButton();
        bt2 = new JButton();
        bt3 = new JButton();
        bt4 = new JButton();
        setButtonText();

        content.add(bt1);
        content.add(bt2);
        content.add(bt3);
        if (npc instanceof Trader) {
            if (negotiated) {
                traderAction();
            } else {
                content.add(bt4);
            }
        }


        
        JPanel npcImgPanel = npc.display();
        root.add(npcImgPanel, mainCon);
        root.add(content, mainCon);
        root.repaint();
        root.revalidate();
    }

    private void banditAction() {
        bt1.setText("Pay Bandit");
        bt2.setText("Flee!");
        bt3.setText("Fight!!");

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = ((Bandit) npc).submit(player);
                if (!b) {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "Insufficient Funds!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                } else {
                    JOptionPane.showMessageDialog(
                        null, "You paid the badit and then ran away!" /*player.getNPCMessage()*/,
                        "You paid the bandit!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                }
            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean b = ((Bandit) npc).flee(player);
                if (!b) {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "You got caught!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                } else {
                    JOptionPane.showMessageDialog(
                        null, "You managed to escape! Phew!!" /*player.getNPCMessage()*/,
                        "You escpaed!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                }
            }
        });

        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean b = ((Bandit) npc).fight(player);
                if (!b) {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "You got beat up!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                } else {
                    JOptionPane.showMessageDialog(
                        null, "You beat up the bandit! Good job!" /*player.getNPCMessage()*/,
                        "You beat up the bandit!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                }
            }
        });
    }

    private void policeAction() {
        bt1.setText("Forfeit Goods");
        bt2.setText("Flee!");
        bt3.setText("Fight!!");

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = ((Police) npc).submit(player);
                if (!b) {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "Couldn't forfeit goods!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                } else {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "Forfeited Goods!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                }
            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean b = ((Police) npc).flee(player);
                if (!b) {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "You got caught!",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.goBack();
                } else {
                    JOptionPane.showMessageDialog(
                        null, player.getNPCMessage(),
                        "You fled",
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                }
            }
        });

        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean b = ((Police) npc).fight(player);
                if (!b) {
                    JOptionPane.showMessageDialog(
                        null, "You got beat up!",
                        player.getNPCMessage(),
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());
                } else {
                    JOptionPane.showMessageDialog(
                        null, "You fought off the police!",
                        player.getNPCMessage(),
                        JOptionPane.ERROR_MESSAGE);
                    GameScreen.carryOn(npc.getNextRegion());

                }
            }
        });
    }



    private void traderAction() {
        bt1.setText("Buy Items");
        bt2.setText("Ignore Trader");
        bt3.setText("Rob Trader!");

        bt1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean b = ((Trader) npc).buy(player);
                    if (!b) {
                        JOptionPane.showMessageDialog(
                                null, player.getNPCMessage(),
                                "Couldn't buy goods!",
                                JOptionPane.ERROR_MESSAGE);
                        GameScreen.carryOn(npc.getNextRegion());
                    } else {
                        JOptionPane.showMessageDialog(
                                null, player.getNPCMessage(),
                                "Bought Goods!",
                                JOptionPane.ERROR_MESSAGE);
                        GameScreen.carryOn(npc.getNextRegion());
                    }
                }
            });

        bt2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean b = ((Trader) npc).ignore(player);
                    if (!b) {
                        JOptionPane.showMessageDialog(
                                null, player.getNPCMessage(),
                                "You got caught ignoring!",
                                JOptionPane.ERROR_MESSAGE);
                        GameScreen.goBack();
                    } else {
                        JOptionPane.showMessageDialog(
                                null, player.getNPCMessage(),
                                "You Ignored Successfully!",
                                JOptionPane.ERROR_MESSAGE);
                        GameScreen.carryOn(npc.getNextRegion());
                    }
                }
            });

        bt3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean b = ((Trader) npc).rob(player);
                    if (!b) {
                        JOptionPane.showMessageDialog(
                                null, "You got beat up!",
                                player.getNPCMessage(),
                                JOptionPane.ERROR_MESSAGE);
                        GameScreen.carryOn(npc.getNextRegion());
                    } else {
                        JOptionPane.showMessageDialog(
                                null, "You didn't get beat up!",
                                player.getNPCMessage(),
                                JOptionPane.ERROR_MESSAGE);
                        GameScreen.carryOn(npc.getNextRegion());

                    }
                }
            });

        bt4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    negotiated = true;
                    boolean b = ((Trader) npc).negotiate(player);
                    if (!b) {
                        JOptionPane.showMessageDialog(
                                null, "Failure!",
                                player.getNPCMessage(),
                                JOptionPane.ERROR_MESSAGE);
                        createDisplay();
                    } else {
                        JOptionPane.showMessageDialog(
                                null, "Success!",
                                player.getNPCMessage(),
                                JOptionPane.ERROR_MESSAGE);
                        createDisplay();
                    }
                }
            });
    }



    private void setButtonText() {
        if (npc instanceof Bandit) {
            bt1.setText("Pay Bandit");
            bt2.setText("Flee!");
            bt3.setText("Fight!!");
        } else if (npc instanceof Trader) {
            bt1.setText("Buy Items");
            bt2.setText("Ignore Trader");
            bt3.setText("Rob Trader!");
            if (!(bt4 == null)) {
                bt4.setText("Negotiate!");
            }
        } else {
            bt1.setText("Forfeit Items");
            bt2.setText("Flee!");
            bt3.setText("Fight!!");
        }
    }

    public JPanel getRoot() {
        return root;
    }

    public static void main(String[] args) {
        /*
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("name", "Aditya");
        hmap.put("money", "1000");
        hmap.put("pilot", "5");
        hmap.put("fighter", "6");
        hmap.put("merchant", "5");
        hmap.put("engineer", "0");
        hmap.put("currentRegion", (new Region(100, 200, TechLevel.PREAG, "Name", 1).toString()));
        ArrayList<Item> inv  = new ArrayList<>();
        Item it = new Item(100.0, ItemType.BAD_FUEL);
        inv.add(it);
        Player p = new Player(hmap);
        p.setInventory(inv);

        NPC np = new Police(p);
        NPC bd = new Bandit();
        //hmap.put("name");
        NPCDisplay nd = new NPCDisplay(bd, p);
        JFrame frame = new JFrame();
        frame.setContentPane(nd.getRoot());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        */
    }
}
