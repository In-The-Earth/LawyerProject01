package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterLawyerController {

    @FXML
    private Button back_btn,res_btn;
    @FXML
    private TextField name_text,email_text,username_text;
    @FXML
    private PasswordField pass_text,conpass_text;
    @FXML
    private DatePicker date_pick;


    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

    @FXML
    public void handleGoRes_btnOnAction(ActionEvent event) throws IOException {
        System.out.println("success01");
        String sql = "INSERT INTO Users(Username,Password,UserType) VALUES(?,?,?)";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"User01");
            preparedStatement.setString(2,"pass01");
            preparedStatement.setString(3,"L");
            preparedStatement.executeUpdate();
            System.out.println("success02");
        }
        catch (Exception e){
            System.out.println(e);
        }

        Main.change_scene(getClass(),res_btn,"loginPage.fxml");
    }
}
