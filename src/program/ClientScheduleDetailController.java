package program;

import Model.Client;
import Model.Lawyer;
import Model.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ClientScheduleDetailController {
    private String username;
    private Client client;
    private String time;
    private String day;
    private String lawyer_name;
    private int id_schedule;
    private Schedule schedule;
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();

    @FXML
    private Button back_btn,cancel_btn;
    @FXML
    private Label cid_txt,cn_txt,ct_txt,cic_txt,ca_txt,day_txt,time_txt;
    @FXML
    private Label des_txt,case_txt,lawyer_txt,status_txt,dw_txt;

    public void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scheduleArrayList = DBhelper.read_Schedule();
                clientArrayList = DBhelper.read_Client();
                lawyerArrayList = DBhelper.read_Lawyer();
                for(Schedule u : scheduleArrayList){
                    for(Client l : clientArrayList){
                        if(l.getUsername().equals(client.getUsername())){
                            if(u.getTime().equals(time)){
                                if(u.getDay().equals(day)){
                                    schedule = new Schedule(u.getId(),u.getClient_id(),u.getLawyer_id(),u.getType_case(),u.getStatus(),u.getType_where(),u.getTime(),u.getDay(),u.getId_sup(),u.getDes());
                                }
                            }
                        }
                    }
                }
                for(Lawyer u : lawyerArrayList){
                    if(u.getId() == schedule.getLawyer_id()){
                        lawyer_name = u.getName();
                    }
                }

                cid_txt.setText(Integer.toString(client.getId()));
                cn_txt.setText(client.getName());
                ct_txt.setText(client.getTel());
                cic_txt.setText(client.getId_card());
                int age = Period.between(LocalDate.parse(client.getBirth_date()),LocalDate.now()).getYears();
                ca_txt.setText(Integer.toString(age));
                day_txt.setText(day);
                time_txt.setText(schedule.getDT_toString());
                case_txt.setText(schedule.getType_case());
                dw_txt.setText(schedule.getType_where());
                lawyer_txt.setText(lawyer_name);
                status_txt.setText(schedule.getStatus());
                des_txt.setText(schedule.getDes());
            }
        });
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"clientSchedulePage.fxml");
        ClientScheduleController c = loader.getController();
        c.setUsername(client.getUsername());
    }

    @FXML
    public void handleGoCancel_btnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You want to cancel this Booked?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            DBhelper.delete_Schedule(schedule.getId());
            FXMLLoader loader = Main.getLoader(getClass(), "clientSchedulePage.fxml");
            Main.change_scene(loader, getClass(), cancel_btn, "clientSchedulePage.fxml");
            ClientScheduleController c = loader.getController();
            c.setUsername(client.getUsername());
        }

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

}