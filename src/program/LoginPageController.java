package program;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

public class LoginPageController {
    @FXML
    private Button login_btn,cnac_btn;

    public void initialize(){
        String sql = "SELECT ID,Username,Password,UserType FROM Users";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("ID")+resultSet.getString("Username"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException{
        Main.change_scene(getClass(),login_btn,"workSchedulePage.fxml");
    }

    @FXML
    public void handleGoCnac_btnOnAction(ActionEvent event) throws IOException{
        Main.change_scene(getClass(),cnac_btn,"chooseTypeRegister.fxml");
    }
}
