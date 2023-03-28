package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication

public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD

		//crear un libro
		
		Book book1 = new Book(null,"La odisea","Oscar velilla",450,29.900, LocalDate.of(2023,1,12),true);
		Book book2 = new Book(null,"Wataplus","Oscar velilla",200,79.900, LocalDate.of(2023,1,11),true);


		//almacenar un libro

		System.out.println("numero de libros en Base de datos: "+repository.findAll().size());

		repository.save(book1);
		repository.save(book2);

		//recuperar todos los libros


		System.out.println("numero de libros en Base de datos: "+repository.findAll().size());

		//borrar todos los libros

		//repository.deleteById(1L);
		System.out.println("numero de libros en Base de datos: "+repository.findAll().size());


	}

}
