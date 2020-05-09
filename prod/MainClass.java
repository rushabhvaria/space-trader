//package com.bananagame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
    private static JFrame frame;


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
        JScrollPane sPanel = null;
        frame = new JFrame("Space Traders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650, 700));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Configuration config = new Configuration();
        JPanel configPanel = config.getRoot();
        ConfigDisplay display = new ConfigDisplay();

        JPanel displayPanel = display.getRootPanel();
        ConfigDisplay disp = new ConfigDisplay();

        Game game = new Game();

        GameScreen gScreen = new GameScreen();

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
                JScrollPane sPane = new JScrollPane(configPanel);
                frame.setContentPane(sPane);
                frame.pack();
                frame.setVisible(true);
            }
        });
        confirm.setPreferredSize(new Dimension(400, 50));
        c.gridy = 5;
        panel.add(confirm,  c);

        sPanel = new JScrollPane(panel);
        frame.setContentPane(sPanel);
        frame.pack();
        frame.setVisible(true);

        final JButton startGame = new JButton("Click to Begin!");
        startGame.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                //display.setUserVals(config.getUserInfo());
                game.setupGame(config.getUserInfo());
                game.startGame();
                gScreen.setupGameScreen(game);

                JScrollPane sPane = new JScrollPane(gScreen.getRoot());
                frame.setContentPane(sPane);
                frame.pack();
            }
        });


        config.b().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display.setUserVals(config.getUserInfo());
                disp.setUserVals(config.getUserInfo());
                disp.setUp();
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.anchor = GridBagConstraints.CENTER;
                JPanel cnfgPanel = disp.getRootPanel();
                cnfgPanel.add(startGame, constraints);

                JScrollPane sPane = new JScrollPane(disp.getRootPanel());
                frame.setContentPane(sPane);
                frame.pack();
            }
        });




    }


    public static JFrame getFrame() {
        return frame;
    }

    public static void refresh() {
        frame = new JFrame();
        Configuration.refresh();
        GameScreen.refresh();
        Game.refresh();
    }
}
