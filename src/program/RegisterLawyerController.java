package program;

import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sun.security.pkcs11.Secmod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class RegisterLawyerController {

    @FXML
    private Button back_btn,res_btn;
    @FXML
    private TextField name_text,email_text,username_text,tel_text;
    @FXML
    private PasswordField pass_text,conpass_text;
    @FXML
    private DatePicker date_pick;
    @FXML
    private Label ut_txt,pi_txt,ci_txt,w1,w3,w4,w5;

    private ArrayList<Users> usersArrayList = new ArrayList<>();

    public void initialize(){
        usersArrayList = DBhelper.read_Users();
        w1.setText("");
        w3.setText("");
        w4.setText("");
        w5.setText("");
        ci_txt.setText("");
    }
    @FXML
    public void handleGoLogin_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

    @FXML
    public void handleGoRes_btnOnAction(ActionEvent event) throws IOException {
        ut_txt.setText("");
        pi_txt.setText("");
        w1.setText("");
        w3.setText("");
        w4.setText("");
        w5.setText("");
        ci_txt.setText("");
        if(checkText_field()){
            ci_txt.setText("Complete Information!!");
            return;
        }

        if(checkUser() == true){
            ut_txt.setText("This username is taken !");
            if (pass_text.getText().equals(conpass_text.getText()) != true) {
                pi_txt.setText("Incorrect Confirm !");
                return;
            }
            return;
        }
        else {
            if (pass_text.getText().equals(conpass_text.getText()) != true) {
                pi_txt.setText("Incorrect Confirm !");
                return;
            } else {
                boolean c = true;
                if(check_email()){
                    w1.setText("Invalid email!");
                    c = false;
                }
                if(!check_pn()){
                    w3.setText("Invalid number!");
                    c = false;
                }if(!check_bd()){
                    w4.setText("Invalid date!");
                    c = false;
                }
                if(!check_name()){
                    w5.setText("Invalid name!");
                    c = false;
                }
                if(c == false){
                    return;
                }
                DBhelper.write_Lawyer(username_text.getText(),pass_text.getText(),name_text.getText(),email_text.getText(),tel_text.getText(),date_pick.getValue().toString());
                DBhelper.write_Users(username_text.getText(),pass_text.getText(),"L");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Registration Success!", ButtonType.OK);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK){
                    Main.change_scene(getClass(), res_btn, "loginPage.fxml");
                }
            }
        }

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

    public boolean check_pn(){
        for(int i =0; i < tel_text.getText().length() ; i++ ){
            if(!Character.isDigit(tel_text.getText().charAt(i))){
                return false;
            }
        }
        if(tel_text.getText().length() != 10){
            return false;
        }
        return true;
    }

    public boolean check_name(){
        for(int i =0; i < name_text.getText().length() ; i++ ){
            if(Character.isDigit(name_text.getText().charAt(i))){
                return false;
            }
        }
        return true;
    }

    public boolean check_email(){
        for(int i=0 ; i<email_text.getText().length();i++){
            if(email_text.getText().charAt(i) == '@'){
                return false;
            }
        }
        return true;
    }
    public boolean check_bd(){
        if(Period.between(LocalDate.parse(date_pick.getValue().toString()),LocalDate.now()).getYears() >= 1){
            return true;
        }
        return false;
    }

    public boolean checkText_field(){
        if(name_text.getText().equals("")|| email_text.getText().equals("")|| username_text.getText().equals("")|| tel_text.getText().equals("")|| pass_text.getText().equals("")|| conpass_text.getText().equals("")|| date_pick.getValue() == null){
            return true;
        }
        return false;
    }
}
