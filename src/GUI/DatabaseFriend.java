package GUI;

import javax.swing.*;

/**
 * Created by acer on 09-Jan-18.
 */
public class DatabaseFriend {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setSize(600,390);
        gui.setResizable(false);
        gui.setTitle("Database Extractor");
    }
}
