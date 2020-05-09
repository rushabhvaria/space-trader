import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class configuration {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private String name = "";
    private String difficulty = "";
    private String money = "";
    private String pilot = "";
    private String fighter = "";
    private String merchant = "";
    private String engineer = "";
    private int totalSkillsLeft = 16;
    private JTextField nameBox;
    private HashMap<String, Integer> currentSkills = new HashMap<>();
    public JButton b;

    public configuration() {
        currentSkills.put("f", 0);
        currentSkills.put("p", 0);
        currentSkills.put("e", 0);
        currentSkills.put("m", 0);

        rootPanel = new JPanel();
        topPanel = new JPanel();
        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
        JPanel skillsPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JPanel fighterPanel = new JPanel(new FlowLayout());
        JPanel pilotPanel = new JPanel(new FlowLayout());
        JPanel engineerPanel = new JPanel(new FlowLayout());
        JPanel merchantPanel = new JPanel(new FlowLayout());

        topPanel.setPreferredSize(new Dimension( 1300, 100));
        middlePanel.setPreferredSize(new Dimension( 800, 500));
        rootPanel.setPreferredSize(new Dimension( 1300, 1800));

        JLabel l = new JLabel("<html> <font color='white' size='20' >Enter Your Information</font></html>");

        JLabel nameLabel = new JLabel("<html> <font color='white' >Enter Name: </font></html>");
        nameBox = new JTextField("");
        nameBox.setPreferredSize(new Dimension(150, 30));
        namePanel.add(nameLabel);
        namePanel.add(nameBox);
        namePanel.setBackground(Color.BLACK);

        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");
        JPanel difficulties = new JPanel();
        difficulties.add(easy);
        difficulties.add(medium);
        difficulties.add(hard);
        difficulties.setPreferredSize(new Dimension(900, 50));
        difficulties.setBackground(Color.BLACK);

        JLabel allocate = new JLabel("<html> <font color='white' >Allocate your skills: </font></html>");

        skillsPanel.setBackground(Color.DARK_GRAY);

        JLabel skillsToAllocate = new JLabel("16");
        skillsPanel.add(allocate);
        skillsPanel.add(skillsToAllocate);


        fighterPanel.setBackground(Color.DARK_GRAY);
        JLabel fighterL = new JLabel("<html> <font color='white' >Fighter:    </font></html>");
        JSlider fighterS = new JSlider(0, 16);
        JLabel fighterSL = new JLabel("0");
        fighterS.setValue(0);
        fighterS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int pastSkill = currentSkills.get("f");
                int difference = fighterS.getValue() - pastSkill;
                int newSkillsLeft = totalSkillsLeft - difference;
                if (newSkillsLeft >= 0) {
                    skillsToAllocate.setText(String.valueOf(newSkillsLeft));
                    fighterSL.setText(String.valueOf(fighterS.getValue()));
                    totalSkillsLeft = newSkillsLeft;
                    fighter = String.valueOf(fighterS.getValue());
                    currentSkills.put("f", fighterS.getValue());
                } else {
                    String newLabelText = fighterSL.getText();
                    newLabelText = "<html> <font color='red' >" + newLabelText + "</font></html>";
                    fighterSL.setText(newLabelText);
                }
            }
        });
        fighterPanel.add(fighterL);
        fighterPanel.add(Box.createHorizontalStrut(8));
        fighterPanel.add(fighterS);
        fighterPanel.add(fighterSL);

        pilotPanel.setBackground(Color.DARK_GRAY);
        JLabel pilotL = new JLabel("<html> <font color='white' >Pilot:</font></html>");
        JSlider pilotS = new JSlider(0, 16);
        JLabel pilotSL = new JLabel("0");
        pilotS.setValue(0);
        pilotS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int pastSkill = currentSkills.get("p");
                int difference = pilotS.getValue() - pastSkill;
                int newSkillsLeft = totalSkillsLeft - difference;
                if (newSkillsLeft >= 0) {
                    skillsToAllocate.setText(String.valueOf(newSkillsLeft));
                    totalSkillsLeft = newSkillsLeft;
                    currentSkills.put("p", pilotS.getValue());
                    pilot = String.valueOf(pilotS.getValue());
                    pilotSL.setText(String.valueOf(pilotS.getValue()));
                } else {
                    String newLabelText = pilotSL.getText();
                    newLabelText = "<html> <font color='red' >" + newLabelText + "</font></html>";
                    pilotSL.setText(newLabelText);
                }
            }
        });
        pilotPanel.add(pilotL);
        pilotPanel.add(Box.createHorizontalStrut(32));
        pilotPanel.add(pilotS);
        pilotPanel.add(pilotSL);

        engineerPanel.setBackground(Color.DARK_GRAY);
        JLabel engineerL = new JLabel("<html> <font color='white' >Engineer:   </font></html>");
        JSlider engineerS = new JSlider(0, 16);
        JLabel engineerSL = new JLabel("0");
        engineerS.setValue(0);
        engineerS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int pastSkill = currentSkills.get("e");
                int difference = engineerS.getValue() - pastSkill;
                int newSkillsLeft = totalSkillsLeft - difference;
                if (newSkillsLeft >= 0) {
                    skillsToAllocate.setText(String.valueOf(newSkillsLeft));
                    totalSkillsLeft = newSkillsLeft;
                    currentSkills.put("e", engineerS.getValue());
                    engineer = String.valueOf(engineerS.getValue());
                    engineerSL.setText(String.valueOf(engineerS.getValue()));
                } else {
                    String newLabelText = engineerSL.getText();
                    newLabelText = "<html> <font color='red' >" + newLabelText + "</font></html>";
                    engineerSL.setText(newLabelText);
                }
            }
        });
        engineerPanel.add(engineerL);
        engineerPanel.add(Box.createHorizontalStrut(1));
        engineerPanel.add(engineerS);
        engineerPanel.add(engineerSL);

        merchantPanel.setBackground(Color.DARK_GRAY);
        JLabel merchantL = new JLabel("<html> <font color='white' >Merchant:   </font></html>");
        JSlider merchantS = new JSlider(0, 16);
        JLabel merchantSL = new JLabel("0");
        merchantS.setValue(0);
        merchantS.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int pastSkill = currentSkills.get("m");
                int difference = merchantS.getValue() - pastSkill;
                int newSkillsLeft = totalSkillsLeft - difference;
                if (newSkillsLeft >= 0) {
                    skillsToAllocate.setText(String.valueOf(newSkillsLeft));
                    totalSkillsLeft = newSkillsLeft;
                    currentSkills.put("m", merchantS.getValue());
                    merchant = String.valueOf(merchantS.getValue());
                    merchantSL.setText(String.valueOf(merchantS.getValue()));
                } else {
                    String newLabelText = merchantSL.getText();
                    newLabelText = "<html> <font color='red' >" + newLabelText + "</font></html>";
                    merchantSL.setText(newLabelText);
                }
            }
        });
        merchantPanel.add(merchantL);
        merchantPanel.add(Box.createHorizontalStrut(2));
        merchantPanel.add(merchantS);
        merchantPanel.add(merchantSL);

        b = new JButton("Confirm");
        b.setBounds(5,5,2, 2);

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "easy";
                currentSkills.put("f", 0);
                currentSkills.put("p", 0);
                currentSkills.put("e", 0);
                currentSkills.put("m", 0);
                totalSkillsLeft = 16;
                fighterS.setValue(0);
                pilotS.setValue(0);
                merchantS.setValue(0);
                engineerS.setValue(0);
                skillsToAllocate.setText(String.valueOf(totalSkillsLeft));
                money = "1000";
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "medium";
                currentSkills.put("f", 0);
                currentSkills.put("p", 0);
                currentSkills.put("e", 0);
                currentSkills.put("m", 0);
                totalSkillsLeft = 12;
                fighterS.setValue(0);
                pilotS.setValue(0);
                merchantS.setValue(0);
                engineerS.setValue(0);
                skillsToAllocate.setText(String.valueOf(totalSkillsLeft));
                money = "500";
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "hard";
                currentSkills.put("f", 0);
                currentSkills.put("p", 0);
                currentSkills.put("e", 0);
                currentSkills.put("m", 0);
                totalSkillsLeft = 8;
                fighterS.setValue(0);
                pilotS.setValue(0);
                merchantS.setValue(0);
                engineerS.setValue(0);
                skillsToAllocate.setText(String.valueOf(totalSkillsLeft));
                money = "100";
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> values = getUserInfo();
            }
        });


        topPanel.setBackground(Color.BLACK);
        topPanel.add(l, BorderLayout.PAGE_START);
        middlePanel.add(namePanel);
        middlePanel.add(difficulties);
        middlePanel.add(skillsPanel);
        middlePanel.add(fighterPanel);
        middlePanel.add(engineerPanel);
        middlePanel.add(pilotPanel);
        middlePanel.add(merchantPanel);
        middlePanel.setBackground(Color.BLACK);
        middlePanel.add(b);
        rootPanel.add(topPanel);
        rootPanel.add(middlePanel);
        rootPanel.setBackground(Color.BLACK);
    }

    public JPanel getRoot() {
        return rootPanel;
    }

    public HashMap<String, String> getUserInfo() {
        name = nameBox.getText();
        HashMap<String,String> userInfo = new HashMap<>();
        userInfo.put("name", name);
        userInfo.put("difficulty", difficulty);
        userInfo.put("money", money);
        userInfo.put("pilot", pilot);
        userInfo.put("fighter", fighter);
        userInfo.put("merchant", merchant);
        userInfo.put("engineer", engineer);
        return userInfo;
    }




}
