package ch.robinglauser.winnetou;

import ch.robinglauser.winnetou.Services.ApacheService;
import ch.robinglauser.winnetou.Services.FileZillaService;
import ch.robinglauser.winnetou.Services.MySQLService;
import ch.robinglauser.winnetou.Services.Service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;


public class ControlCenter extends JFrame implements ActionListener {

    ArrayList<Service> services = new ArrayList<Service>();

    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    ResourceBundle bundle;
    Connection connection;
    Settings settings;
    HostFile hostFile;
    JTable table;

    public ControlCenter() {
        openConnection();
        setLookAndFeel();


        JPanel servicePanel = getServicePanel();

        hostFile = new HostFile(new File("C:\\Windows\\System32\\drivers\\etc\\hosts"));

        ArrayList entityList = hostFile.getData();


        servicePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(servicePanel, BorderLayout.NORTH);
        servicePanel.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(actionPanel, BorderLayout.SOUTH);
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        JButton btnNewButton_1 = new JButton("New button");
        actionPanel.add(btnNewButton_1);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        actionPanel.add(btnNewButton);




        table = new JTable();


        TableModel model = new TableModel(entityList);


        table.setModel(model);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    HostEntry hostEntry = (HostEntry) model.wordsList.get(row);
                    HostEntryEditor.edit(hostEntry);
                    model.fireTableDataChanged();
                }
            }
        });

        JButton button = new JButton(bundle.getString("add_new_host"));
        button.addActionListener(e -> {
            HostEntry hostEntry = new HostEntry("","127.0.0.1");
            HostEntryEditor.edit(hostEntry);
            hostFile.addHostEntry(hostEntry);
            model.fireTableDataChanged();
        });

        table.setDragEnabled(false);

        Panel buttonPanel = new Panel();
        buttonPanel.add(button);


        JPanel mainPanel = new JPanel();
        mainPanel.add(servicePanel);

        JScrollPane scrollPane = new JScrollPane(table);
        table.getParent().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                if (table.getPreferredSize().width < table.getParent().getWidth()) {
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                } else {
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }
            }
        });
        scrollPane.setMaximumSize(new Dimension(10,10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel);

        add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
        setVisible(true);

    }

    private JPanel getServicePanel() {
        services.add(new ApacheService());
        services.add(new MySQLService());
        services.add(new FileZillaService());

        JPanel startPanel = new JPanel();
        Iterator<Service> it = services.iterator();

        JPanel ApacheService = new JPanel();


        while (it.hasNext()) {
            Service service = it.next();
            System.out.println(service.getName());
            JLabel jLabel = new JLabel(service.getName());
            jLabel.setHorizontalAlignment(SwingConstants.LEFT);

            JButton jButton = new JButton("Start");
            labels.add(jLabel);
            buttons.add(jButton);
            JLabel lblLed = new JLabel("●");
            lblLed.setForeground(Color.RED);
            lblLed.setHorizontalAlignment(SwingConstants.RIGHT);

            lblLed.setFont(new Font(null, Font.PLAIN, 18));

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (service.isRunning()) {
                        lblLed.setForeground(Color.YELLOW);
                        service.stop();
                        lblLed.setForeground(Color.RED);
                        jButton.setText("Start");
                    } else {
                        lblLed.setForeground(Color.YELLOW);
                        service.start();
                        lblLed.setForeground(Color.GREEN);
                        jButton.setText("Stop");
                    }
                }
            });

            JPanel servicePanel = new JPanel();

            servicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            servicePanel.add(lblLed);

            servicePanel.add(jButton);
            servicePanel.add(jLabel);
            startPanel.add(servicePanel);
        }
        return startPanel;
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Fail");
        }
        setFont(new Font("Arial", Font.PLAIN, 10));
        setTitle("Winnetou");
    }

    private void openConnection() {
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:apache.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        settings = new Settings(connection);
        String[] language = settings.get("language").split("_");
        Locale locale = new Locale(language[0], language[1]);

        bundle = ResourceBundle.getBundle("Winnetou", locale);
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
