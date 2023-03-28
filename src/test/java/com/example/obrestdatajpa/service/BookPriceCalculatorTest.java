package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePrice() {

        //configuracion del test o prueba
        Book book=new Book(1L,
                "el señor de las rosquillas",
                "El veli",
                150,
                26700d,
                LocalDate.now(),
                true);
        BookPriceCalculator calculator=new BookPriceCalculator();

        //se ejecuta el comportamiento a testear
        double price= calculator.calculatePrice(book);
        System.out.println(price);

        //comprobaciones de que tdo esta bien (comprobacion de asercion)
        assertTrue(price>0);
        assertEquals(41700.0,price);



    }

}