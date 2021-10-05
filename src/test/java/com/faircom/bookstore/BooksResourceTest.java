package com.faircom.bookstore;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BooksResourceTest {

    @Test
    public void testBooksEndpoint() {

        given()
          .when().get("/books?name=ON THE STEEL BREEZE")
          .then().statusCode(200);
    }

}