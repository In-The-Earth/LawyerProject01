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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ShowLawyerScheduleController {
    private String username;
    @FXML
    private Button back_btn;

    @FXML
    private TableView<Schedule> table;

    @FXML
    private TableColumn<Schedule, String> day_c;

    @FXML
    private TableColumn<Schedule, String> time_c;

    @FXML
    private TableColumn<Schedule, String> tc_c;

    @FXML
    private TableColumn<Schedule, String> do_c;

    @FXML
    private TableColumn<Schedule, String> cli_c;

    private ArrayList<Schedule> userSchedules = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Schedule> userSchedules02 = new ArrayList<>();

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clientArrayList = DBhelper.read_Client();
                for(Schedule u : userSchedules){
                    for(Client v : clientArrayList){
//                        System.out.println(u.getLawyer_id());
//                        System.out.println(v.getId());
//                        System.out.println(v.getName());
                        if(u.getClient_id() == v.getId()){
                            if(u.getStatus().equals("Accepted")) {
                                userSchedules02.add(new Schedule(u.getType_case(), u.getStatus(), u.getType_where(), u.getDT_toString(), u.getDay(), v.getName()));
                            }
                        }
                    }
                }
                ObservableList<Schedule> schedules = FXCollections.observableArrayList(userSchedules02);

                day_c.setCellValueFactory(new PropertyValueFactory<>("day"));
                time_c.setCellValueFactory(new PropertyValueFactory<>("time"));
                tc_c.setCellValueFactory(new PropertyValueFactory<>("type_case"));
                do_c.setCellValueFactory(new PropertyValueFactory<>("type_where"));
                cli_c.setCellValueFactory(new PropertyValueFactory<>("lawyer_name"));
                table.setItems(schedules);

            }
        });
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserSchedules(ArrayList<Schedule> userSchedules) {
        this.userSchedules = userSchedules;
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"workSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"workSchedulePage.fxml");
        WorkScheduleController c = loader.getController();
        c.setUsername(username);
    }
}
