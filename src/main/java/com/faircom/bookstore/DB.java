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

            var book1 = Book.getInstance("Against a Dark Background", "Iain M. Banks", "Space Opera", 626, "Culture");
            var book2 = Book.getInstance("The Three-Body Problem", "Cixun Liu", "Hard SF", 416, "Remembrance of Earth's Past");
            var book3 = Book.getInstance("The Abyss Beyond Dreams", "Peter F. Hamilton", "Space Opera", 623, "Void Trilogy");
            var book4 = Book.getInstance("The Reality Dysfunction", "Peter F. Hamilton", "Space Opera", 1094, "The Night's Dawn Trilogy");
            var book5 = Book.getInstance("Salvation", "Peter F. Hamilton", "Space Opera", 565, "Salvation Sequence");
            var book6 = Book.getInstance("On the Steel Breeze", "Alastair Reynolds", "Space Opera", 532, "Poseidon's Children");
            var book7 = Book.getInstance("Shadow Captain", "Alastair Reynolds", "Space Opera", 426, "Revenger Trilogy");
            var book8 = Book.getInstance("Barbary Station", "R.E. Stearns", "SCIFI", 100, "");
            var book9 = Book.getInstance("The Peripheral", "William Gibson", "Cyberpunk", 485, "");

            session.beginTx();

            session.insertRecord(book1);
            session.insertRecord(book2);
            session.insertRecord(book3);
            session.insertRecord(book4);
            session.insertRecord(book5);
            session.insertRecord(book6);
            session.insertRecord(book7);
            session.insertRecord(book8);
            session.insertRecord(book9);

            session.commitTx();
        }
    }

    public AnnotatedSession getSession() {

        return session;
    }
}
