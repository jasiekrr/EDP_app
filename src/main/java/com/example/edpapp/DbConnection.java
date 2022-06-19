package com.example.edpapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.edpapp.models.GameStat;
import com.example.edpapp.models.NewGame;
import com.example.edpapp.models.NewgameEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnection implements IDbConnection {

    Configuration configuration;
    Session session;

    public DbConnection(){
        setUp();
    }

    public void setUp(){
        configuration = new Configuration();
        configuration.configure("com/example/edpapp/META-INf/hibernate.cfg.xml");
        configuration.addAnnotatedClass(NewGame.class);
        configuration.addAnnotatedClass(GameStat.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        session = sessionFactory.openSession();

        //open session for newgame table



    }

    public void makeConnection() throws ClassNotFoundException {
        String connectionString;
        Connection connection;
        try{
            Class.forName("org.postgresql.Driver");
            connectionString = "jdbc:postgresql://localhost:5432/gamesDb";
            connection = DriverManager.getConnection(connectionString, "postgres", "password");
            System.out.println("connection created!");
        } catch (SQLException e) {
            System.out.println("error occured");
            throw new RuntimeException(e);
        }

    }
    public void saveNewGame(NewGame newGame){
        try{
            session.getTransaction().begin();
            session.persist(newGame);
            session.getTransaction().commit();
            session.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Database error");
        }
    }

    @Override
    public NewGame getLastNewGame() {
        session.getTransaction().begin();
        NewGame newGame = (NewGame) session.createQuery("from NewGame order by id desc").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        System.out.println("newGame: " + newGame.toString());
        return newGame;
    }

    public void saveGame(GameStat gameStat){
        try{
            session.beginTransaction();
            session.persist(gameStat);
            session.getTransaction().commit();
            session.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Database error");
        }
    }
}
