package org.mvn.controllers;

import  static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import  static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import io.swagger.annotations.Api;
import org.mvn.data.model.Books;
import org.mvn.data.vo.Booksvo;
import org.mvn.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@CrossOrigin
@RestController
@RequestMapping("api/books/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(produces = {"application/json","application/xml"})
    public List<Booksvo> findAll()
    {
       List<Booksvo> volist=service.findAll();
        volist.stream().forEach(p->p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return volist;
    }

    @GetMapping(value = "/{id}",produces = {"application/json","application/xml"},consumes = {"application/json","application/xml"})
    public Booksvo findById(@PathVariable("id") int id){
        Booksvo vo=service.findById(id);
         vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
         return vo;

    }

    @PostMapping
    public Booksvo create(@RequestBody Booksvo book){
        Booksvo booksvo= service.create(book);
        booksvo.add(linkTo(methodOn(BookController.class).findById(booksvo.getKey())).withSelfRel());
        return booksvo;
    }

    @PutMapping
    public Booksvo update(@RequestBody Booksvo book){

        Booksvo booksvo= service.update(book);
        booksvo.add(linkTo(methodOn(BookController.class).findById(booksvo.getKey())).withSelfRel());
        return booksvo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        return service.delete(id);
    }

}
