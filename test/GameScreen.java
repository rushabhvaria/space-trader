import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.HashMap;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
import java.util.Random;
import javax.swing.border.EmptyBorder;


public class GameScreen {
    private static JPanel root;
    private static JFrame frame;
    private static boolean displayMap = false;
    private static JPanel mainDisplayPanel;
    private static JPanel curRegDisplay;
    private static JPanel blank;
    private static JButton mapButton;
    private static JPanel mbPanel;
    private static TravelMap tMap;
    private static Game game;
    private static boolean travel;
    private static JPanel inventory;
    private static boolean firstTime = true;
    private static JLabel regLabel = new JLabel();
    private static JPanel sDispPanel;
    private static JButton refuelShip = new JButton("Click to refuel ship for 20 credits");
    private static JButton repairShip;
    private static JLabel credits;
    private static JPanel shipButtonPanel;

    public GameScreen() {
        System.out.println("Creating GameScreen");
    }

    public static void setRoot(JPanel root) {
        GameScreen.root = root;
    }

    public static void repaintMainDisplayPanel() {
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.RELATIVE;
        mainDisplayPanel.removeAll();
        sDispPanel = (new ShipDisplay(game.getShip())).getRootPanel();
        RegionDisplay newReg = new RegionDisplay(game.getUserInfo());
        //regLabel.setText("Current Region: " + game.getCurrRegion().getName());
        curRegDisplay = newReg.getRootPanel();
        mainDisplayPanel.add(curRegDisplay, con);
        mainDisplayPanel.add(sDispPanel, con);
        mainDisplayPanel.repaint();
        mainDisplayPanel.revalidate();
    }

