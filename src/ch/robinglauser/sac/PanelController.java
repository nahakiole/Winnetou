package ch.robinglauser.sac;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PanelController {

    public void doSomething(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
