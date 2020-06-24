package org.mvn.services;

import org.mvn.convertor.DozerConvertor;
import org.mvn.data.model.Books;
import org.mvn.data.vo.Booksvo;
import org.mvn.exceptions.ResourceNotFoundException;
import org.mvn.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BooksRepository repository;

    public List<Booksvo> findAll() {
        List<Booksvo> entity= DozerConvertor.parseListObject(repository.findAll(),Booksvo.class);

        return entity;

    }

    public Booksvo findById(int id) {

        Booksvo entity= DozerConvertor.parseObject(repository.findById(id).orElseThrow(()->new ResourceNotFoundException("given id doesnt exits")),Booksvo.class);
        return entity;

    }

    public Booksvo create(Booksvo book) {
        Books entity=DozerConvertor.parseObject(book,Books.class);
        Booksvo vo=DozerConvertor.parseObject(repository.save(entity),Booksvo.class);
        return vo;
    }

    public Booksvo update(Booksvo book) {
        Books entity=DozerConvertor.parseObject(repository.findById(book.getKey()).orElseThrow(()->new ResourceNotFoundException("Id not available")),Books.class);
        entity.setAuthor(book.getAuthor());
        entity.setLaunch_date(book.getLaunch_date());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        entity.setId(book.getKey());
       Booksvo vo=DozerConvertor.parseObject(repository.save(entity),Booksvo.class);
       return vo;

    }


    public ResponseEntity delete(int id) {
        Books entity=DozerConvertor.parseObject(repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id not available")),Books.class);
         repository.delete(entity);
         return ResponseEntity.ok().build();


    }
}
