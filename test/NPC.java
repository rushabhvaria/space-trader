import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;

import java.util.Random;

public abstract class NPC {

    private String message;
    private Region nextRegion;


    public NPC(Region r) {
        this.nextRegion = r;
    }

    public abstract JPanel display();

    public boolean rollDice(int skillLevel, int karma) {
        Random rand = new Random();
        int randNumber = rand.nextInt(1000);
        randNumber += (50 * skillLevel);
        randNumber += (10 * karma);
        return (randNumber >= 700);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Region getNextRegion() {
        return nextRegion;
    }



}
