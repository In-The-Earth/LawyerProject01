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
    private Button back_btn;

    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(loader);
        Stage a = (Stage) back_btn.getScene().getWindow();
        a.setScene(scene);
    }
}
