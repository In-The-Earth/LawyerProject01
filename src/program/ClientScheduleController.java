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
import java.util.ArrayList;

public class ClientScheduleController {
    @FXML
    private Button back_btn,show_btn;
    @FXML
    private Button m8,m9,m10,m11,m12,m13,m14,m15,tu8,tu9,tu10,tu11,tu12,tu13,tu14,tu15,w8,w9,w10,w11,w12,w13,w14,w15,th8,th9,th10,th11,th12,th13,th14,th15,f8,f9,f10,f11,f12,f13,f14,f15;
    @FXML
    private DatePicker date_pick;
    @FXML
    private Label d1,d2,d3,d4,d5;

    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private String username;

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(username);
                clientArrayList = DBhelper.read_Client();
                scheduleArrayList = DBhelper.read_Schedule();
                Button[] btns = {m8,m9,m10,m11,m12,m13,m14,m15,tu8,tu9,tu10,tu11,tu12,tu13,tu14,tu15,w8,w9,w10,w11,w12,w13,w14,w15,th8,th9,th10,th11,th12,th13,th14,th15,f8,f9,f10,f11,f12,f13,f14,f15};
                String[] data = LocalDate.now().toString().split("-");
                int today = Integer.parseInt(data[2]);
                System.out.println(today);

            }
        });
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    public void handleGoLogout_btnOnAction(ActionEvent event) throws IOException {
        Main.change_scene(getClass(),back_btn,"loginPage.fxml");
    }

    @FXML
    public void handleGoShow_btnOnAction(ActionEvent event) throws IOException {

    }

    @FXML
    public void handleGoF15_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"enter_book.fxml");
        Main.change_scene(loader,getClass(),f15,"enter_book.fxml");

    }
}
