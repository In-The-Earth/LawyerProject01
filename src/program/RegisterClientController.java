package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterClientController {

    @FXML
    private Button back_btn,res_btn;
    @FXML
    private TextField name_text,email_text,username_text,id_card_text;
    @FXML
    private PasswordField pass_text,conpass_text;
    @FXML
    private DatePicker date_pick;

    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

}
