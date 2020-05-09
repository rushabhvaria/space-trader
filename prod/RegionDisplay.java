//package com.bananagame;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.io.File;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
//import static java.awt.font.TextAttribute.FONT;

public class RegionDisplay {
    private static JPanel rootPanel;
    private static HashMap<String, String> userInfo;
    private static Region region;

    public RegionDisplay() {
        this(new HashMap<>());
    }

    public static Region getRegion() {
        return region;
    }

    public RegionDisplay(HashMap<String, String> userInfo) {
        this.userInfo = userInfo;
        rootPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        region = Game.getCurrRegion();


        JLabel regionName = new JLabel("Name: " + region.getName());
        JLabel xCoord = new JLabel("X Coordinates: " + region.getX());
        JLabel yCoord = new JLabel("Y Coordinates: " + region.getY());
        JLabel techLevel = new JLabel("Tech Level: " + region.getTechLevel());
        JLabel title = new JLabel("<html><font size='32' color='#ffffff'"
                + "><b><i>Region"
                + " Details</i></b></font></html>");
        title.setBorder(new EmptyBorder(10, 25, 40, 25));

        rootPanel.add(title, constraints);
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        rootPanel.add(regionName, constraints);
        rootPanel.add(xCoord, constraints);
        rootPanel.add(yCoord, constraints);

        rootPanel.add(techLevel, constraints);
        addMarket(region, constraints);


        constraints.anchor = GridBagConstraints.CENTER;
        rootPanel.add(new JLabel(new ImageIcon("regImg/planet"
                + region.getRegionNumber()
                + ".jpg")), constraints);

        //constraints.anchor = GridBagConstraints.EAST;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        setFonts(rootPanel);
        rootPanel.setBackground(new Color(25, 25, 25));

    }

    public RegionDisplay(HashMap<String, String> userInfo, Region region) {
        this.userInfo = userInfo;
        rootPanel = new JPanel(new GridBagLayout());
        RegionDisplay.region = region;

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel regionName = new JLabel("Name: " + region.getName());
        JLabel xCoord = new JLabel("X Coordinates: " + region.getX());
        JLabel yCoord = new JLabel("Y Coordinates: " + region.getY());
        JLabel techLevel = new JLabel("Tech Level: " + region.getTechLevel());
        JLabel title = new JLabel("<html><font size='32' color='#ffffff'"
                + "><b><i>Region"
                + " Details</i></b></font></html>");
        title.setBorder(new EmptyBorder(10, 25, 40, 25));


        //Adding components to Panel
        //constraints.gridwidth = GridBagConstraints.CENTER;
        rootPanel.add(title, constraints);
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        rootPanel.add(regionName, constraints);
        rootPanel.add(xCoord, constraints);
        rootPanel.add(yCoord, constraints);
        rootPanel.add(techLevel, constraints);
        //addMarket(region, constraints);

        //constraints.anchor = GridBagConstraints.EAST;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        //rootPanel.add(new JLabel(new ImageIcon("regImg/planet"
        //+region.getRegionNumber()
        //+".jpg")), constraints);
        setFonts(rootPanel);
        rootPanel.setBackground(new Color(25, 25, 25));

    }

    public void addMarket(Region r, GridBagConstraints constraints) {
        JPanel marketPanel = new JPanel(new GridLayout(5, 2));
        ArrayList<Item> shelf = r.getMarket().getShelf();
        for (Item item: shelf) {
            JButton currItem = new JButton(item.toString());
            addPurchaseOption(currItem, item);
            marketPanel.add(currItem);
        }
        rootPanel.add(marketPanel, constraints);
    }

    private void addPurchaseOption(JButton button, Item item) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // maybe check for cargo space and whether player can afford here?
                //                    JDialog confirm = new JDialog("Are you sure?", true);
                System.out.println("purchased" + item.toString());
                //System.out.println("\n\n" + Game.getPlayer().purchaseItem(item)
                //    + "  _  " + Game.getPlayer().getCredits());
                //JOptionPane.showMessageDialog(frame,"Not enough space or money");
                if (!Game.getPlayer().purchaseItem(item)) {
                    JOptionPane.showMessageDialog(
                        MainClass.getFrame(), "Not enough space or money");
                } else {
                    //Check for endgame here
                    if (item.getItemType().ordinal() == 27) {
                        GameScreen.winGame(new WinScreen());
                    }
                }
                GameScreen.refreshInventory();
            }
        });

    }


    public static void display() throws IOException {
        //rootPanel.setBackground(Color.);
        //Adding to frame and displaying
        JFrame frame = new JFrame("Region Details");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(rootPanel);
        frame.setBackground(new Color(25, 25, 25));
        frame.setMinimumSize(new Dimension(670, 850));
        frame.pack();
        frame.setVisible(true);

    }

    private static void setFonts(Component comp) {
        comp.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        for (Component child : ((Container) comp).getComponents()) {
            if (child instanceof JLabel) {
                JLabel chil = (JLabel) child;
                String text = chil.getText();
                if (text != null && !text.contains("Region Details")) {
                    child.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    child.setForeground(Color.WHITE);
                    ((JLabel) child).setBorder(new EmptyBorder(5, 25, 10, 25));
                }

            }
        }
    }

    public static JPanel getRootPanel() {
        return rootPanel;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, String> userInfo = new HashMap<>();
        Region test = new Region(5, 62, TechLevel.PREAG,
                "Test Name", 10);
        RegionDisplay r = new RegionDisplay(userInfo, test);
        r.display();
    }
}
