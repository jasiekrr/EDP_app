package com.example.edpapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.edpapp.models.NewGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

public class DbConnection {


    public void makeConnection() throws ClassNotFoundException {
        String connectionString;
        Connection connection;
        try{
            Class.forName("org.postgresql.Driver");
            connectionString = "jdbc:postgresql://localhost:5432/gamesDb";
            connection = DriverManager.getConnection(connectionString, "postgres", "password");
            System.out.println("connection created!");
        } catch (SQLException e) {
            System.out.println("wystapil blad");
            throw new RuntimeException(e);
        }

    }
    public void connect(){

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(NewGame.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(new NewGame(1,"rome","sandbox", "miasto"));
        session.getTransaction().commit();
    }
}
