//package com.bananagame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {


    /**
     * The plan is to either create methods on each class so that they can
     * return the panels OR return the necessary values in order to run stuff.
     *
     * Ideally if we can get the panel from each of the classes we can set up
     * actionListeners on them and perform the necessary functions. For
     * example,  on the welcome screen startButton click,  we can force a close
     * of the frame and repopen a new frame (looks bad - not ideal),  or
     * simply switch panels so that the frame is now displaying the
     * configuration screen instead of the Welcome Screen. In the same way,
     * we can run the config screen and on the "confirm" button click the
     * panels can switch on the frame. Also,  we will have to allow the
     * ConfigDisplay class to get the user values from the configuration
     * class so that they can be populated in the panel and then displayed.
     *
     *@param args not being used currently.
     *
     */



    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Traders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650, 700));

        configuration config = new configuration();
        JPanel configPanel = config.getRoot();
        ConfigDisplay display = new ConfigDisplay();
        JPanel displayPanel = display.getRootPanel();

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(25, 25, 25));

        JLabel title = new JLabel();
        title.setText("<html><font size='42' color='#ffffff'"
            + "><b><i>Welcome to Space Traders!</i></b></font></html>");
        title.setBorder(new EmptyBorder(20, 0, 25, 0));
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(title);

        JButton confirm = new JButton("Click here to Start New Game!");
        confirm.setFont(new Font("Times New Roman", Font.BOLD, 20));
        confirm.setFocusPainted(false);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.remove(panel);
                frame.setContentPane(configPanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
        confirm.setPreferredSize(new Dimension(400, 50));
        c.gridy = 5;
        panel.add(confirm,  c);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

        config.b().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display.setUserVals(config.getUserInfo());
                ConfigDisplay disp = new ConfigDisplay(config.getUserInfo());
                frame.setContentPane(display.getRootPanel());
                frame.pack();
            }
        });

    }
}
