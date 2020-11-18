package program;

import Model.Client;
import Model.Lawyer;
import Model.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WorkScheduleController {
    @FXML
    private Button back_btn,request_btn,show_btn,sAll_btn;
    @FXML
    private Button m8,m9,m10,m11,m12,m13,m14,m15,tu8,tu9,tu10,tu11,tu12,tu13,tu14,tu15,w8,w9,w10,w11,w12,w13,w14,w15,th8,th9,th10,th11,th12,th13,th14,th15,f8,f9,f10,f11,f12,f13,f14,f15;
    @FXML
    private DatePicker date_pick;
    @FXML
    private Label d1,d2,d3,d4,d5,user_txt;

    private String username;
    private Lawyer lawyer;
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> userSchedules = new ArrayList<>();

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(username);
                user_txt.setText(username);
                lawyerArrayList = DBhelper.read_Lawyer();
                scheduleArrayList = DBhelper.read_Schedule();
                for(Lawyer u : lawyerArrayList){
                    if(u.getUsername().equals(username)){
                        lawyer = new Lawyer(u.getId(),u.getUsername(),u.getPassword(),u.getName(),u.getEmail(),u.getTel(),u.getBirth_date());
                    }
                }
                for(Schedule v : scheduleArrayList){
                    if(v.getLawyer_id() == lawyer.getId()){
                        userSchedules.add(v);
                    }
                }
                showSchedule(LocalDate.now());

            }
        });
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void showSchedule(LocalDate localDate) {
        Button[] btns = {m8, m9, m10, m11, m12, m13, m14, m15, tu8, tu9, tu10, tu11, tu12, tu13, tu14, tu15, w8, w9, w10, w11, w12, w13, w14, w15, th8, th9, th10, th11, th12, th13, th14, th15, f8, f9, f10, f11, f12, f13, f14, f15};
        for (Button b : btns) {
            b.setStyle("-fx-border-color: Black");
            b.setText("-Free-");
        }
        for (LocalDate v : arrayListWeek(localDate)) {
            if (v.getDayOfWeek().toString().equals("MONDAY")) {
                d1.setText(v.toString());
            } else if (v.getDayOfWeek().toString().equals("TUESDAY")) {
                d2.setText(v.toString());
            } else if (v.getDayOfWeek().toString().equals("WEDNESDAY")) {
                d3.setText(v.toString());
            } else if (v.getDayOfWeek().toString().equals("THURSDAY")) {
                d4.setText(v.toString());
            } else if (v.getDayOfWeek().toString().equals("FRIDAY")) {
                d5.setText(v.toString());
            }
        }
        for (Schedule u : userSchedules) {
            for (LocalDate v : arrayListWeek(localDate)) {
                if (u.getDay().equals(v.toString())) {
                    for (int i = 0; i < 40; i++) {
                        if (u.getTime().equals(btns[i].getId())) {
                            if (u.getStatus().equals("Accepted")) {
//                                System.out.println("successBtn01");
                                btns[i].setStyle("-fx-background-color: #42d4f5");
                                btns[i].setText("-Booked-\nAccept");
                            }
                        } else {
                            if (btns[i].getText().equals("-Booked-\nAccept")) {
                                continue;
                            } else {
                                btns[i].setText("-Free-");
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<LocalDate> arrayListWeek(LocalDate date){
        ArrayList<LocalDate> arrayList = new ArrayList<>();
        if(date.getDayOfWeek().toString().equals("MONDAY")){
            arrayList.add(date);
            arrayList.add(date.plusDays(1));
            arrayList.add(date.plusDays(2));
            arrayList.add(date.plusDays(3));
            arrayList.add(date.plusDays(4));
//            System.out.println(arrayList);
        }
        else if(date.getDayOfWeek().toString().equals("TUESDAY")){
            return arrayListWeek(date.plusDays(-1));
        }
        else if(date.getDayOfWeek().toString().equals("WEDNESDAY")){
            return arrayListWeek(date.plusDays(-2));
        }
        else if(date.getDayOfWeek().toString().equals("THURSDAY")){
            return arrayListWeek(date.plusDays(-3));
        }
        else if(date.getDayOfWeek().toString().equals("FRIDAY")){
            return arrayListWeek(date.plusDays(-4));
        }
        else if(date.getDayOfWeek().toString().equals("SATURDAY")){
            return arrayListWeek(date.plusDays(-5));
        }
        else if(date.getDayOfWeek().toString().equals("SUNDAY")){
            return arrayListWeek(date.plusDays(1));
        }
        return arrayList;
    }

    @FXML
    public void handleGoLogout_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

    @FXML
    public void handleGoRequestLawyerPage_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleRequest.fxml");
        Main.change_scene(loader,getClass(),request_btn,"lawyerScheduleRequest.fxml");
        LawyerScheduleRequestController c = loader.getController();
        c.setUsername(username);
    }

    @FXML
    public void handleGoSAll_btnOnAction(ActionEvent event) throws IOException {
//        FXMLLoader loader = Main.getLoader(getClass(),"showClientScheduleAllPage.fxml");
//        Main.change_scene(loader,getClass(),back_btn,"showClientScheduleAllPage.fxml");
//        ShowClientScheduleAllController c = loader.getController();
//        c.setUserSchedules(userSchedules);
//        c.setUsername(username);
    }

    @FXML
    public void handleGoShow_btnOnAction(ActionEvent event) throws IOException {
        if(date_pick.getValue() == null){
            return;
        }
        LocalDate date = date_pick.getValue();
        showSchedule(date);
    }

    @FXML
    public void handleSelectOnAction(ActionEvent event) throws IOException {
//        Button b = (Button)event.getSource();
//        if(b.getText().equals("-Free-")){
//            FXMLLoader loader = Main.getLoader(getClass(),"enter_book.fxml");
//            Main.change_scene(loader,getClass(),f15,"enter_book.fxml");
//            EnterBookController c = loader.getController();
//            c.setClient(client);
//            c.setTime(b.getId());
//            if(b.getId().charAt(0) == 'm'){
//                c.setDay(d1.getText());
//            }else if(b.getId().charAt(0) == 'w'){
//                c.setDay(d3.getText());
//            }else if(b.getId().charAt(0) == 'f'){
//                c.setDay(d5.getText());
//            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'u'){
//                c.setDay(d2.getText());
//            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'h'){
//                c.setDay(d4.getText());
//            }
//        }
//        else if(b.getText().equals("Booked")){
//            return;
//        }
    }
}
