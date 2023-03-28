package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

class BookControllerTest {

    private TestRestTemplate testRestTemplate; //

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder= restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate=new TestRestTemplate(restTemplateBuilder);
    }


    @Test
    void hello() {

        ResponseEntity<String> response=testRestTemplate.getForEntity("/hola", String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Holaaa mundo que tal vamos!!",response.getBody());
    }

    @Test
    void findALl() {
        ResponseEntity<Book[]> response=testRestTemplate.getForEntity("/api/books", Book[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        //assertEquals(200,response.getStatusCodeValue());

        List<Book> books= Arrays.asList(response.getBody());
        System.out.println(books.size());


    }

    @Test
    void findOneById() {

        ResponseEntity<Book> response=testRestTemplate.getForEntity("/api/books/1", Book.class);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json= """
                {
                    "title": "Libro creado desde sprint test",
                    "autor": "Oscar velilla",
                    "pages": 450,
                    "price": 29.9,
                    "releaseDate": "2023-01-12",
                    "online": true
                }
                """;
        HttpEntity<String> request= new HttpEntity<>(json,headers);

        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books",HttpMethod.POST,request, Book.class);

        Book result = response.getBody();

        assertEquals(1L,result.getId());
        assertEquals("Libro creado desde sprint test",result.getTitle());
    }
}