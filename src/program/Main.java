package program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        primaryStage.setTitle("Lawyer Schedule");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        getConnection();
//        createTable();
    }
    public Connection getConnection() throws Exception {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            System.out.println("Connected");
            PreparedStatement create1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Users' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Username' TEXT, 'Password' TEXT, 'UserType' TEXT);");
            PreparedStatement create2 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Lawyer' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Username' TEXT, 'Password' TEXT, 'Name' TEXT, 'Email' TEXT, 'Tel' TEXT, 'Date' TEXT);");
            PreparedStatement create3 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Client' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Username' TEXT, 'Password' TEXT, 'Name' TEXT, 'Email' TEXT, 'Id_Card' TEXT, 'Tel' TEXT, 'Date' TEXT);");
            //
            //db table create
            //
            create1.executeUpdate();
            create2.executeUpdate();
            create3.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    static void change_scene(Class cass,Button btn, String fxml)throws IOException {
        Parent loader = FXMLLoader.load(cass.getResource(fxml));
        Scene scene = new Scene(loader);
        Stage a = (Stage) btn.getScene().getWindow();
        a.setScene(scene);
    }


//    public void createTable() throws Exception{
//        try {
//            Connection connection = getConnection();
//            PreparedStatement create = connection.prepareStatement(
//                    "CREATE TABLE IF NOT EXISTS Persons (PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));");
//            create.executeUpdate();
//        }
//        catch (Exception e){
//            System.out.print(e);
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
