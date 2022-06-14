package com.example.edpapp;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public Connection link;

    public Connection getConnection() throws ClassNotFoundException {
        String databaseName = "";
        String databaseUser ="";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            link = DriverManager.getConnection(url);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return link;
    }
}
