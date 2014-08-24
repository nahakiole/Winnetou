package ch.robinglauser.sac;


import javafx.scene.Parent;
import javafx.stage.Stage;

public class SettingsController {
    private Stage stage;

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    private boolean firstTime = true;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {

        this.stage = stage;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}