    public static void setupGameScreen(Game g) {
        createShipButtons();
        game = g;
        root = new JPanel(new GridBagLayout());
        root.setPreferredSize(new Dimension(1000, 800));
        root.setBackground(new Color(25, 25, 25));
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(new Color(200, 200, 200));
        RegionDisplay curReg = new RegionDisplay(g.getUserInfo());
        ShipDisplay sDisp = new ShipDisplay(g.getShip());
        sDispPanel = sDisp.getRootPanel();
        curRegDisplay = curReg.getRootPanel();
        JLabel nameLabel = new JLabel("<html><font color='black' size='7' >"
            + g.getUserInfo().get("name")
            + "</font></html>");
        mapButton = new JButton("Travel Map");
        JPanel labelsPanel = new JPanel();
        JPanel skillsPanel = new JPanel();
        skillsPanel.setLayout(new BoxLayout(skillsPanel, BoxLayout.X_AXIS));
        skillsPanel.add(new JLabel("<html><font color='black' size='5' >Fighter:"
            + g.getUserInfo().get("fighter")
            + "</font></html>"));
        skillsPanel.add(Box.createHorizontalStrut(10));
        skillsPanel.add(new JLabel("<html><font color='black' size='5' >Engineer:"
            + g.getUserInfo().get("engineer")
            + "</font></html>"));
        skillsPanel.add(Box.createHorizontalStrut(10));
        skillsPanel.add(new JLabel("<html><font color='black' size='5' >Pilot:"
            + g.getUserInfo().get("pilot")
            + "</font></html>"));
        skillsPanel.add(Box.createHorizontalStrut(10));
        skillsPanel.add(new JLabel("<html><font color='black' size='5' >Merchant:"
            + g.getUserInfo().get("merchant")
            + "</font></html>"));

        skillsPanel.setBackground(new Color(200, 200, 200));
        labelsPanel.setBackground(new Color(200, 200, 200));
        Region[] regList = g.getRegionList().toArray(new Region[g.getRegionList().size()]);
        tMap = new TravelMap(g.getCurrRegion(), regList);
        blank = new JPanel();
        blank.setBackground(new Color(25, 205, 25));
        blank.setPreferredSize(new Dimension(1000, 300));
        gamePanel.setPreferredSize(new Dimension(1000, 200));
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
        JLabel blank2 = new JLabel("                        ");
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setBackground(new Color(200, 200, 200));
        namePanel.add(blank2);
        namePanel.add(nameLabel);
        labelsPanel.add(namePanel);
        labelsPanel.add(skillsPanel);

        mbPanel = new JPanel();
        mbPanel.setLayout(new BoxLayout(mbPanel, BoxLayout.X_AXIS));
        mbPanel.setBackground(new Color(200, 200, 200));
        mbPanel.add(mapButton);
        labelsPanel.add(mbPanel);
        labelsPanel.add(shipButtonPanel);

        gamePanel.add(labelsPanel);

        gamePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        JPanel inventory = makeInventory();
        labelsPanel.add(inventory);
        mainDisplayPanel = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.RELATIVE;
        mainDisplayPanel.add(curRegDisplay, con);
        mainDisplayPanel.add(sDispPanel, con);
        mainDisplayPanel.setBackground(new Color(25, 25, 25));
        GridBagConstraints mainCon = new GridBagConstraints();
        mainCon.gridy = GridBagConstraints.RELATIVE;
        mainCon.gridx = 0;
        root.add(mainDisplayPanel, mainCon);
        root.add(gamePanel, mainCon);
        tMap.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tMapAction(g);
            }
        });
        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapButton.getText().equals("Travel Map")) {
                    //root.removeAll();
                    mapButton.setText("Display Region");
                    //mainDisplayPanel = new JPanel();
                    mainDisplayPanel.removeAll();
                    sDispPanel = (new ShipDisplay(game.getShip())).getRootPanel();
                    tMap.getRoot().setPreferredSize(new Dimension(1050, 600));
                    JScrollPane tMapScroll = new JScrollPane(tMap.getRoot(),
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    mainDisplayPanel.add(tMap.getRoot(), con);
                    mainDisplayPanel.add(sDispPanel, con);
                    mainDisplayPanel.repaint();
                    mainDisplayPanel.revalidate();
                } else {
                    mapButton.setText("Travel Map");
                    mainDisplayPanel.removeAll();
                    sDispPanel = (new ShipDisplay(game.getShip())).getRootPanel();
                    RegionDisplay newReg = new RegionDisplay(g.getUserInfo());
                    regLabel.setText("Current Region: " + g.getCurrRegion().getName());
                    curRegDisplay = newReg.getRootPanel();
                    mainDisplayPanel.add(curRegDisplay, con);
                    mainDisplayPanel.add(sDispPanel, con);
                    mainDisplayPanel.repaint();
                    mainDisplayPanel.revalidate();
                }
                displayMap = !displayMap;
            }
        });
    }

    private static void tMapAction(Game g) {
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.RELATIVE;
        Region r = tMap.getSelectedRegion();
        Random rand = new Random();
        String difficulty = (Game.getDifficulty());
        int range = (difficulty.equals("Easy") ? 8 : 6);
        if (difficulty.equals("Hard")) {
            range = 4;
        }
        int randNumber = rand.nextInt(range);
        System.out.println(randNumber);
        boolean blocked = false;
        boolean chkTrvl = checkTravel(g.getCurrRegion(), r);
        travel = chkTrvl;
        //randNumber = 2;
        if (!chkTrvl) {
            JOptionPane.showMessageDialog(
                    null, "Insufficient Fuel Error",
                    "Insufficient fuel. Cannot travel to " + r.getName(),
                    JOptionPane.ERROR_MESSAGE);
        } else if (randNumber <= 1) {
            refuelShip.setEnabled(false);
            repairShip.setEnabled(false);
            NPCDisplay encounter = new NPCDisplay(new Bandit(r), Game.getPlayer());
            mainDisplayPanel.removeAll();
            mbPanel.remove(mapButton);
            mainDisplayPanel.add(encounter.getRoot(), con);
        } else if (randNumber == 2) {
            refuelShip.setEnabled(false);
            repairShip.setEnabled(false);
            NPCDisplay encounter = new NPCDisplay(new Trader(r), Game.getPlayer());
            mainDisplayPanel.removeAll();
            mbPanel.remove(mapButton);
            mainDisplayPanel.add(encounter.getRoot(), con);
        } else if (randNumber == 3 && Game.getPlayer().getInventory().size() != 0) {
            refuelShip.setEnabled(false);
            repairShip.setEnabled(false);
            NPCDisplay encounter = new NPCDisplay(
                new Police(Game.getPlayer(), r), Game.getPlayer());
            mainDisplayPanel.removeAll();
            mbPanel.remove(mapButton);
            mainDisplayPanel.add(encounter.getRoot(), con);
        } else {
            RegionDisplay curRg = new RegionDisplay(g.getUserInfo());
            JPanel curRgDisplay = curRg.getRootPanel();
            if (travel) {
                g.setCurrRegion(r);
                for (Item item: Game.getPlayer().getInventory()) {
                    if (item.getTechLevel() <= r.getTechLevel().ordinal()) {
                        item.setPrice(
                            item.getPrice()
                            * (1.10 + (0.1 * Game.getPlayer().getMerchant())));
                    }
                }
                GameScreen.refreshInventory();
                curRg = new RegionDisplay(g.getUserInfo());
                curRgDisplay = curRg.getRootPanel();
                mapButton.setText("Travel Map");
                sDispPanel = (new ShipDisplay(game.getShip())).getRootPanel();
                mainDisplayPanel.removeAll();
                mainDisplayPanel.add(curRgDisplay, con);
                mainDisplayPanel.add(sDispPanel, con);
            } else {
                JOptionPane.showMessageDialog(
                    null, "Insufficient Fuel Error",
                    "Insufficient fuel. Cannot travel to " + r.getName(),
                    JOptionPane.ERROR_MESSAGE);
            }
            repaintMainDisplayPanel();
        }
        mainDisplayPanel.repaint();
        mainDisplayPanel.revalidate();
    }


    public static void carryOn(Region r) {
        refuelShip.setEnabled(true);
        repairShip.setEnabled(true);
        mapButton.setText("Travel Map");
        mbPanel.add(mapButton);
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.RELATIVE;
        //boolean travel = checkTravel(Game.getCurrRegion(), r);
        RegionDisplay curRg = new RegionDisplay(Game.getUserInfo());
        JPanel curRgDisplay = curRg.getRootPanel();
        if (travel) {
            Game.setCurrRegion(r);
            for (Item item: Game.getPlayer().getInventory()) {
                if (item.getTechLevel() <= r.getTechLevel().ordinal()) {
                    item.setPrice(
                        item.getPrice() * (1.10 + (0.1 * Game.getPlayer().getMerchant())));
                }
            }
            GameScreen.refreshInventory();
            curRg = new RegionDisplay(Game.getUserInfo());
            curRgDisplay = curRg.getRootPanel();
            //mapButton.setText("Travel Map"); THIS IS SOMETHING WE NEED TO FIX!!!!!
            sDispPanel = (new ShipDisplay(Game.getShip())).getRootPanel();
            mainDisplayPanel.removeAll();
            mainDisplayPanel.add(curRgDisplay, con);
            mainDisplayPanel.add(sDispPanel, con);
        } else {
            JOptionPane.showMessageDialog(
                null, "Insufficient Fuel Error",
                "Insufficient fuel. Cannot travel to " + r.getName(),
                JOptionPane.ERROR_MESSAGE);
        }
        repaintMainDisplayPanel();
        mainDisplayPanel.repaint();
        mainDisplayPanel.revalidate();
        if (Game.lost()) {
            GameScreen.endGame(new LoseScreen());
        }
    }

    public static void goBack() {
        refuelShip.setEnabled(true);
        repairShip.setEnabled(true);
        mapButton.setText("Travel Map");
        mbPanel.add(mapButton);
        Region r = Game.getCurrRegion();
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.RELATIVE;
        //boolean travel = checkTravel(Game.getCurrRegion(), r);
        RegionDisplay curRg = new RegionDisplay(Game.getUserInfo());
        JPanel curRgDisplay = curRg.getRootPanel();
        if (travel) {
            Game.setCurrRegion(r);
            for (Item item: Game.getPlayer().getInventory()) {
                if (item.getTechLevel() <= r.getTechLevel().ordinal()) {
                    item.setPrice(
                        item.getPrice() * (1.10 + (0.1 * Game.getPlayer().getMerchant())));
                }
            }
            GameScreen.refreshInventory();
            curRg = new RegionDisplay(Game.getUserInfo());
            curRgDisplay = curRg.getRootPanel();
            //mapButton.setText("Travel Map"); THIS IS SOMETHING WE NEED TO FIX!!!!!
            sDispPanel = (new ShipDisplay(Game.getShip())).getRootPanel();
            mainDisplayPanel.removeAll();
            mainDisplayPanel.add(curRgDisplay, con);
            mainDisplayPanel.add(sDispPanel, con);
        } else {
            JOptionPane.showMessageDialog(
                null, "Insufficient Fuel Error",
                "Insufficient fuel. Cannot travel to " + r.getName(),
                JOptionPane.ERROR_MESSAGE);
        }
        repaintMainDisplayPanel();
        mainDisplayPanel.repaint();
        mainDisplayPanel.revalidate();
        if (Game.lost()) {
            GameScreen.endGame(new LoseScreen());
        }
    }

    public static JPanel getRoot() {
        return root;
    }

    public static void refreshInventory() {
        GameScreen.inventory.removeAll();
        GameScreen.inventory.repaint();
        GameScreen.inventory.revalidate();
        GameScreen.inventory.add(makeInventory());
        GameScreen.inventory.repaint();
        GameScreen.inventory.revalidate();
        /*
        GameScreen.root.removeAll();
        GameScreen.root.repaint();
        GameScreen.root.revalidate();
        return new JPanel();
        */
    }

    private static void createShipButtons() {
        shipButtonPanel = new JPanel();
        shipButtonPanel.setBackground(new Color(200, 200, 200));
        refuelShip = new JButton("Click to add 50 Ship Fuel for 50 credits");
        refuelShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refuel();
            }
        });
        String diff = game.getDifficulty();
        diff = diff.toUpperCase();
        int div = 16;
        if (diff.equals("MEDIUM")) {
            div = 12;
        } else if (diff.equals("HARD")) {
            div = 8;
        }
        int i = game.getPlayer().getEngineer();
        int creds = 100 - (70 * i / div);
        repairShip = new JButton("Click to repair ship for " + creds + " credits");
        repairShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repair(creds);
            }
        });
        shipButtonPanel.add(refuelShip);
        shipButtonPanel.add(repairShip);
    }

    private static void refuel() {
        /*
          Subtract credits
          Add fuel to ship
            If remaining fuel + new fuel > total cap then only fill tank.
        */
        int cost = 50;

        int plCredits = game.getPlayer().getCredits();

        if (plCredits >= cost) {

            double currFuel = game.getShip().getFuelCapacity();
            double maxFuel = game.getShip().getStartFuel();

            if (currFuel == maxFuel) {
                JOptionPane.showMessageDialog(
                    null, "Fuel Tank is full, cannot refuel.",
                    "Fuel Tank is Full",
                    JOptionPane.ERROR_MESSAGE);
                return;
            } else if (currFuel + 50.0 > maxFuel) {
                currFuel = maxFuel;
            } else {
                currFuel += 50.0;
            }

            plCredits -= cost;
            game.getPlayer().setCredits(plCredits);

            game.getShip().setFuelCapacity(currFuel);
            credits.setText("Current Credits: " + game.getPlayer().getCredits());
            sDispPanel = (new ShipDisplay(game.getShip())).getRootPanel();
            repaintMainDisplayPanel();
            JOptionPane.showMessageDialog(
                null, "Ship has been refueled",
                "Refueling Complete",
                JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                null, "Insufficient credits. Cannot refuel ship",
                "Insufficient Credits Error",
                JOptionPane.ERROR_MESSAGE);
        }
        root.repaint();
    }

    private static void repair(int cost) {
        /*
          Subtract credits
          Add fuel to ship
            If remaining fuel + new fuel > total cap then only fill tank.
        */
        int plCredits = game.getPlayer().getCredits();

        if (plCredits >= cost) {

            int currHealth = game.getShip().getHealth();
            int maxHealth = game.getShip().getMaxHealth();

            if (currHealth == maxHealth) {
                JOptionPane.showMessageDialog(
                    null, "Health is already maxed out.",
                    "Already max health",
                    JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                currHealth = maxHealth;
                game.getShip().setHealth(currHealth);
            }

            plCredits -= cost;
            game.getPlayer().setCredits(plCredits);
            credits.setText("Current Credits: " + game.getPlayer().getCredits());
            repaintMainDisplayPanel();
        } else {
            JOptionPane.showMessageDialog(
                null, "Insufficient credits. Cannot repair ship",
                "Insufficient Credits Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }


    private static JPanel makeInventory() {
        JPanel inventory = new JPanel();
        JPanel inventoryPanel = new JPanel(new GridLayout(5, 5));
        //inventoryPanel.setPreferredSize(new Dimension(450, 60));
        inventoryPanel.setBackground(new Color(60, 60, 60));
        inventory.setBackground(new Color(60, 60, 60));
        inventory.setBorder(BorderFactory.createRaisedBevelBorder());
        inventory.setPreferredSize(new Dimension(500, 300));

        inventory.add(new JLabel("<html><font color='white' size='5' >Inventory "
            + "</font></html>"));
        credits = new JLabel("Current Credits: "
            + game.getPlayer().getCredits());
        credits.setForeground(Color.WHITE);
        inventory.add(credits);
        for (Item item: Game.getPlayer().getInventory()) {
            JButton currItem = new JButton("<html><font color='black' size='1' >"
                + item.toString() + "</font></html>");
            currItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game.getPlayer().sellItem(item);
                    refreshInventory();
                }
            });
            inventoryPanel.add(currItem);
        }
        JScrollPane scrollFrame = new JScrollPane(inventoryPanel);
        scrollFrame.setPreferredSize(new Dimension(450, 60));
        //inventoryPanel.setAutoscrolls(true);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inventory.add(scrollFrame);
        //inventory.add(inventoryPanel);
        if (firstTime) {
            GameScreen.inventory = inventory;
            firstTime = false;
        }
        return inventory;
    }

    private static boolean checkTravel(Region curr, Region newRegion) {
        double dist = Region.getDist(curr, newRegion);
        double dist3 = dist / 3.0;
        double dist2 = dist - dist3;
        System.out.println("Distance: " + dist);
        String difficulty = game.getUserInfo().get("pilot").trim();
        int diff = 16;
        if (difficulty.equals("Medium")) {
            diff = 12;
        } else if (difficulty.equals("Hard")) {
            diff = 8;
        }
        /*
         Get the ship fuel level and subtract from cost to check if there is enough
         fuel left.
         */
        int pilotLevel = Integer.parseInt(game.getUserInfo().get("pilot").trim());
        dist2 = dist2 * (1 - (pilotLevel * 1.0 / diff));
        dist = dist3 + dist2;
        System.out.println("Pilot skill = " + pilotLevel + "Distance: " + dist);
        double fuelCap = game.getShip().getFuelCapacity();
        System.out.println("Fuel Capacity: " + fuelCap);
        if (fuelCap - dist >= 0) {
            game.getShip().setFuelCapacity(Math.round((fuelCap - dist) * 100) / 100);
            System.out.println("New fuel: " + game.getShip().getFuelCapacity());
            return true;
        }
        return false;
    }

    public static void endGame(EndScreen screen) {
        root.removeAll();
        //root = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.REMAINDER;
        root.add(new JLabel("<html> <font size='6'"
            + "color='red'>"
            + "You Lose</font></html>"), con);
        root.add(new JLabel(), con);
        root.add(new JLabel(), con);
        JButton resButton = new JButton("Restart?");
        resButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClass.refresh();
                MainClass.main(new String[3]);
            }
        });
        root.add(resButton, con);
        root.repaint();
        root.revalidate();
        //GameScreen.setRoot(screen.getRoot());
    }

    public static void winGame(EndScreen screen) {
        root.removeAll();
        //root = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.REMAINDER;
        root.add(new JLabel("<html> <font size='6'"
            + "color='yellow'>"
            + "You Won</font></html>"), con);
        root.add(new JLabel(), con);
        root.add(new JLabel(), con);
        JButton resButton = new JButton("Restart?");
        resButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        resButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainClass.refresh();
                MainClass.main(new String[3]);
            }
        });
        root.add(resButton, con);
        root.repaint();
        root.revalidate();
        //GameScreen.setRoot(screen.getRoot());
    }


    public static void restart() {
        frame = new JFrame();
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

    public static void refresh() {
        root = new JPanel();
        frame = new JFrame();
        displayMap = false;
        mainDisplayPanel = new JPanel();
        curRegDisplay = new JPanel();
        blank = new JPanel();
        mapButton = new JButton();
        mbPanel = new JPanel();
        game = new Game();
        travel = false;
        firstTime = true;
        regLabel = new JLabel();
        sDispPanel  = new JPanel();
        refuelShip = new JButton("Click to refuel ship for 20 credits");
        repairShip  = new JButton();
        credits = new JLabel();
        shipButtonPanel = new JPanel();
    }

    public static void main(String[] args) {
        /**
        JFrame jframe = new JFrame("JFrame Background Color");
        jframe.setBackground(Color.WHITE);
        jframe.setSize(1000, 800);



        HashMap userInfo = new HashMap();
        userInfo.put("name", "Anonymous User");
        userInfo.put("difficulty", "Easy");
        userInfo.put("money", "500");
        userInfo.put("pilot", "4");
        userInfo.put("fighter", "4");
        userInfo.put("merchant", "4");
        userInfo.put("engineer", "4");
        Game game = new Game(userInfo);
        game.startGame();

        JPanel root = (new GameScreen(game)).getRoot();
        JScrollPane scroll = new JScrollPane(root);

        jframe.setContentPane(scroll);
        jframe.pack();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        **/
    }
}
