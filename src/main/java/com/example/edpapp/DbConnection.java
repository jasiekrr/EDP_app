package com.example.edpapp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.example.edpapp.models.GameStat;
import com.example.edpapp.models.NewGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnection implements IDbConnection {

    Configuration configuration;
    Session session;
    SessionFactory sessionFactory;

    public DbConnection(){
        setUp();
    }

    public void setUp(){
        configuration = new Configuration();
        configuration.configure("com/example/edpapp/META-INf/hibernate.cfg.xml");
        configuration.addAnnotatedClass(NewGame.class);
        configuration.addAnnotatedClass(GameStat.class);

        sessionFactory = configuration.buildSessionFactory();
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
        session = sessionFactory.openSession();
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
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        NewGame newGame = (NewGame) session.createQuery("from NewGame order by id desc").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        session.close();
        System.out.println("newGame: " + newGame.toString());
        return newGame;
    }

    public void saveGame(GameStat gameStat){
        session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.merge(gameStat);
            session.getTransaction().commit();
            session.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Database error");
        }
    }

    public List<GameStat> getAllGames(){
        session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<GameStat> gameStats = session.createQuery("from GameStat").list();
        session.getTransaction().commit();
        session.close();
        return gameStats;
    }

    @Override
    public GameStat getGameBySaveGameColumns(String faction, String townName, Timestamp createdOn) {
        session = sessionFactory.openSession();
        System.out.println("session = " + session.toString());
        session.getTransaction().begin();
        GameStat gameStat = (GameStat) session.createQuery("from GameStat where newgame.faction = :faction and newgame.townName = :townName and createdon = :createdOn").setParameter("faction", faction).setParameter("townName", townName).setParameter("createdOn", createdOn).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return gameStat;
    }
}
