package program;

import Model.Client;
import Model.Lawyer;
import Model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBhelper {

    static public ArrayList<Users> read_Users(){
        ArrayList<Users> usersArrayList = new ArrayList<>();
        String sql = "SELECT ID,Username,Password,UserType FROM Users";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                usersArrayList.add(new Users(resultSet.getInt("ID"),resultSet.getString("Username"),resultSet.getString("Password"),resultSet.getString("UserType")));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return usersArrayList;
    }

    static public ArrayList<Lawyer> read_Lawyer(){
        ArrayList<Lawyer> lawyerArrayList = new ArrayList<>();
        String sql = "SELECT ID,Username,Password,Name,Email,Tel,Date FROM Lawyer";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                lawyerArrayList.add(new Lawyer(resultSet.getInt("ID"),resultSet.getString("Username"),resultSet.getString("Password"),resultSet.getString("Name"),
                        resultSet.getString("Email"),resultSet.getString("Tel"),resultSet.getString("Date")));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return lawyerArrayList;
    }

    static public ArrayList<Client> read_Client(){
        ArrayList<Client> clientArrayList = new ArrayList<>();
        String sql = "SELECT ID,Username,Password,Name,Email,Id_Card,Tel,Date FROM Client";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                clientArrayList.add(new Client(resultSet.getInt("ID"),resultSet.getString("Username"),resultSet.getString("Password"),resultSet.getString("Name"),
                        resultSet.getString("Email"),resultSet.getString("Id_Card"),resultSet.getString("Tel"),resultSet.getString("Date")));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return clientArrayList;
    }

}
