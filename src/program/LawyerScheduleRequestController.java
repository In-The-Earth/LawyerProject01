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

public class LawyerScheduleRequestController {
    private String username;
    private int id;

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

    @FXML
    private TableColumn<Schedule, String> de_c;

    private ArrayList<Schedule> scheduleArrayList = new ArrayList<>();
    private ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
    private ArrayList<Client> clientArrayList = new ArrayList<>();
    private ArrayList<Schedule> userSchedules = new ArrayList<>();
    private ArrayList<Schedule> userSchedules02 = new ArrayList<>();

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scheduleArrayList = DBhelper.read_Schedule();
                lawyerArrayList = DBhelper.read_Lawyer();
                clientArrayList = DBhelper.read_Client();
                for(Lawyer u : lawyerArrayList){
                    if(u.getUsername().equals(username)){
                        id = u.getId();
                    }
                }
                for(Schedule u : scheduleArrayList){
                    if(u.getLawyer_id() == id){
                        userSchedules.add(u);
                    }
                }
                System.out.println(username);
                System.out.println(id);

                for(Schedule u : userSchedules){
                    for(Client v : clientArrayList){
                        if(u.getClient_id() == v.getId()){
                            if(u.getStatus().equals("Request")) {
                                userSchedules02.add(new Schedule(u.getType_case(), u.getType_where(), u.getTime(), u.getDay(), v.getName(),
                                        new Client(u.getId(), v.getUsername(), v.getPassword(), v.getName(), v.getEmail(), v.getId_card(), v.getTel(), v.getBirth_date()), username));
                            }
                        }
                    }
                }
                ObservableList<Schedule> schedules = FXCollections.observableArrayList(userSchedules02);

                day_c.setCellValueFactory(new PropertyValueFactory<>("day"));
                time_c.setCellValueFactory(new PropertyValueFactory<>("time"));
                tc_c.setCellValueFactory(new PropertyValueFactory<>("type_case"));
                do_c.setCellValueFactory(new PropertyValueFactory<>("type_where"));
                cli_c.setCellValueFactory(new PropertyValueFactory<>("client_name"));
                de_c.setCellValueFactory(new PropertyValueFactory<>("detail"));
                table.setItems(schedules);

            }
        });
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    @FXML
    public void handleGoBack_btnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = Main.getLoader(getClass(),"workSchedulePage.fxml");
        Main.change_scene(loader,getClass(),back_btn,"workSchedulePage.fxml");
        WorkScheduleController c = loader.getController();
        c.setUsername(username);
    }

}
