package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseTypeRegisterController {
    @FXML
    private Button back_btn, client_btn, lawyer_btn;



    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

    @FXML
    public void handleGoClient_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),client_btn,"registerClientPage.fxml");
    }

    @FXML
    public void handleGoLawyer_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),lawyer_btn,"registerLawyerPage.fxml");
//        Parent loader = FXMLLoader.load(getClass().getResource("registerLawyerPage.fxml"));
//        Scene scene = new Scene(loader);
//        Stage a = (Stage) lawyer_btn.getScene().getWindow();
//        a.setScene(scene);
    }

}
