import javax.swing.*;
//import javax.swing.border.EmptyBorder;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public abstract class EndScreen {
    private JPanel root;

    public EndScreen() {
        root = new JPanel();
        root.setBackground(new Color(25, 25, 25));
    }

    public JPanel getRoot() {
        return root;
    }


}
