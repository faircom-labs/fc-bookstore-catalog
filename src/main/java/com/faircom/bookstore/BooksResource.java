package com.faircom.bookstore;

import FairCom.CtreeDb.CTException;
import com.faircom.db.client.rio.filter.RecordFilter;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;

@Path("/books")
public class BooksResource {

    @Inject
    DB db;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() throws CTException {

        var session = db.getSession();

        return session.getRecords(Book.class);
    }

    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam("id") int id) throws CTException {

        var session = db.getSession();

        return session.getRecord("db_catalog", "tbl_books", id, Book.class);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksByName(@PathParam("name") String name) throws CTException {

        var session = db.getSession();

        var filter = RecordFilter.getInstance("db_catalog", "tbl_books");

        filter.add("bk_name", name);

        return session.getRecords(filter, Book.class);
    }

    @Path("/category/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksByCategory(@PathParam("category") String category) throws CTException {

        var session = db.getSession();

        var filter = RecordFilter.getInstance("db_catalog", "tbl_books");

        filter.add("bk_cat", category.toUpperCase(Locale.ROOT));

        return session.getRecords(filter, Book.class);
    }
}