package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        createTable();
    }
    public Connection getConnection() throws Exception {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1"
                    ,"root","");
            System.out.println("Connected");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    public void createTable() throws Exception{
        try {
            Connection connection = getConnection();
            PreparedStatement create = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Persons (PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));");
            create.executeUpdate();
        }
        catch (Exception e){
            System.out.print(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
