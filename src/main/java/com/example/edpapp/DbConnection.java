package com.example.edpapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.edpapp.models.NewGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnection implements IDbConnection {


    Configuration configuration;

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
        NewGame game = new NewGame();

        game.setTownName("miasto");
        game.setFaction("rome");
        game.setLevel("sandbox");
        session.save(game);
        session.getTransaction().commit();
        session.close();
    }
}
