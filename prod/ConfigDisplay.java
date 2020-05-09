//package com.bananagame;

//import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.io.File;
import java.io.IOException;
import java.util.HashMap;
//import static java.awt.font.TextAttribute.FONT;

public class ConfigDisplay {
    private static JPanel rootPanel;
    private static HashMap<String, String> userVals;
    private static JButton startGame;

    public ConfigDisplay() {
        this(new HashMap<>());
    }

    public ConfigDisplay(HashMap<String, String> vals) {
        userVals = vals;
        rootPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel name =
                new JLabel("Name: " + userVals.getOrDefault(
                        "name", "No"
                        + " name found"));
        JLabel difficulty = new JLabel("Game Difficulty: " + userVals.getOrDefault(
                "difficulty", "No difficulty found"));
        JLabel money = new JLabel("Credits: " + userVals.getOrDefault("money",
                "No credits found") + " credits");
        JLabel pilot = new JLabel("Pilot points: " + userVals.getOrDefault(
                "pilot", "No pilot points found"));
        JLabel fighter = new JLabel("Fighter points: " + userVals.getOrDefault(
                "fighter", "No fighter points found"));
        JLabel merchant = new JLabel("Merchant points: " + userVals.getOrDefault(
                "merchant", "No merchant points found"));
        JLabel engineer =
                new JLabel("Engineer points: " + userVals.getOrDefault(
                        "engineer", "No engineer points found"));
        JLabel title = new JLabel("<html><font size='32' color='#ffffff'"
            + "><b><i>Configuration"
            + " Details</i></b></font></html>");
        title.setBorder(new EmptyBorder(10, 25, 40, 25));
        startGame = new JButton("Click to Begin!");
        startGame.setFont(new Font("Times New Roman", Font.PLAIN, 20));


        //Adding components to Panel
        //constraints.gridwidth = GridBagConstraints.CENTER;
        rootPanel.add(title, constraints);
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        rootPanel.add(name, constraints);
        rootPanel.add(difficulty, constraints);
        rootPanel.add(money, constraints);
        rootPanel.add(pilot, constraints);
        rootPanel.add(fighter, constraints);
        rootPanel.add(merchant, constraints);
        rootPanel.add(engineer, constraints);
        rootPanel.add(new JLabel(""), constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        //rootPanel.add(startGame, constraints);

        //constraints.anchor = GridBagConstraints.EAST;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        rootPanel.add(new JLabel(new ImageIcon("configBackground.jpg")), constraints);
        setFonts(rootPanel);
        rootPanel.setBackground(new Color(25, 25, 25));

    }

    public static void setUp() {
        rootPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel name =
                new JLabel("Name: " + userVals.getOrDefault(
                        "name", "No"
                        + " name found"));
        JLabel difficulty = new JLabel("Game Difficulty: " + userVals.getOrDefault(
                "difficulty", "No difficulty found"));
        JLabel money = new JLabel("Credits: " + userVals.getOrDefault("money",
                "No credits found") + " credits");
        JLabel pilot = new JLabel("Pilot points: " + userVals.getOrDefault(
                "pilot", "No pilot points found"));
        JLabel fighter = new JLabel("Fighter points: " + userVals.getOrDefault(
                "fighter", "No fighter points found"));
        JLabel merchant = new JLabel("Merchant points: " + userVals.getOrDefault(
                "merchant", "No merchant points found"));
        JLabel engineer =
                new JLabel("Engineer points: " + userVals.getOrDefault(
                        "engineer", "No engineer points found"));
        JLabel title = new JLabel("<html><font size='32' color='#ffffff'"
            + "><b><i>Configuration"
            + " Details</i></b></font></html>");
        title.setBorder(new EmptyBorder(10, 25, 40, 25));
        startGame = new JButton("Click to Begin!");
        startGame.setFont(new Font("Times New Roman", Font.PLAIN, 20));


        //Adding components to Panel
        //constraints.gridwidth = GridBagConstraints.CENTER;
        rootPanel.add(title, constraints);
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        rootPanel.add(name, constraints);
        rootPanel.add(difficulty, constraints);
        rootPanel.add(money, constraints);
        rootPanel.add(pilot, constraints);
        rootPanel.add(fighter, constraints);
        rootPanel.add(merchant, constraints);
        rootPanel.add(engineer, constraints);
        rootPanel.add(new JLabel(""), constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        //rootPanel.add(startGame, constraints);

        //constraints.anchor = GridBagConstraints.EAST;
        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        rootPanel.add(new JLabel(new ImageIcon("configBackground.jpg")), constraints);
        setFonts(rootPanel);
        rootPanel.setBackground(new Color(25, 25, 25));
    }

    public static void setUserVals(HashMap<String, String> values) {
        userVals = values;
    }


    public static void display() throws IOException {
        //rootPanel.setBackground(Color.);
        //Adding to frame and displaying
        JFrame frame = new JFrame("Configuration Details");
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
                if (text != null && !text.contains("Configuration Details")) {
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

    public static JButton getStartButton() {
        return startGame;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, String> vals = new HashMap<>();
        vals.put("name", "Aditya");
        ConfigDisplay c = new ConfigDisplay(vals);
        c.setUserVals(vals);
        c.display();
    }
}
