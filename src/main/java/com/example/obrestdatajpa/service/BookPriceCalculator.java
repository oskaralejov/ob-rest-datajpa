package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;

public class BookPriceCalculator {
    
    public double calculatePrice(Book book){

        double price =book.getPrice();

        if(book.getPages()>300){

            price+=20000;
        }
        //envio
        price+=15000;

        return price;


    }
    
}
