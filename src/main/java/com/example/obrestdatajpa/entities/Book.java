package com.example.obrestdatajpa.entities;

//import jakarta.persistence.*;
import javax.persistence.*;

import java.time.LocalDate;


@Entity  //relacionar los atributos con la base de datos
@Table(name = "Books") //cambiar nombre de la base de datos

public class Book {

    //atributos
    @Id //clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String autor;
    private Integer pages;
    private Double price;
    private LocalDate releaseDate;
    private Boolean online;


    //construsctores
    public Book() {
    }

    public Book(Long id, String title, String autor, Integer pages, Double price, LocalDate releaseDate, Boolean online) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.online = online;
    }

    //getter y setter

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getPages() {
        return pages;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
    //tostring


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", autor='" + autor + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", online=" + online +
                '}';
    }
}
