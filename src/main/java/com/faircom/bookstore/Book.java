package com.faircom.bookstore;

import com.faircom.db.client.rio.annotations.Field;
import com.faircom.db.client.rio.annotations.Index;
import com.faircom.db.client.rio.annotations.Record;
import com.faircom.db.client.rio.direct.schema.FieldType;

@Record(db = "db_catalog", table = "tbl_books",
        fields = {
                @Field(property="Name", name = "bk_name", type = FieldType.VARCHAR, length = 40),
                @Field(property="Category", name = "bk_cat", type = FieldType.VARCHAR, length = 40),
                @Field(property="NumPages", name = "bk_num_pages", type = FieldType.INTEGER)},
        indexes={
                @Index(fields="bk_name"),
                @Index(fields="bk_cat"),
                @Index(fields={"bk_name", "bk_cat"})
})
public class Book {

    private String name;
    private String category;
    private int numPages;

    public Book() {}

    public Book(String name, String category, int numPages) {

        this.name = name;
        this.category = category;
        this.numPages = numPages;
    }

    public static Book getInstance(String name, String category, int numPages) {

        return new Book(name, category, numPages);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public String toString() {

        return "name=" + name +
                ", category=" + category +
                ", numbPages=" + numPages;
    }
}
