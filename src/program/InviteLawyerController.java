package program;

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
import java.util.ArrayList;

public class InviteLawyerController {
    private String username;
    private String time;
    private String day;
    private Schedule schedule;

    @FXML
    private Label w;
    @FXML
    private Button back_btn,add_ly_btn;
    @FXML
    private ComboBox<String> lch_cb;
    @FXML
    private TableView<Schedule> table;
    @FXML
    private TableColumn<Schedule, String> ln_c;
    @FXML
    private TableColumn<Schedule, String> delete_c;

    private ArrayList<Integer> ids_arrays = new ArrayList<>();
    private ArrayList<Schedule> show_schedules = new ArrayList<>();

    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("rf");
                lawyerArrayList = DBhelper.read_Lawyer();
                scheduleArrayList = DBhelper.read_Schedule();
                System.out.println("rf2");
                for(Schedule u : scheduleArrayList){
                    if(u.getId() == schedule.getId()){
                        schedule = new Schedule(u.getId(),u.getClient_id(),u.getLawyer_id(),u.getType_case(),u.getStatus(),u.getType_where(),u.getTime(),u.getDay(),u.getId_sup(),u.getDes());
                    }
                }
                if(!schedule.getId_sup().equals("null")){
                    String[] ids = schedule.getId_sup().split(":");
                    for(String i : ids){
                        ids_arrays.add(Integer.parseInt(i));
                    }
                    System.out.println(ids_arrays);
                    for(Lawyer t : lawyerArrayList){
                        for (int y : ids_arrays){
                            if(t.getId() == y){
                                show_schedules.add(new Schedule(schedule.getId(),t.getId(),t.getName(),schedule.getId_sup(),username,day,time,schedule));
                            }
                        }
                    }
                    ObservableList<Schedule> schedules = FXCollections.observableArrayList(show_schedules);

                    ln_c.setCellValueFactory(new PropertyValueFactory<>("lawyer_name"));
                    delete_c.setCellValueFactory(new PropertyValueFactory<>("delete_lawyer"));
                    table.setItems(schedules);

                }
                w.setText("");

                for(String u : getLawyers()){
                    lch_cb.getItems().addAll(u);
                }
            }
        });
    }

    @FXML
    public void handleGoAdd_ly_btnOnAction(ActionEvent event) throws IOException {
        w.setText("");
        if(lch_cb.getValue() == null){
            w.setText("can't add!");
            return;
        }
        if(check_lawyer()){
            w.setText("This lawyer is busy in this selected time.");
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
//        DBhelper.write_Schedule(schedule.getClient_id(),id_ly,schedule.getType_case(),"Accepted",schedule.getType_where(),time,day,"non",schedule.getDes());

        FXMLLoader loader = Main.getLoader(getClass(), "inviteLawyerPage.fxml");
        Main.change_scene(loader, getClass(),add_ly_btn, "inviteLawyerPage.fxml");
        InviteLawyerController c = loader.getController();
        c.setUsername(username);
        c.setDay(day);
        c.setTime(time);
        c.setSchedule(schedule);

//        System.out.println("s1");
//        DBhelper.write_Schedule(schedule.getClient_id(),id_ly,schedule.getType_case(),"Accepted",schedule.getType_where(),time,day,"no",schedule.getDes());

//        FXMLLoader loader = Main.getLoader(getClass(), "inviteConfirmPage.fxml");
//        Main.change_scene(loader, getClass(), add_ly_btn, "inviteConfirmPage.fxml");
//        InviteConfirmPageController c = loader.getController();
//        c.setUsername(username);
//        c.setDay(day);
//        c.setTime(time);
//        c.setSchedule(schedule);
//        c.setId_sup_result(id_sup);
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"lawyerScheduleDetail.fxml");
        Main.change_scene(loader,getClass(),back_btn,"lawyerScheduleDetail.fxml");
        LawyerScheduleDetailController c = loader.getController();
        c.setUsername(username);
        c.setDay(day);
        c.setTime(time);
    }

    public ArrayList<String> getLawyers(){
        ArrayList<String> lawyers = new ArrayList<>();
        for(Lawyer u : lawyerArrayList){
            if(u.getId() != schedule.getLawyer_id()) {
                lawyers.add(u.getId() + ": " + u.getName());
            }
        }
//        for(Lawyer u : lawyerArrayList){
//            for(int v : ids_arrays) {
//                if (u.getId() != schedule.getLawyer_id()) {
//                    if(u.getId() != v) {
//                        lawyers.add(u.getId() + ": " + u.getName());
//                    }
//                }
//            }
//        }
        return lawyers;
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
//                            System.out.println("c1");
                            return true;
                        }
                    }
                }
            }
        }

        for(int v : ids_arrays){
            if(v == id_ly){
//                System.out.println("c2");
                return true;
            }
        }
        return false;
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

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
