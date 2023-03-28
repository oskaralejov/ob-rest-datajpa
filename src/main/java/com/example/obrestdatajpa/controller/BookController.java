package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController  //controlador de tipo Rest

public class BookController {

    //atributos

    private final Logger log= LoggerFactory.getLogger(BookController.class);

    private BookRepository bookRepository;

    //constructor

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD sobre la entidad book
    //buscar todos los libros

    /**
     *http://localhost:8081/api/books
     * @return
     */

    @GetMapping("/api/books")

    public List<Book> findALl(){
        //recuperar y devolver libro de base de datos

        return bookRepository.findAll();

    }

    //buscar libro en base datos segun su id
    /**
     *http://localhost:8081/
     * @return
     */
    @GetMapping("/api/books/{id}")
    @ApiOperation("buscar un  libro por clave primaria id Long")
    public ResponseEntity<Book> findOneById(@ApiParam("clave primaria tipo Long") @PathVariable Long id){

       Optional <Book> bookOpt=bookRepository.findById(id);

       //opcion 1
        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }

        //opcion 2
        // return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    //crear un nuevo libro en base datos

    @PostMapping("/api/books")

    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));

        //Guardar libro recibido por parametro en la base de datos
        if(book.getId() != null){ //quiere decir que existe el id y por lo tanto no es una creacion

            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();

        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }



    //actualizar libro existente en base datos

    @PutMapping("/api/books")

    public ResponseEntity<Book> update (@RequestBody Book book){

        if(book.getId()==null){ //si no tiene id quiere decir que si es una creacion

            log.warn("tryikng to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){

            log.warn("tryikng to update a non existent book");
            return ResponseEntity.notFound().build();

        }

        //el proceso de actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    //borrar un libro en base de datos
    @ApiIgnore
    @DeleteMapping("api/books/{id}")

    public ResponseEntity<Book> delete(@PathVariable Long id){

        if(!bookRepository.existsById(id)){

            log.warn("tryikng to update a non existent book");
            return ResponseEntity.notFound().build();

        }

        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }
    @ApiIgnore
    @DeleteMapping ("/api/books")
    public ResponseEntity<Book> deleteAll(){

        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }


}
