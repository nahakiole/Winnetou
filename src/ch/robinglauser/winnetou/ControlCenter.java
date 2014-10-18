package ch.robinglauser.winnetou;


import com.apple.eawt.Application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlCenter extends JFrame implements ActionListener {

    JLabel apacheStatus = new JLabel("Not running");
    JButton startApache = new JButton("Start Apache");

    JLabel mysqlStatus = new JLabel("Not running");
    JButton startMySQL = new JButton("Start MySQL");

    public ControlCenter() {
        setTitle("Winnetou");
        setIconImage(Toolkit.getDefaultToolkit().getImage("winnetou.png"));
        JPanel startPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        startPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        startPanel.add(apacheStatus);
        startPanel.add(startApache);
        startPanel.add(mysqlStatus);
        startPanel.add(startMySQL);
        startMySQL.addActionListener(this);
        startApache.addActionListener(this);
        add(startPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


    }

    public static void main(String[] args) {

        System.out.println("Started");
        Application application = Application.getApplication();
        Image image = Toolkit.getDefaultToolkit().getImage("resources/winnetou.png");
        application.setDockIconImage(image);

        ControlCenter controlCenter = new ControlCenter();


    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Clicked");
        Object src = evt.getSource();
        if (src == startApache) {

        } else if (src == startMySQL) {

        }
    }
}
