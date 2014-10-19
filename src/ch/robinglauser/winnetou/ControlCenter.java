package ch.robinglauser.winnetou;

import ch.robinglauser.winnetou.Services.ApacheService;
import ch.robinglauser.winnetou.Services.MySQLService;
import ch.robinglauser.winnetou.Services.Service;
import com.apple.eawt.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class ControlCenter extends JFrame implements ActionListener {

    ArrayList<Service> services = new ArrayList<Service>();

    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    ArrayList<JButton> buttons = new ArrayList<JButton>();

    public ControlCenter() {
        setTitle("Winnetou");
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources/winnetou.png"));
        JPanel startPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        startPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        services.add(new ApacheService());
        services.add(new MySQLService());

        Iterator<Service> it = services.iterator();



        JLabel jLabel;
        JButton jButton;
        while (it.hasNext()) {
            Service obj = it.next();
            System.out.println(obj.getName());
            jLabel = new JLabel(obj.getName());
            jButton = new JButton(obj.getName());
            labels.add(jLabel);
            buttons.add(jButton);
            JLabel lblLed = new JLabel("•");
            lblLed.setForeground(Color.GREEN);
            lblLed.setFont(new Font(null, Font.PLAIN, 30));
            startPanel.add(lblLed);
            jButton.addActionListener(this);
            startPanel.add(jLabel);
            startPanel.add(jButton);
        }
        add(startPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
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
        System.out.println(src.toString());

    }


}
