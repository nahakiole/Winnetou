package ch.robinglauser.winnetou;

import ch.robinglauser.winnetou.Services.ApacheService;
import ch.robinglauser.winnetou.Services.MySQLService;
import ch.robinglauser.winnetou.Services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


public class ControlCenter extends JFrame implements ActionListener {

    ArrayList<Service> services = new ArrayList<Service>();

    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    ArrayList<JButton> buttons = new ArrayList<JButton>();

    public ControlCenter() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Fail");
        }
        setFont(new Font("Arial",10,10));
        setTitle("Winnetou");
        JPanel startPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        startPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        services.add(new ApacheService());
        services.add(new MySQLService());

        Iterator<Service> it = services.iterator();



        JLabel jLabel;
        JButton jButton;
        while (it.hasNext()) {
            Service service = it.next();
            System.out.println(service.getName());
            jLabel = new JLabel(service.getName());
            jButton = new JButton(service.getName());
            labels.add(jLabel);
            buttons.add(jButton);
            JLabel lblLed = new JLabel("•");
            lblLed.setForeground(Color.GREEN);
            lblLed.setFont(new Font(null, Font.PLAIN, 30));
            startPanel.add(lblLed);
            jButton.addActionListener(e -> {
                if (service.isRunning()){
                    service.stop();
                    lblLed.setForeground(Color.RED);
                }
                else {
                    service.start();
                    lblLed.setForeground(Color.GREEN);
                }
            });
            startPanel.add(jLabel);
            startPanel.add(jButton);
        }


        HostFile hostFile = new HostFile(new File("C:\\Users\\Robin\\IdeaProjects\\Winnetou\\resources\\test"));

        ArrayList entityList = hostFile.getData();
        JTable table = new JTable();
        TableModel model = new TableModel(entityList);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setModel(model);
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        Panel buttonPanel = new Panel();

        JButton button = new JButton("Add Virtual Host");
        button.addActionListener(e -> {
            JTextField hostName = new JTextField();
            hostName.addAncestorListener( new AncestorListener()
            {
                @Override
                public void ancestorRemoved( final AncestorEvent event ) {}
                @Override
                public void ancestorMoved( final AncestorEvent event ) {}
                @Override
                public void ancestorAdded( final AncestorEvent event )
                {
                    hostName.requestFocusInWindow();
                }
            });

            hostName.addFocusListener(new FocusListener()
            {
                @Override
                public void focusGained( final FocusEvent e ) {}
                @Override
                public void focusLost( final FocusEvent e )
                {
                    if( isFirstTime )
                    {
                        // When we lose focus, ask for it back but only once
                        hostName.requestFocusInWindow();
                        isFirstTime = false;
                    }
                }
                private boolean isFirstTime = true;
            } );
            JTextField ipadress = new JTextField("127.0.0.1");
            final JComponent[] inputs = new JComponent[] {
                    new JLabel("HostName"),
                    hostName,
                    new JLabel("IP-Adress"),
                    ipadress,
            };

            JOptionPane.showMessageDialog(null, inputs, "Add new host", JOptionPane.PLAIN_MESSAGE, null);

            hostFile.addHostEntry(hostName.getText(), ipadress.getText());
            model.fireTableDataChanged();
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    HostEntry hostEntry = (HostEntry) model.wordsList.get(row);
                    JTextField hostName = new JTextField(hostEntry.getHostname());
                    hostName.addAncestorListener( new AncestorListener()
                    {
                        @Override
                        public void ancestorRemoved( final AncestorEvent event ) {}
                        @Override
                        public void ancestorMoved( final AncestorEvent event ) {}
                        @Override
                        public void ancestorAdded( final AncestorEvent event )
                        {
                            hostName.requestFocusInWindow();
                        }
                    });

                    hostName.addFocusListener(new FocusListener()
                    {
                        @Override
                        public void focusGained( final FocusEvent e ) {}
                        @Override
                        public void focusLost( final FocusEvent e )
                        {
                            if( isFirstTime )
                            {
                                hostName.requestFocusInWindow();
                                isFirstTime = false;
                            }
                        }
                        private boolean isFirstTime = true;
                    } );
                    JTextField ipadress = new JTextField(hostEntry.getIpAddress());
                    final JComponent[] inputs = new JComponent[] {
                            new JLabel("HostName"),
                            hostName,
                            new JLabel("IP-Adress"),
                            ipadress,
                    };

                    JOptionPane.showMessageDialog(null, inputs, "Add new host", JOptionPane.PLAIN_MESSAGE, null);
                    hostEntry.setHostname(hostName.getText());
                    hostEntry.setIpAddress(ipadress.getText());
                    model.fireTableDataChanged();

                }
            }
        });


        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMaxWidth(90);


        buttonPanel.add(button);

        mainPanel.add(startPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(buttonPanel);


        add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(300, 300);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        setVisible(true);

    }

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");
        System.out.println("Started");
        new ControlCenter();

    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Clicked");
        Object src = evt.getSource();
        System.out.println(src.toString());

    }


}
