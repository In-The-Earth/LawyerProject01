package program;

import Model.Client;
import Model.Lawyer;
import Model.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class LawyerScheduleRequestDetailController {

    private Client client;
    private String time;
    private String day;
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();

    @FXML
    private Button back_btn,accept_btn,reject_btn;
    @FXML
    private Label cid_txt,cn_txt,ct_txt,cic_txt,ca_txt,day_txt,time_txt;

    @FXML
    private Label des_txt,case_txt,lawyer_txt;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lawyerArrayList = DBhelper.read_Lawyer();
                scheduleArrayList = DBhelper.read_Schedule();


                System.out.println(client.getUsername());
                cid_txt.setText(Integer.toString(client.getId()));
                cn_txt.setText(client.getName());
                ct_txt.setText(client.getTel());
                cic_txt.setText(client.getId_card());
                int age = Period.between(LocalDate.parse(client.getBirth_date()),LocalDate.now()).getYears();
                ca_txt.setText(Integer.toString(age));
                day_txt.setText(day);
                time_txt.setText(time);

            }
        });
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"WorkSchedulePage.fxml");
    }
}
