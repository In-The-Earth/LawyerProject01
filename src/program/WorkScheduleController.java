package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class WorkScheduleController {
    @FXML
    private Button back_btn,request_btn;


    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    public void handleGoLogout_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

    @FXML
    public void handleGoRequestLawyerPage_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleRequest.fxml");
        Main.change_scene(loader,getClass(),request_btn,"lawyerScheduleRequest.fxml");
        LawyerScheduleRequestController c = loader.getController();
        c.setUsername(username);
    }
}
