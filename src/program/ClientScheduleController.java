package program;

import Model.Client;
import Model.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ClientScheduleController {
    @FXML
    private Button back_btn,show_btn,sAll_btn;
    @FXML
    private Button m8,m9,m10,m11,m12,m13,m14,m15,tu8,tu9,tu10,tu11,tu12,tu13,tu14,tu15,w8,w9,w10,w11,w12,w13,w14,w15,th8,th9,th10,th11,th12,th13,th14,th15,f8,f9,f10,f11,f12,f13,f14,f15;
    @FXML
    private DatePicker date_pick;
    @FXML
    private Label d1,d2,d3,d4,d5,user_txt,w;

    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private Client client;
    private ArrayList<Schedule> userSchedules = new ArrayList<>();
    private String username;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                w.setText("");
                System.out.println(username);
                user_txt.setText(username);
                clientArrayList = DBhelper.read_Client();
                scheduleArrayList = DBhelper.read_Schedule();
                for(Client u : clientArrayList){
                    if(u.getUsername().equals(username)){
                        client = new Client(u.getId(),u.getUsername(),u.getPassword(),u.getName(),u.getEmail(),u.getId_card(),u.getTel(),u.getBirth_date());
                    }
                }
                for(Schedule v : scheduleArrayList){
                    if(v.getClient_id() == client.getId()){
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

    public void showSchedule(LocalDate localDate){
        Button[] btns = {m8,m9,m10,m11,m12,m13,m14,m15,tu8,tu9,tu10,tu11,tu12,tu13,tu14,tu15,w8,w9,w10,w11,w12,w13,w14,w15,th8,th9,th10,th11,th12,th13,th14,th15,f8,f9,f10,f11,f12,f13,f14,f15};
        for(Button b : btns){
            b.setStyle("-fx-border-color: Black");
            b.setText("-Free-");
        }
        for(LocalDate v : arrayListWeek(localDate)){
            if(v.getDayOfWeek().toString().equals("MONDAY")){
                d1.setText(v.toString());
            }
            else if(v.getDayOfWeek().toString().equals("TUESDAY")) {
                d2.setText(v.toString());
            }
            else if(v.getDayOfWeek().toString().equals("WEDNESDAY")) {
                d3.setText(v.toString());
            }
            else if(v.getDayOfWeek().toString().equals("THURSDAY")) {
                d4.setText(v.toString());
            }
            else if(v.getDayOfWeek().toString().equals("FRIDAY")) {
                d5.setText(v.toString());
            }
        }
        for(Schedule u : userSchedules){
            for(LocalDate v : arrayListWeek(localDate)){
                if(u.getDay().equals(v.toString())){
                    for(int i=0; i<40;i++){
                        if(u.getTime().equals(btns[i].getId())){
                            if(u.getStatus().equals("Request")){
//                                System.out.println("successBtn01");
                                btns[i].setStyle("-fx-background-color: Yellow");
                                btns[i].setText("-Booked-\nRequest");
                            }
                            else if(u.getStatus().equals("Accepted")){
                                if(u.getType_where().equals("Go to court")) {
                                    btns[i].setStyle("-fx-background-color: #334ab0");
                                    btns[i].setText("-Booked-\nGo to court");
                                }else {
                                    btns[i].setStyle("-fx-background-color: Green");
                                    btns[i].setText("-Booked-\nAccept");
                                }
                            }else if(u.getStatus().equals("Reject")){
                                btns[i].setStyle("-fx-background-color: Red");
                                btns[i].setText("-Booked-\nReject");
                            }
                        }
                        else {
                            if(btns[i].getText().equals("-Booked-\nRequest")){
                                continue;
                            }else if(btns[i].getText().equals("-Booked-\nAccept")){
                                continue;
                            }else if(btns[i].getText().equals("-Booked-\nReject")){
                                continue;
                            }else if(btns[i].getText().equals("-Booked-\nGo to court")){
                                continue;
                            } else {
                                btns[i].setText("-Free-");
                            }
                        }
                    }
                }
//                if(v.getDayOfWeek().toString().equals("MONDAY")){
//                    d1.setText(v.toString());
//                }
//                else if(v.getDayOfWeek().toString().equals("TUESDAY")) {
//                    d2.setText(v.toString());
//                }
//                else if(v.getDayOfWeek().toString().equals("WEDNESDAY")) {
//                    d3.setText(v.toString());
//                }
//                else if(v.getDayOfWeek().toString().equals("THURSDAY")) {
//                    d4.setText(v.toString());
//                }
//                else if(v.getDayOfWeek().toString().equals("FRIDAY")) {
//                    d5.setText(v.toString());
//                }

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
    public void handleGoSAll_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"showClientScheduleAllPage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"showClientScheduleAllPage.fxml");
        ShowClientScheduleAllController c = loader.getController();
        c.setUserSchedules(userSchedules);
        c.setUsername(username);
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
        Button b = (Button)event.getSource();
        if(b.getText().equals("-Free-")){
            if(b.getId().charAt(0) == 'm'){
                if(Period.between(LocalDate.parse(d1.getText()),LocalDate.now()).getDays() > 0 ){
                    w.setText("can't select the past day!");
                    return;
                }
            }else if(b.getId().charAt(0) == 'w'){
                if(Period.between(LocalDate.parse(d3.getText()),LocalDate.now()).getDays() > 0 ){
                    w.setText("can't select the past day!");
                    return;
                }
            }else if(b.getId().charAt(0) == 'f'){
                if(Period.between(LocalDate.parse(d5.getText()),LocalDate.now()).getDays() > 0 ){
                    w.setText("can't select the past day!");
                    return;
                }
            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'u'){
                if(Period.between(LocalDate.parse(d2.getText()),LocalDate.now()).getDays() > 0 ){
                    w.setText("can't select the past day!");
                    return;
                }
            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'h'){
                if(Period.between(LocalDate.parse(d4.getText()),LocalDate.now()).getDays() > 0 ){
                    w.setText("can't select the past day!");
                    return;
                }
            }
            FXMLLoader loader = Main.getLoader(getClass(),"enter_book.fxml");
            Main.change_scene(loader,getClass(),b,"enter_book.fxml");
            EnterBookController c = loader.getController();
            c.setClient(client);
            c.setTime(b.getId());
            if(b.getId().charAt(0) == 'm'){
                c.setDay(d1.getText());
            }else if(b.getId().charAt(0) == 'w'){
                c.setDay(d3.getText());
            }else if(b.getId().charAt(0) == 'f'){
                c.setDay(d5.getText());
            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'u'){
                c.setDay(d2.getText());
            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'h'){
                c.setDay(d4.getText());
            }
        }
        else{
            FXMLLoader loader = Main.getLoader(getClass(),"clientScheduleDetailPage.fxml");
            Main.change_scene(loader,getClass(),b,"clientScheduleDetailPage.fxml");
            ClientScheduleDetailController c = loader.getController();
            c.setClient(client);
            c.setTime(b.getId());
            if(b.getId().charAt(0) == 'm'){
                c.setDay(d1.getText());
            }else if(b.getId().charAt(0) == 'w'){
                c.setDay(d3.getText());
            }else if(b.getId().charAt(0) == 'f'){
                c.setDay(d5.getText());
            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'u'){
                c.setDay(d2.getText());
            }else if(b.getId().charAt(0) == 't'& b.getId().charAt(1) == 'h'){
                c.setDay(d4.getText());
            }
        }
    }
}
