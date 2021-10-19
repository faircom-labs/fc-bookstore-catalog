package com.faircom.bookstore;

import com.faircom.db.client.rio.annotations.Field;
import com.faircom.db.client.rio.annotations.Index;
import com.faircom.db.client.rio.annotations.PersistentRecord;
import com.faircom.db.client.rio.annotations.Record;
import com.faircom.db.client.rio.direct.schema.FieldType;

@Record(db = "db_catalog", table = "tbl_books",
        fields = {
                @Field(property = "Name", name = "bk_name", type = FieldType.VARCHAR, length = 40),
                @Field(property = "Author", name = "bk_author", type = FieldType.VARCHAR, length = 40),
                @Field(property = "Category", name = "bk_cat", type = FieldType.VARCHAR, length = 40),
                @Field(property = "NumPages", name = "bk_num_pages", type = FieldType.INTEGER),
                @Field(property = "Series", name = "bk_series", type = FieldType.VARCHAR, length = 40)},
        indexes={
                @Index(fields = "bk_name"),
                @Index(fields = "bk_author"),
                @Index(fields = "bk_cat"),
                @Index(fields = "bk_series"),
                @Index(fields = {"bk_name", "bk_cat"})
})
public class Book extends PersistentRecord {

    private String name;
    private String author;
    private String category;
    private int numPages;
    private String series;

    public Book() {}

    public Book(String name, String author, String category, int numPages, String series) {

        this.name = name;
        this.author = author;
        this.category = category;
        this.numPages = numPages;
        this.series = series;
    }

    public static Book getInstance(String name, String author, String category, int numPages, String series) {

        return new Book(name, author, category, numPages, series);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {

        return "name=" + name +
                ", author=" + author +
                ", category=" + category +
                ", numbPages=" + numPages +
                ", series=" + series;
    }
}
