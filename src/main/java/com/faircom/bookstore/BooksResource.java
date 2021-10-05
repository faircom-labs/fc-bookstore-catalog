package com.faircom.bookstore;

import FairCom.CtreeDb.CTException;
import com.faircom.db.client.rio.filter.RecordFilter;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

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

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBookById(@PathParam String id) throws CTException {

        var session = db.getSession();

        var book = session.getRecord("db_catalog", "tbl_books", Integer.valueOf(id), Book.class);

        return book;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksByName(@QueryParam("name") String name) throws CTException {

        var session = db.getSession();

        var filter = RecordFilter.getInstance("db_catalog", "tbl_books");

        filter.add("bk_name", name);

        var books = session.getRecords(filter, Book.class);

        return books;
    }

    @Path("categories/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksByCategory(@PathParam String category) throws CTException {

        var session = db.getSession();

        var filter = RecordFilter.getInstance("db_catalog", "tbl_books");

        filter.add("bk_cat", category.toUpperCase(Locale.ROOT));

        var books = session.getRecords(filter, Book.class);

        return books;
    }
}