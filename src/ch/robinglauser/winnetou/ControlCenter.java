package ch.robinglauser.winnetou;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class ControlCenter extends JFrame {


    public static void main(String[] args) {
        ControlCenter controlCenter = new ControlCenter();
        controlCenter.setTitle("Winnetou");
        JPanel startPanel = new JPanel(new GridLayout(2,2,5,5));

        startPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        JLabel apacheStatus = new JLabel("Not running");
        JButton startApache = new JButton("Start Apache");

        JLabel mysqlStatus = new JLabel("Not running");
        JButton startMySQL = new JButton("Start MySQL");
        startPanel.add(apacheStatus);
        startPanel.add(startApache);
        startPanel.add(mysqlStatus);
        startPanel.add(startMySQL);
        controlCenter.add(startPanel);


        controlCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlCenter.pack();
        controlCenter.setVisible(true);
    }
}
