package com.faircom.bookstore;

import com.faircom.db.client.rio.Engine;
import com.faircom.db.client.rio.annotations.AnnotatedSession;

import javax.inject.Singleton;

@Singleton
public class DB {

    Engine engine;

    AnnotatedSession session;

    public DB() throws Exception {

        engine = com.faircom.db.client.rio.Engine.getInstance();

        engine.start();

        session = engine.getAnnotatedSession();

        if (!session.isDatabaseExists("db_catalog")) {

            session.createDatabase("db_catalog");
            session.createTable(Book.class);

            var book1 = Book.getInstance("ON THE STEEL BREEZE", "SCIFI", 532);
            var book2 = Book.getInstance("SHADOW CAPTAIN", "SCIFI", 426);
            var book3 = Book.getInstance("TEST", "SCIFI", 100);

            session.beginTx();

            session.insertRecord(book1);
            session.insertRecord(book2);
            session.insertRecord(book3);

            session.commitTx();
        }
    }

    public AnnotatedSession getSession() {

        return session;
    }
}
