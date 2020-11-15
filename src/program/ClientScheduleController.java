package program;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ClientScheduleController {
    @FXML
    private Button back_btn;

    private String username;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(username);
            }
        });
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    public void handleGoLogout_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }
}
