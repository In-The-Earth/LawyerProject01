package program;

import Model.Client;
import Model.Lawyer;
import Model.Schedule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class EnterBookController {
    private Client client;
    private String time;
    private String day;
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    @FXML
    private Button back_btn,submit_btn;
    @FXML
    private Label cid_txt,cn_txt,ct_txt,cic_txt,ca_txt,day_txt,time_txt,w1,w2;
    @FXML
    private ComboBox<String> tch_cb;
    @FXML
    private ComboBox<String> lch_cb;
    @FXML
    private TextArea des_txt;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lawyerArrayList = DBhelper.read_Lawyer();
                scheduleArrayList = DBhelper.read_Schedule();
                System.out.println(client.getUsername());
                w1.setText("");
                w2.setText("");
                cid_txt.setText(Integer.toString(client.getId()));
                cn_txt.setText(client.getName());
                ct_txt.setText(client.getTel());
                cic_txt.setText(client.getId_card());
                int age = Period.between(LocalDate.parse(client.getBirth_date()),LocalDate.now()).getYears();
                ca_txt.setText(Integer.toString(age));
                day_txt.setText(day);
                time_txt.setText(getTime_toString());
                tch_cb.getItems().addAll("แพ่ง");
                tch_cb.getItems().addAll("อาญา");
                tch_cb.getItems().addAll("ล้มละลาย");
                for(String u : getLawyers()){
                    lch_cb.getItems().addAll(u);
                }
            }
        });
    }

    public ArrayList<String> getLawyers(){
        ArrayList<String> lawyers = new ArrayList<>();
        for(Lawyer u : lawyerArrayList){
            lawyers.add(u.getId() +": "+ u.getName());
        }
        return lawyers;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"clientSchedulePage.fxml");
        ClientScheduleController c = loader.getController();
        c.setUsername(client.getUsername());

    }

    @FXML
    public void handleGoSubmit_btnOnAction(ActionEvent event) throws IOException {
        w1.setText("");
        w2.setText("");
        System.out.println(tch_cb.getValue());
        System.out.println(lch_cb.getValue());
        if(tch_cb.getValue() == null){
            w1.setText("choose Type!");
            return;
        }
        if(lch_cb.getValue() == null){
            w2.setText("choose Lawyer!");
            return;
        }
        if(check_lawyer()){
            w2.setText("This lawyer is busy \nin this selected time.");
            return;
        }
        String[] d = lch_cb.getValue().split(":");
        int id_ly = Integer.parseInt(d[0].trim());
        DBhelper.write_Schedule(client.getId(),id_ly,tch_cb.getValue(),"Request","Talk",time,day,"null",des_txt.getText());

        FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
        Main.change_scene(loader,getClass(),submit_btn,"clientSchedulePage.fxml");
        ClientScheduleController c = loader.getController();
        c.setUsername(client.getUsername());
    }

    public boolean check_lawyer(){
        ArrayList<String> lawyers = new ArrayList<>();
        String[] d = lch_cb.getValue().split(":");
        int id_ly = Integer.parseInt(d[0].trim());
        for(Schedule u : scheduleArrayList){
            if(u.getLawyer_id() == id_ly) {
                if (u.getDay().equals(day)) {
                    if (u.getTime().equals(time)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getTime_toString(){
        String result = "";
        if(time.charAt(0) == 'm') {
            if (time.charAt(1) == '8') {
                return "monday 8.00-9.00";
            } else if (time.charAt(1) == '9') {
                return "monday 9.00-10.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '0') {
                return "monday 10.00-11.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '1') {
                return "monday 11.00-12.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '2') {
                return "monday 12.00-13.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '3') {
                return "monday 13.00-14.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '4') {
                return "monday 14.00-15.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '5') {
                return "monday 15.00-16.00";
            }
        }
        else if(time.charAt(0) == 'w') {
            if (time.charAt(1) == '8') {
                return "wednesday 8.00-9.00";
            } else if (time.charAt(1) == '9') {
                return "wednesday 9.00-10.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '0') {
                return "wednesday 10.00-11.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '1') {
                return "wednesday 11.00-12.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '2') {
                return "wednesday 12.00-13.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '3') {
                return "wednesday 13.00-14.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '4') {
                return "wednesday 14.00-15.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '5') {
                return "wednesday 15.00-16.00";
            }
        }
        else if(time.charAt(0) == 'f') {
            if (time.charAt(1) == '8') {
                return "friday 8.00-9.00";
            } else if (time.charAt(1) == '9') {
                return "friday 9.00-10.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '0') {
                return "friday 10.00-11.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '1') {
                return "friday 11.00-12.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '2') {
                return "friday 12.00-13.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '3') {
                return "friday 13.00-14.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '4') {
                return "friday 14.00-15.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '5') {
                return "friday 15.00-16.00";
            }
        }else if(time.charAt(0) == 't' & time.charAt(1) == 'u') {
            if (time.charAt(2) == '8') {
                return "tuesday 8.00-9.00";
            } else if (time.charAt(2) == '9') {
                return "tuesday 9.00-10.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '0') {
                return "tuesday 10.00-11.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '1') {
                return "tuesday 11.00-12.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '2') {
                return "tuesday 12.00-13.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '3') {
                return "tuesday 13.00-14.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '4') {
                return "tuesday 14.00-15.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '5') {
                return "tuesday 15.00-16.00";
            }
        }
        else if(time.charAt(0) == 't' & time.charAt(1) == 'h') {
            if (time.charAt(2) == '8') {
                return "thursday 8.00-9.00";
            } else if (time.charAt(2) == '9') {
                return "thursday 9.00-10.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '0') {
                return "thursday 10.00-11.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '1') {
                return "thursday 11.00-12.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '2') {
                return "thursday 12.00-13.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '3') {
                return "thursday 13.00-14.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '4') {
                return "thursday 14.00-15.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '5') {
                return "thursday 15.00-16.00";
            }
        }
        return result;
    }
}
