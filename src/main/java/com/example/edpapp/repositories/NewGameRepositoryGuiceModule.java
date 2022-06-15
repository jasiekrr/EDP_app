package com.example.edpapp.repositories;

import com.example.edpapp.DbConnection;
import com.example.edpapp.IDbConnection;

public class NewGameRepositoryGuiceModule extends com.google.inject.AbstractModule{
    @Override
    protected void configure() {
        bind(IDbConnection.class).to(DbConnection.class);
    }
}
