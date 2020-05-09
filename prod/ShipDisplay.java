import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.io.File;
import java.io.IOException;
import java.util.HashMap;
//import static java.awt.font.TextAttribute.FONT;
public class ShipDisplay {
    private static JPanel rootPanel;
    private static HashMap<String, String> shipInfo;
    private static Ship ship;

    public ShipDisplay(Ship ship) {
        rootPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        //Eventually create a method getCurrShip in Game Class
        //till then testing through main
        //ship = Game.getCurrShip();
        this.ship = ship;

        JLabel shipName = new JLabel("Name: " + ship.getName());
        JLabel shipCargoCap = new JLabel("Cargo Capacity: " + ship.getCargoSpace());
        JLabel shipHealth = new JLabel("Health: " + ship.getHealth());
        JLabel shipFuelCap = new JLabel("Fuel Capacity: " + ship.getFuelCapacity());
        JLabel title = new JLabel("<html><font size='32' color='#ffffff'"
                + "><b><i>Ship"
                + " Details</i></b></font></html>");
        title.setBorder(new EmptyBorder(10, 25, 40, 25));

        rootPanel.add(title, constraints);
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;

        rootPanel.add(new JLabel(new ImageIcon("shipImg/"
                + ship.getName()
                + ".jpg")), constraints);

        rootPanel.add(shipName, constraints);
        rootPanel.add(shipCargoCap, constraints);
        rootPanel.add(shipHealth, constraints);
        rootPanel.add(shipFuelCap, constraints);

        constraints.anchor = GridBagConstraints.CENTER;


        constraints.gridheight = GridBagConstraints.REMAINDER;
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.BOTH;
        setFonts(rootPanel);
        rootPanel.setBackground(new Color(25, 25, 25));

    }

    public static void display() throws IOException {
        JFrame frame = new JFrame("Ship Details");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(rootPanel);
        frame.setBackground(new Color(25, 25, 25));
        //frame.setMinimumSize(new Dimension(670, 850));
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
        Ship test = new Ship(ShipType.ROBOFLIGHT);
        ShipDisplay s = new ShipDisplay(test);
        s.display();
    }
}
