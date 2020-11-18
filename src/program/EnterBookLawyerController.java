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

public class EnterBookLawyerController {
    private Lawyer lawyer;
    private String time;
    private String day;
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    @FXML
    private Button back_btn,submit_btn;
    @FXML
    private Label lid_txt,ln_txt,lt_txt,day_txt,time_txt,w1,w2,w3;
    @FXML
    private ComboBox<String> tch_cb;
    @FXML
    private ComboBox<String> cch_cb;
    @FXML
    private ComboBox<String> do_cb;
    @FXML
    private TextArea des_txt;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lawyerArrayList = DBhelper.read_Lawyer();
                scheduleArrayList = DBhelper.read_Schedule();
                clientArrayList = DBhelper.read_Client();
                System.out.println(lawyer.getUsername());
                w1.setText("");
                w2.setText("");
                w3.setText("");
                lid_txt.setText(Integer.toString(lawyer.getId()));
                ln_txt.setText(lawyer.getName());
                lt_txt.setText(lawyer.getTel());
                day_txt.setText(day);
                time_txt.setText(getTime_toString());
                tch_cb.getItems().addAll("แพ่ง");
                tch_cb.getItems().addAll("อาญา");
                do_cb.getItems().addAll("Talk");
                do_cb.getItems().addAll("Go to court");
                System.out.println(getClients());
                for(String u : getClients()){
                    cch_cb.getItems().addAll(u);
                }
            }
        });
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    public ArrayList<String> getClients(){
        ArrayList<String> clients = new ArrayList<>();
        for(Client u : clientArrayList){
            clients.add(u.getId() +": "+ u.getName());
        }
        return clients;
    }

    @FXML
    public void handleGoSubmit_btnOnAction(ActionEvent event) throws IOException {
        w1.setText("");
        w2.setText("");
        w3.setText("");
        System.out.println(tch_cb.getValue());
        System.out.println(cch_cb.getValue());
        if(do_cb.getValue() == null){
            w3.setText("Choose One!");
            return;
        }
        if(tch_cb.getValue() == null){
            w1.setText("choose Type!");
            return;
        }
        if(cch_cb.getValue() == null){
            w2.setText("choose Client!");
            return;
        }
        if(check_client()){
            w2.setText("This client is busy \nin this selected time.");
            return;
        }
        String[] d = cch_cb.getValue().split(":");
        int id_cl = Integer.parseInt(d[0].trim());
        DBhelper.write_Schedule(id_cl,lawyer.getId(),tch_cb.getValue(),"Accepted",do_cb.getValue(),time,day,"null",des_txt.getText());

        FXMLLoader loader = Main.getLoader(getClass(),"workSchedulePage.fxml");
        Main.change_scene(loader,getClass(),submit_btn,"workSchedulePage.fxml");
        WorkScheduleController c = loader.getController();
        c.setUsername(lawyer.getUsername());
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"workSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"workSchedulePage.fxml");
        WorkScheduleController c = loader.getController();
        c.setUsername(lawyer.getUsername());

    }

    public boolean check_client(){
        ArrayList<String> lawyers = new ArrayList<>();
        String[] d = cch_cb.getValue().split(":");
        int id_ly = Integer.parseInt(d[0].trim());
        for(Schedule u : scheduleArrayList){
            if(u.getClient_id() == id_ly) {
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
