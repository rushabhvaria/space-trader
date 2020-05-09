//Doesn't work anymore

import javax.swing.*;
import java.awt.*;
//import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Configuration {

    private static JPanel rootPanel;
    private static JPanel topPanel;
    private static JPanel middlePanel;
    private static String name = "Anonymous User";
    private static String difficulty = "Easy";
    private static String money = "1000";
    private static String[] abilities = {"0", "0", "0", "0"};
    private static String pilot = "0";
    private static String fighter = "0";
    private static String merchant = "0";
    private static String engineer = "0";
    private static int totalSkillsLeft = 16;
    private static JTextField nameBox;
    private static HashMap<String, Integer> currentSkills = new HashMap<>();
    private static JButton b;

    public Configuration() {
        currentSkills = zeroHashMap(currentSkills);

        rootPanel = new JPanel(new GridBagLayout());

        rootPanel.setMinimumSize(new Dimension(650, 700));
        topPanel = new JPanel();
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
        JPanel skillsPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel fighterPanel = new JPanel(new FlowLayout());
        JPanel pilotPanel = new JPanel(new FlowLayout());
        JPanel engineerPanel = new JPanel(new FlowLayout());
        JPanel merchantPanel = new JPanel(new FlowLayout());

        topPanel.setPreferredSize(new Dimension(1300, 100));
        middlePanel.setPreferredSize(new Dimension(600, 500));
        rootPanel.setPreferredSize(new Dimension(650, 700));

        JLabel l = new JLabel(
            "<html> <font family='Times New Roman' color='white'"
            + " size='30' >Create Your Space Trader</font></html>");

        JLabel nameLabel = new JLabel(
            "<html> <font size='6' color='white' >Enter Name: </font></html>");
        nameBox = new JTextField("");
        nameBox.setPreferredSize(new Dimension(150, 30));
        namePanel.add(nameLabel);
        namePanel.add(nameBox);
        namePanel.setBackground(new Color(25, 25, 25));

        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");
        JPanel difficulties = new JPanel();
        difficulties.add(easy);
        difficulties.add(medium);
        difficulties.add(hard);
        difficulties.setPreferredSize(new Dimension(900, 50));
        difficulties.setBackground(new Color(25, 25, 25));

        JLabel allocate = new JLabel(
            "<html> <font size='6' color='white' >"
            +   "Allocate your skills: </font></html>");

        skillsPanel.setBackground(Color.DARK_GRAY);

        JLabel skillsToAllocate = new JLabel(
            "<html> <font size='6' color='white'>16</font></html>");
        skillsPanel.add(allocate);
        skillsPanel.add(skillsToAllocate);

        JSlider fighterS = new JSlider(0, 16);
        fighterPanel = makeSlider(fighterPanel, fighterS, "Fighter", currentSkills, 20,
         skillsToAllocate, 1);


        JSlider pilotS = new JSlider(0, 16);
        pilotPanel = makeSlider(pilotPanel, pilotS, "Pilot", currentSkills, 61,
         skillsToAllocate, 0);

        JSlider engineerS = new JSlider(0, 16);
        engineerPanel = makeSlider(engineerPanel, engineerS, "Engineer", currentSkills, 1,
         skillsToAllocate, 2);

        JSlider merchantS = new JSlider(0, 16);
        merchantPanel = makeSlider(merchantPanel, merchantS, "Merchant", currentSkills, 1,
         skillsToAllocate, 3);

        b = new JButton("Confirm");

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "Easy";
                currentSkills = zeroHashMap(currentSkills);
                totalSkillsLeft = 16;
                zeroSliders(fighterS, pilotS, merchantS, engineerS);
                skillsToAllocate.setText("<html> <font size='6' color='white'>"
                    + String.valueOf(totalSkillsLeft)
                    + "</font></html>");
                money = "1000";
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "Medium";
                currentSkills = zeroHashMap(currentSkills);
                totalSkillsLeft = 12;
                zeroSliders(fighterS, pilotS, merchantS, engineerS);
                skillsToAllocate.setText("<html> <font size='6' color='white'>"
                    + String.valueOf(totalSkillsLeft)
                    + "</font></html>");
                money = "500";
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "Hard";
                currentSkills = zeroHashMap(currentSkills);
                totalSkillsLeft = 8;
                zeroSliders(fighterS, pilotS, merchantS, engineerS);
                skillsToAllocate.setText("<html> <font size='6' color='white'>"
                    + String.valueOf(totalSkillsLeft)
                    + "</font></html>");
                money = "100";
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> values = getUserInfo();
            }
        });


        topPanel.setBackground(new Color(25, 25, 25));
        topPanel.add(l, BorderLayout.PAGE_START);
        middlePanel = addPanelToPanel(middlePanel, namePanel, difficulties, skillsPanel,
         fighterPanel, engineerPanel, pilotPanel, merchantPanel);
        middlePanel.setBackground(new Color(25, 25, 25));
        middlePanel.add(b);
        rootPanel = addPanelToPanel(rootPanel, topPanel, middlePanel);
        rootPanel.setBackground(new Color(25, 25, 25));
    }

    private static JPanel makeSlider(JPanel skillPanel, JSlider skillS, String title,
        HashMap<String, Integer> currentSkills, int structNum, JLabel skillsToAllocate, int index) {
        skillPanel.setBackground(Color.DARK_GRAY);
        JLabel skillL = new JLabel(
            "<html> <font size='6' color='white' >" + title + ":    </font></html>");
        JLabel skillSL = new JLabel("0");
        skillSL.setForeground(Color.white);
        skillS.setValue(0);
        skillS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                String letter = title.substring(0, 1).toLowerCase();
                int pastSkill = currentSkills.get(letter);
                int difference = skillS.getValue() - pastSkill;
                int newSkillsLeft = totalSkillsLeft - difference;
                if (newSkillsLeft >= 0) {
                    skillsToAllocate.setText(
                        "<html> <font size='6' color='white'>"
                        + String.valueOf(newSkillsLeft)
                        + "</font></html>");
                    skillSL.setText(
                        "<html> <font color='white' >"
                        + String.valueOf(skillS.getValue())
                        + "</font></html>");
                    totalSkillsLeft = newSkillsLeft;
                    abilities[index] = String.valueOf(skillS.getValue());
                    currentSkills.put(letter, skillS.getValue());
                } else {
                    String newLabelText = "<html> <font color='red' >"
                        + abilities[index]
                        + "</font></html>";
                    skillSL.setText(newLabelText);
                }
            }
        });
        skillPanel = addComponents(skillPanel, skillL, skillS, skillSL, structNum);
        return skillPanel;
    }

    private static JPanel addPanelToPanel(JPanel addTo, JPanel... toAdd) {
        GridBagConstraints con = new GridBagConstraints();
        con.gridy = GridBagConstraints.RELATIVE;
        con.gridx = 0;
        for (JPanel panel : toAdd) {
            addTo.add(panel, con);
        }
        return addTo;
    }

    private static HashMap<String, Integer> zeroHashMap(HashMap<String, Integer> currentSkills) {
        currentSkills.put("f", 0);
        currentSkills.put("p", 0);
        currentSkills.put("e", 0);
        currentSkills.put("m", 0);
        return currentSkills;
    }

    private static void zeroSliders(JSlider... sliders) {
        for (JSlider slider: sliders) {
            slider.setValue(0);
        }

    }

    private static JPanel addComponents(JPanel addTo,
        JLabel l, JSlider s, JLabel sl, int structNum) {
        addTo.add(l);
        addTo.add(Box.createHorizontalStrut(structNum));
        addTo.add(s);
        addTo.add(sl);
        return addTo;
    }

    public static JPanel getRoot() {
        return rootPanel;
    }

    public static HashMap<String, String> getUserInfo() {
        name = nameBox.getText();
        if (name.equals("")) {
            name = "Anonymous User";
        }
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("name", name);
        userInfo.put("difficulty", difficulty);
        userInfo.put("money", money);
        userInfo.put("pilot", abilities[0]);
        userInfo.put("fighter", abilities[1]);
        userInfo.put("merchant", abilities[3]);
        userInfo.put("engineer", abilities[2]);
        return userInfo;
    }

    public static JButton b() {
        return b;
    }

    public static void refresh() {
        rootPanel = new JPanel();
        topPanel = new JPanel();
        middlePanel = new JPanel();
        name = "Anonymous User";
        difficulty = "Easy";
        money = "1000";
        abilities = new String[4];
        abilities[0] = "0";
        abilities[1] = "0";
        abilities[2] = "0";
        abilities[3] = "0";
        pilot = "0";
        fighter = "0";
        merchant = "0";
        engineer = "0";
        totalSkillsLeft = 16;
        nameBox = new JTextField();
        currentSkills = new HashMap<>();
        b = new JButton();
    }


}
