package sac;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.getIcons().add(new Image("file:image/tray.png"));

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SAC");
        primaryStage.setScene(new Scene(root, 500, 400));


        if (SystemTray.isSupported()) {
            setTrayIcon(primaryStage);
            Platform.setImplicitExit(false);
        }
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void setTrayIcon(final Stage primaryStage) {
        SystemTray sTray = SystemTray.getSystemTray();
        java.awt.Image image = Toolkit.getDefaultToolkit().getImage("image/tray.png");

        ActionListener listenerShow = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        primaryStage.show();
                    }
                });
            }
        };

        ActionListener listenerClose = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent arg0) {
                primaryStage.hide();
            }
        });

        PopupMenu popup = new PopupMenu();
        MenuItem showItem = new MenuItem("Open Control Panel");
        MenuItem exitItem = new MenuItem("Shut down");


        showItem.addActionListener(listenerShow);
        exitItem.addActionListener(listenerClose);

        popup.add(showItem);
        popup.addSeparator();
        popup.add(exitItem);

        TrayIcon icon = new TrayIcon(image, "SAC", popup);

        try {
            sTray.add(icon);
        } catch (AWTException e) {
            System.err.println(e);
        }
    }


    public static void main(String[] args) {
        com.apple.eawt.Application.getApplication().setDockIconImage(new ImageIcon("image/tray.png").getImage());
        launch(args);
    }
}
