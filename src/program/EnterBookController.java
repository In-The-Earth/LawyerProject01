package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class EnterBookController {
    @FXML
    private Button back_btn,submit_btn;

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"clientSchedulePage.fxml");
    }

    @FXML
    public void handleGoSubmit_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
        Main.change_scene(loader,getClass(),submit_btn,"clientSchedulePage.fxml");
        DBhelper.write_Schedule(1,1,"แพ่ง","Requset","Talk","m1","2020-11-17","null");
    }
}
