package com.example.edpapp.repositories;

import com.example.edpapp.DbConnection;
import com.example.edpapp.IDbConnection;
import com.example.edpapp.specials.GameStartResources;

public class NewGameRepositoryGuiceModule extends com.google.inject.AbstractModule{
    @Override
    protected void configure() {
        bind(IDbConnection.class).to(DbConnection.class);
        bind(GameStartResources.class).in(com.google.inject.Singleton.class);
    }
}
