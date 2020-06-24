package org.mvn.services;

import org.mvn.convertor.DozerConvertor;
import org.mvn.data.vo.Personvo;
import org.mvn.exceptions.ResourceNotFoundException;
import org.mvn.data.model.Person;
import org.mvn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class PersonService {
   // private final AtomicLong counter =new AtomicLong();

    @Autowired
    PersonRepository repository;

    public Personvo findById(Long id){
        Personvo entity = DozerConvertor.parseObject(repository.findById(id).orElseThrow(()->new  ResourceNotFoundException("id not found")),Personvo.class);
         return entity;
       // return  repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records found for this id"));
    }
    public Page<Personvo> findAll(Pageable pageable){
        //getContect is added to convert page to a list
       Page<Person> page = repository.findAll(pageable);
       // return DozerConvertor.parseListObject(Personvo.class);
        return  page.map(this::convertToPersonvo);


     //return  repository.findAll();
    }
    public Page<Personvo> findByName(String firstname,Pageable pageable){
        //getContect is added to convert page to a list
        Page<Person> page = repository.findUserByName(firstname,pageable);
        // return DozerConvertor.parseListObject(Personvo.class);
        return  page.map(this::convertToPersonvo);


        //return  repository.findAll();
    }


    private Personvo convertToPersonvo(Person person) {

       return DozerConvertor.parseObject(person,Personvo.class);
    }

//    private Personvo mockperson(int i){
//        Personvo person=new Personvo();
//        person.setId(i);
//        person.setName("Suhail"+i);
//        person.setLastName("Ahmed");
//        person.setAddress("sin");
//        person.setGender("Male");
//        return  person;
//    }


    public Personvo create(Personvo person){
        Person entity= DozerConvertor.parseObject(person, Person.class);
        Personvo vo= DozerConvertor.parseObject(repository.save(entity), Personvo.class);
        return vo;
    }

    public Personvo update(Personvo person) {

        Person entity = DozerConvertor.parseObject(repository.findById(person.getKey()).orElseThrow(()->new  ResourceNotFoundException("id not found")),Person.class);

        entity.setId(person.getKey());
        entity.setName(person.getName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return DozerConvertor.parseObject(repository.save(entity), Personvo.class);
    }

    @Transactional
    public Personvo  disable(Long id){
        repository.disablePerson(id);
        Person entity = repository.findById(id).orElseThrow(()->new  ResourceNotFoundException("id not found"));
        return DozerConvertor.parseObject(entity,Personvo.class);
        // return  repository.findById(id).orElseThrow(()->new ResourceNotFoundException("No records found for this id"));
    }
    public ResponseEntity delete(Long id)
    {
        Person entity = DozerConvertor.parseObject(repository.findById(id).orElseThrow(()->new  ResourceNotFoundException("id not found")),Person.class);
        repository.delete(entity);
         return  ResponseEntity.ok().build();
    }
}
