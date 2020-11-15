package program;

import Model.Client;
import Model.Lawyer;
import Model.Users;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LoginPageController {
    @FXML
    private Button login_btn,cnac_btn;
    @FXML
    private TextField username_text;
    @FXML
    private PasswordField pass_text;
    @FXML
    private Label uw_txt,paw_txt;

    private ArrayList<Users> usersArrayList = new ArrayList<>();
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usersArrayList = DBhelper.read_Users();
                lawyerArrayList = DBhelper.read_Lawyer();
                clientArrayList = DBhelper.read_Client();
            }
        });
    }


    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException{
        uw_txt.setText("");
        paw_txt.setText("");
        if(checkUser() == true) {
            for (Users v : usersArrayList) {
                if ((v.getUsername()).equals(username_text.getText())) {
                    if ((v.getPassword()).equals(pass_text.getText())) {
                        if((v.getUserType()).equals("L")){
                            FXMLLoader loader = Main.getLoader(getClass(),"workSchedulePage.fxml");
                            Main.change_scene(loader,getClass(),login_btn,"workSchedulePage.fxml");
                            WorkScheduleController c = loader.getController();
                            c.setUsername(v.getUsername());
                        }
                        else if((v.getUserType()).equals("C")){
                            FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
                            Main.change_scene(loader,getClass(),login_btn,"clientSchedulePage.fxml");
                            ClientScheduleController c = loader.getController();
                            c.setUsername(v.getUsername());
                        }
                    } else {
                        paw_txt.setText("Incorrect Password");
                    }
                }
            }
        }else {
            uw_txt.setText("Incorrect Username");
        }

    }

    @FXML
    public void handleGoCnac_btnOnAction(ActionEvent event) throws IOException{
        Main.change_scene(getClass(),cnac_btn,"chooseTypeRegister.fxml");
    }

    public boolean checkUser(){
        String user = username_text.getText();
        for(Users v : usersArrayList){
            if(v.getUsername().equals(user)){
                return true;
            }
        }
        return false;
    }
}
