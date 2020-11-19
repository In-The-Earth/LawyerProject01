package program;

import Model.Client;
import Model.Lawyer;
import Model.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class LawyerScheduleRequestDetailController {

    private String username;
    private Client client;
    private String time;
    private String day;
    private String lawyer_name;
    private int id_schedule;
    private Schedule schedule ;
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private ArrayList<Schedule> userArrayList = new ArrayList<>();

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
                System.out.println(time);
                System.out.println(day);
                System.out.println(username);
//                for(Lawyer l : lawyerArrayList){
//                    if(l.getUsername().equals(username)){
//                        userArrayList.ad
//                    }
//                }
                for(Schedule u : scheduleArrayList){
                    for(Lawyer l : lawyerArrayList){
                        if(l.getUsername().equals(username)){
                            System.out.println(u.getTime());
                            System.out.println(time);
                            if(u.getTime().equals(time)){
                                if(u.getDay().equals(day)){
                                    lawyer_name = l.getName();
                                    id_schedule = u.getId();
                                    schedule = new Schedule(u.getId(),u.getClient_id(),u.getLawyer_id(),u.getType_case(),u.getStatus(),u.getTime(),u.getTime(),u.getDay(),u.getId_sup(),u.getDes());
                                }
                            }
                        }
                    }
                }
                System.out.println(id_schedule);
                cid_txt.setText(Integer.toString(client.getId()));
                cn_txt.setText(client.getName());
                ct_txt.setText(client.getTel());
                cic_txt.setText(client.getId_card());
                lawyer_txt.setText(lawyer_name);
                int age = Period.between(LocalDate.parse(client.getBirth_date()),LocalDate.now()).getYears();
                ca_txt.setText(Integer.toString(age));
                day_txt.setText(day);
                time_txt.setText(schedule.getDT_toString());
                case_txt.setText(schedule.getType_case());
                des_txt.setText(schedule.getDes());
                des_txt.setWrapText(true);

            }
        });
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleRequest.fxml");
        Main.change_scene(loader,getClass(),back_btn,"lawyerScheduleRequest.fxml");
        LawyerScheduleRequestController c = loader.getController();
        c.setUsername(username);
    }

    @FXML
    public void handleGoAccept_btnOnAction(ActionEvent event) throws IOException {
        DBhelper.update_Status(id_schedule,"Accepted");

        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleRequest.fxml");
        Main.change_scene(loader,getClass(),accept_btn,"lawyerScheduleRequest.fxml");
        LawyerScheduleRequestController c = loader.getController();
        c.setUsername(username);
    }

    @FXML
    public void handleGoReject_btnOnAction(ActionEvent event) throws IOException {
        DBhelper.update_Status(id_schedule,"Reject");

        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleRequest.fxml");
        Main.change_scene(loader,getClass(),accept_btn,"lawyerScheduleRequest.fxml");
        LawyerScheduleRequestController c = loader.getController();
        c.setUsername(username);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
