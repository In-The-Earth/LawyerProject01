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

public class ShowClientScheduleAllController {
    private Client client;
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
    private TableColumn<Schedule, String> lay_c;

    @FXML
    private TableColumn<Schedule, String> sta_c;

    private ArrayList<Schedule> userSchedules = new ArrayList<>();
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Schedule> userSchedules02 = new ArrayList<>();

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lawyerArrayList = DBhelper.read_Lawyer();
                for(Schedule u : userSchedules){
                    for(Lawyer v : lawyerArrayList){
//                        System.out.println(u.getLawyer_id());
//                        System.out.println(v.getId());
//                        System.out.println(v.getName());
                        if(u.getLawyer_id() == v.getId()){
                            userSchedules02.add(new Schedule(u.getType_case(),u.getStatus(),u.getType_where(),u.getTime_toString(),u.getDay(),v.getName()));
                        }
                    }
                }
                ObservableList<Schedule> schedules = FXCollections.observableArrayList(userSchedules02);

                day_c.setCellValueFactory(new PropertyValueFactory<>("day"));
                time_c.setCellValueFactory(new PropertyValueFactory<>("time"));
                tc_c.setCellValueFactory(new PropertyValueFactory<>("type_case"));
                do_c.setCellValueFactory(new PropertyValueFactory<>("type_where"));
                lay_c.setCellValueFactory(new PropertyValueFactory<>("lawyer_name"));
                sta_c.setCellValueFactory(new PropertyValueFactory<>("status"));
                table.setItems(schedules);

            }
        });
    }


    public void setUserSchedules(ArrayList<Schedule> userSchedules) {
        this.userSchedules = userSchedules;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"clientSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"clientSchedulePage.fxml");
        ClientScheduleController c = loader.getController();
        c.setUsername(username);
    }


}
