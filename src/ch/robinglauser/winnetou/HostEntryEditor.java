package ch.robinglauser.winnetou;


import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HostEntryEditor {

    public static void edit(HostEntry hostEntry){
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
                    // When we lose focus, ask for it back but only once
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
    }

}
