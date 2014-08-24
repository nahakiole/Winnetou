package ch.robinglauser.sac;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.ResourceBundle;

public class PanelController {

    @FXML
    private TableView<VirtualHost> tableView;
    @FXML
    private TableColumn<VirtualHost, String> hostName;
    @FXML
    private TableColumn<VirtualHost, String> fileName;


    private ObservableList<VirtualHost> masterData = FXCollections.observableArrayList();


    public void initialize() {
        masterData.add(new VirtualHost("local.robinglauser.ch","/User/Robin/"));

        tableView.setItems(masterData);
    }


    private SettingsController settingsController = new SettingsController();

    public void openSettings(ActionEvent actionEvent) {
        if (this.settingsController.isFirstTime()){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
                final Stage stage = new Stage();
                stage.setTitle("Settings");
                stage.setScene(new Scene(root, 450, 450));

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent arg0) {
                        stage.hide();
                    }
                });
                stage.show();
                settingsController.setStage(stage);
                settingsController.setFirstTime(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            settingsController.getStage().show();
        }


    }
}
