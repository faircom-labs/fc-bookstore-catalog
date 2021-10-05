package com.faircom.bookstore;

import FairCom.CtreeDb.CTException;
import com.faircom.db.client.rio.filter.RecordFilter;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public class BooksResource {

    @Inject
    DB db;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksByName(@QueryParam("name") String name) throws CTException {

        var session = db.getSession();

        var filter = RecordFilter.getInstance("db_catalog", "tbl_books");

        filter.add("bk_name", name);

        var books = session.filterRecords(filter, Book.class);

        return books;
    }

    @Path("{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooksByCategory(String category) throws CTException {

        var session = db.getSession();

        var filter = RecordFilter.getInstance("db_catalog", "tbl_books");

        filter.add("bk_cat", category);

        var books = session.filterRecords(filter, Book.class);

        return books;
    }
}