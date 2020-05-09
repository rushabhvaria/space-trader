import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


public class WinScreen extends EndScreen {

    public WinScreen() {
        JPanel root = getRoot();
        root.add(new JLabel("<html> <font size='6'"
            + "color='yellow'>"
            + "You Win! </font></html>"));
    }


    public static void main(String[] args) {
        EndScreen toDisplay = new WinScreen();
        JFrame frame = new JFrame("Testing End Screen");
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(toDisplay.getRoot());
        frame.setBackground(new Color(20, 20, 20));
        frame.pack();
        frame.setVisible(true);
    }

}
