package program;

import Model.Client;
import Model.Lawyer;
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
                time_txt.setText(time);
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
        String[] d = lch_cb.getValue().split(":");
        int id_ly = Integer.parseInt(d[0].trim());
        DBhelper.write_Schedule(client.getId(),id_ly,tch_cb.getValue(),"Request","Talk",time,day,"null",des_txt.getText());

        FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
        Main.change_scene(loader,getClass(),submit_btn,"clientSchedulePage.fxml");
        ClientScheduleController c = loader.getController();
        c.setUsername(client.getUsername());
    }
}
