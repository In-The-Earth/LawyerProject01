package program;

import Model.Client;
import Model.Lawyer;
import Model.Schedule;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class LawyerScheduleDetailController {
    private String username;
    private Client client;
    private String time;
    private String day;
    private Schedule schedule;
    private String lawyer_name;
    private ArrayList<Integer> ids_arrays = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> show_schedules = new ArrayList<>();

    @FXML
    private Button back_btn,cancel_btn,add_ly_btn;
    @FXML
    private Label cid_txt,cn_txt,ct_txt,cic_txt,ca_txt,day_txt,time_txt,w;
    @FXML
    private Label des_txt,case_txt,lawyer_txt,status_txt,dw_txt;
    @FXML
    private ComboBox<String> lch_cb;
    @FXML
    private TableView<Schedule> table;
    @FXML
    private TableColumn<Schedule, String> ln_c;
    @FXML
    private TableColumn<Schedule, String> delete_c;

    public void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
//                w.setText("");
                scheduleArrayList = DBhelper.read_Schedule();
                clientArrayList = DBhelper.read_Client();
                lawyerArrayList = DBhelper.read_Lawyer();
                des_txt.setWrapText(true);
                for(Schedule u : scheduleArrayList){
                    for(Client l : clientArrayList){
                        if(u.getTime().equals(time)){
                            if(u.getDay().equals(day)){
                                schedule = new Schedule(u.getId(),u.getClient_id(),u.getLawyer_id(),u.getType_case(),u.getStatus(),u.getType_where(),u.getTime(),u.getDay(),u.getId_sup(),u.getDes());
                            }
                        }
                    }
                }
                for(Lawyer u : lawyerArrayList){
                    if(u.getId() == schedule.getLawyer_id()){
                        lawyer_name = u.getName();
                    }
                }
                for(Schedule u : scheduleArrayList) {
                    for (Client v : clientArrayList) {
                        if(u.getClient_id() == v.getId()){
                            client = new Client(v.getId(),v.getUsername(),v.getPassword(),v.getName(),v.getEmail(),v.getId_card(),v.getTel(),v.getBirth_date());
                        }
                    }
                }
//                if(!schedule.getId_sup().equals("null")){
//                    String[] ids = schedule.getId_sup().split(":");
//                    for(String u : ids){
//                        ids_arrays.add(Integer.parseInt(u));
//                    }
//                    for(Lawyer u : lawyerArrayList){
//                        for (int v : ids_arrays){
//                            if(u.getId() == v){
//                                show_schedules.add(new Schedule(schedule.getId(),u.getId(),u.getName(),schedule.getId_sup()));
//                            }
//                        }
//                    }
//                    ObservableList<Schedule> schedules = FXCollections.observableArrayList(show_schedules);
//
//                    ln_c.setCellValueFactory(new PropertyValueFactory<>("lawyer_name"));
//                    delete_c.setCellValueFactory(new PropertyValueFactory<>("delete_lawyer"));
//                    table.setItems(schedules);
//                }
//                else if(schedule.getId_sup().equals("no")){
//                    add_ly_btn.disableProperty();
//                }

//                String[] ids = schedule.getId_sup().split(":");
//                for(String u : ids){
//                    ids_arrays.add(Integer.parseInt(u));
//                }
//                for(Lawyer u : lawyerArrayList){
//                    for (int v : ids_arrays){
//                        if(u.getId() == v){
//                            show_schedules.add(new Schedule(schedule.getId(),u.getId(),u.getName(),schedule.getId_sup()));
//                        }
//                    }
//                }
//                ObservableList<Schedule> schedules = FXCollections.observableArrayList(show_schedules);
//
//                ln_c.setCellValueFactory(new PropertyValueFactory<>("lawyer_name"));
//                delete_c.setCellValueFactory(new PropertyValueFactory<>("delete_lawyer"));
//                table.setItems(schedules);

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

//                for(String u : getLawyers()){
//                    lch_cb.getItems().addAll(u);
//                }
            }
        });
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"workSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"workSchedulePage.fxml");
        WorkScheduleController c = loader.getController();
        c.setUsername(username);
    }

    @FXML
    public void handleGoCancel_btnOnAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You want to cancel this Booked?", ButtonType.YES,ButtonType.NO);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.YES) {
            DBhelper.delete_Schedule(schedule.getId());
            FXMLLoader loader = Main.getLoader(getClass(), "workSchedulePage.fxml");
            Main.change_scene(loader, getClass(), cancel_btn, "workSchedulePage.fxml");
            WorkScheduleController c = loader.getController();
            c.setUsername(username);
        }

    }

    @FXML
    public void handleGoAdd_ly_btnOnAction(ActionEvent event) throws IOException {
        w.setText("");
        if(lch_cb.getValue() == null){
            w.setText("can't add!");
            return;
        }
        if(check_lawyer()){
            w.setText("This lawyer is busy \nin this selected time.");
            return;
        }
        String[] d = lch_cb.getValue().split(":");
        int id_ly = Integer.parseInt(d[0].trim());
        String id_sup = "";
        if(schedule.getId_sup().equals("null")){
            id_sup = Integer.toString(id_ly);
        }else{
            id_sup = schedule.getId_sup() +":"+ id_ly;
        }
        DBhelper.update_ID_sup(schedule.getId(),id_sup);
//        DBhelper.write_Schedule(schedule.getClient_id(),id_ly,schedule.getType_case(),"Accepted",schedule.getType_where(),time,day,"no",schedule.getDes());

        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleDetail.fxml");
        Main.change_scene(loader,getClass(),add_ly_btn,"lawyerScheduleDetail.fxml");
        LawyerScheduleDetailController c = loader.getController();
        c.setUsername(username);
        c.setTime(time);
        c.setDay(day);

//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You want to cancel this Booked?", ButtonType.YES,ButtonType.NO);
//        alert.showAndWait();
//        if(alert.getResult() == ButtonType.YES) {
//            DBhelper.delete_Schedule(schedule.getId());
//            FXMLLoader loader = Main.getLoader(getClass(), "workSchedulePage.fxml");
//            Main.change_scene(loader, getClass(), cancel_btn, "workSchedulePage.fxml");
//            WorkScheduleController c = loader.getController();
//            c.setUsername(username);
//        }
    }

    public boolean check_lawyer(){
        ArrayList<String> lawyers = new ArrayList<>();
        String[] d = lch_cb.getValue().split(":");
        int id_ly = Integer.parseInt(d[0].trim());
        for(Schedule u : scheduleArrayList){
            if(u.getLawyer_id() == id_ly) {
                if (u.getDay().equals(day)) {
                    if (u.getTime().equals(time)) {
                        if(u.getStatus().equals("Request") || u.getStatus().equals("Accepted")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<String> getLawyers(){
        ArrayList<String> lawyers = new ArrayList<>();
//        for(Lawyer u : lawyerArrayList){
//            if(u.getId() != schedule.getLawyer_id()) {
//                lawyers.add(u.getId() + ": " + u.getName());
//            }
//        }
        for(Lawyer u : lawyerArrayList){
            for(int v : ids_arrays) {
                if (u.getId() != schedule.getLawyer_id()) {
                    if(u.getId() != v) {
                        lawyers.add(u.getId() + ": " + u.getName());
                    }
                }
            }
        }
        return lawyers;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
