package program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        primaryStage.setTitle("Lawyer Schedule");
        primaryStage.setScene(new Scene(root, 800, 600));
        Image icon = new Image(getClass().getResourceAsStream("/resources/icon.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.show();
        getConnection();
//        createTable();
    }
    public Connection getConnection() throws Exception {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:lySchedule.db");
            System.out.println("Connected");
            PreparedStatement create1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Users' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Username' TEXT, 'Password' TEXT, 'UserType' TEXT);");
            PreparedStatement create2 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Lawyer' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Username' TEXT, 'Password' TEXT, 'Name' TEXT, 'Email' TEXT, 'Tel' TEXT, 'Date' TEXT);");
            PreparedStatement create3 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Client' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Username' TEXT, 'Password' TEXT, 'Name' TEXT, 'Email' TEXT, 'Id_Card' TEXT, 'Tel' TEXT, 'Date' TEXT);");
            PreparedStatement create4 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Schedule' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Client_ID' INTEGER, 'Lawyer_ID' INTEGER, 'TypeCase' TEXT, 'Status' TEXT, 'TypeWhere' TEXT, 'Time' TEXT, 'DAY' TEXT, 'ID_sup' TEXT, 'Description' TEXT);");
            //
            //db table create
            //
            create1.executeUpdate();
            create2.executeUpdate();
            create3.executeUpdate();
            create4.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    static void change_scene(Class cass, Button btn, String fxml)throws IOException {
        FXMLLoader loader = new FXMLLoader(cass.getResource(fxml));
        Scene scene = new Scene(loader.load());
        Stage a = (Stage) btn.getScene().getWindow();
        a.setScene(scene);
    }

    public static void change_scene(FXMLLoader loader, Class cass, Button btn, String fxml)throws IOException {
        Scene scene = new Scene(loader.load());
        Stage a = (Stage) btn.getScene().getWindow();
        a.setScene(scene);
    }

    public static FXMLLoader getLoader(Class cass, String fxml){
        FXMLLoader loader = new FXMLLoader(cass.getResource(fxml));
        return loader;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
