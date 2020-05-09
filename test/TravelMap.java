import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class TravelMap extends JPanel {

    private static JPanel rootPanel;
    //private static JList<Region> regionList;
    private static Region currRegion;
    private static Region[] regionArr;
    private static Region selectedRegion;
    private static HashMap<String, String> userInfo;
    private static JPanel dispPanel;
    private static JButton confirmButton;
    private static JLabel dispLabel;
    private static JButton cancelButton;
    private static HashMap<String, Region> regionList;
    /*
        Add a hashmap in Universe class to be able to pull the specific region
        from it. Then in the travel button use that hashmap to get the value.
    */

    public TravelMap(Region currRegion, Region[] regionArr) {
        TravelMap.currRegion = currRegion;
        selectedRegion = currRegion;
        TravelMap.regionArr = regionArr;
        rootPanel = new JPanel(new BorderLayout(45, 10));
        regionList = new HashMap<>();
        for (int i = 0; i < regionArr.length; i++) {
            regionList.put(regionArr[i].getName(), regionArr[i]);
        }
        //rootPanel.setPreferredSize(new Dimension(400, 400));
        //rootPanel.setMaximumSize(new Dimension(100, 100));
        //rootPanel.setMinimumSize(new Dimension(1800, 1000));
        rootPanel.setBackground(new Color(25, 25, 25));
        //JList<Region> regionList = new JList<>();'

        dispPanel = new JPanel(new GridBagLayout());
        dispLabel = new JLabel("Select a region to see properties");
        dispLabel.setForeground(Color.WHITE);
        confirmButton = new JButton("Travel!");
        cancelButton = new JButton("Cancel");

        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.REMAINDER;
        dispPanel.add(dispLabel, con);
        dispPanel.add(confirmButton, con);
        //dispPanel.add(cancelButton, con);
        dispPanel.setBackground(new Color(25, 25, 25));

        dispPanel = (new RegionDisplay(null, currRegion)).getRootPanel();
        dispPanel.add(confirmButton, con);
        travel(regionArr);

        rootPanel.repaint();
        rootPanel.revalidate();

        /*
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Changing");
                JPanel temp = new JPanel();
                JLabel temp1 = new JLabel(selectedRegion.toString());
                temp.add(temp1);
                //dispLabel.setText(selectedRegion.toString());
                travel();
            }
        });
        */
    }

    public static JButton getConfirmButton() {
        return confirmButton;
    }

    public static JButton getCancelButton() {
        return cancelButton;
    }

    public static Region getSelectedRegion() {
        return selectedRegion;
    }

    public static Region travel() {
        travel(regionArr);
        return selectedRegion;
    }

    private static Region travel(Region[] regionArrayList) {
        regionArrayList = regionArr;
        JPanel jPanel = new JPanel(null);
        jPanel.setBackground(new Color(25, 25, 25));
        //jPanel.setSize(800, 500);
        //jPanel.setMinimumSize(new Dimension(800, 500));
        jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel testLabel = new JLabel("TEST");
        Insets insets = jPanel.getInsets();

        for (int i = 0; i < regionArrayList.length; i++) {
            JButton j = new JButton(regionArrayList[i].getName());
            JLabel j1 = new JLabel(regionArrayList[i].toString());
            j.add(j1);
            j1.setVisible(false);
            int x = regionArrayList[i].getX() + 200 + insets.left;
            int y = -1 * regionArrayList[i].getY() + 200;
            //JLabel j =
            //      new JLabel(regionArrayList[i].getX() + ", " +
            // regionArrayList[i].getY() );
            GridBagConstraints cons = new GridBagConstraints();
            cons.gridx = x;
            cons.gridy = y;
            j.setBounds((int) (x * 1.5), (int) (y * 1.25), 90, 65);
            //j.setMargin(new Insets(10, 10, 10, 10));
            Random rand = new Random();
            int imgNo = regionArrayList[i].getRegionNumber();
            j.setIcon(new ImageIcon("img/planet" + imgNo + ".jpg"));

            j.setVerticalTextPosition(SwingConstants.BOTTOM);
            j.setHorizontalTextPosition(SwingConstants.CENTER);
            j.setBackground(new Color(25, 25, 25));
            j.setForeground(Color.WHITE);
            j.setFocusPainted(false);

            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String rgN = j.getText();
                    // System.out.println(rgN);
                    // System.out.println(((JLabel) j.getComponent(0)).getText());
                    String rgnDetails = ((JLabel) j.getComponent(0)).getText();
                    String[] rgnDArr = rgnDetails.split(",");
                    String name = rgnDArr[0].substring(5).trim();
                    Region rgn = regionList.get(name);
                    selectedRegion = rgn;
                    System.out.println("Region:" + rgn);
                    travelHelper(rgn);
                    testLabel.setText(rgN);
                }
            });

            jPanel.add(j);

        }


        GridBagConstraints con = new GridBagConstraints();
        rootPanel.removeAll();
        //rootPanel = jPanel;
        rootPanel.add(jPanel, BorderLayout.CENTER);
        //con.gridy = GridBagConstraints.RELATIVE;
        //con.gridy = 0;
        rootPanel.add(dispPanel, BorderLayout.LINE_END);
        //rootPanel = jPanel;
        //rootPanel.setMinimumSize(new Dimension(900, 900));
        rootPanel.setBackground(new Color(25, 25, 25));
        //rootPanel.add(jPanel);
        return selectedRegion;
    }

    private static Region travelHelper(Region cRegion) {
        RegionDisplay rDisp = new RegionDisplay(null, cRegion);
        JPanel rdispPanel = rDisp.getRootPanel();

        //dispLabel = new JLabel("Select a region to see properties");


        dispPanel.removeAll();

        GridBagConstraints con = new GridBagConstraints();
        con.gridx = GridBagConstraints.REMAINDER;

        dispPanel.add(dispLabel, con);
        dispPanel.add(rdispPanel, con);
        dispPanel.add(confirmButton, con);
        //dispPanel.add(cancelButton, con);
        dispPanel.repaint();
        dispPanel.revalidate();

        return cRegion;
    }

    public static JPanel getRoot() {
        return rootPanel;
    }

    public static void main(String[] args) {
        //Adding to frame and displaying
        Universe uni = new Universe();
        //Game game = new Game();
        ArrayList<Region> regionsList = uni.getRegions();
        regionList = new HashMap<>();

        Region[] rgArr = new Region[10];
        int i = 0;
        for (Region r : regionsList) {
            rgArr[i++] = r;
            regionList.put(r.getName(), r);
        }
        new TravelMap(regionsList.get(0), rgArr);
        //rgArr =
        Region rg = travel(rgArr);


        JFrame frame = new JFrame("Travel Map");



        frame.setMinimumSize(new Dimension(1000, 600));
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setMaximumSize(new Dimension(1000, 600));
        frame.setSize(1000, 600);

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(rootPanel);
        //frame.setBackground(new Color(25, 25, 25));
        //frame.setMinimumSize(new Dimension(670, 850));
        frame.pack();
        frame.setVisible(true);
    }

}
