package org.mvn.controllers;

import  static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import  static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mvn.data.model.Person;
import org.mvn.data.vo.Personvo;
import org.mvn.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "PersonEndpoint" )
@CrossOrigin
@RestController
@RequestMapping("api/person/v1")
public class PersonController {
    @Autowired
    private PersonService service;

    @ApiOperation("get list of all persons ")
    @GetMapping(produces = {"application/json","application/xml"})
    public ResponseEntity<PagedResources<Personvo>> findAll (@RequestParam(value = "page",defaultValue = "0")int page , @RequestParam(value = "limit",defaultValue = "12") int limit, @RequestParam(value = "direction",defaultValue = "asc") String direction,
                                                             PagedResourcesAssembler assembler){

        Direction sortDirection="desc".equalsIgnoreCase(direction)? Direction.DESC: Direction.ASC;

        Pageable pageable= PageRequest.of(page,limit, Sort.by(sortDirection,"name"));


        Page<Personvo> personvos= service.findAll(pageable);
         personvos.stream().forEach(p->p.add(linkTo(methodOn(PersonController.class).findByid(p.getKey())).withSelfRel()));
         return new ResponseEntity<>(assembler.toResource(personvos), HttpStatus.OK);
    }

    @GetMapping(value = "findByName/{firstname}",produces = {"application/json","application/xml"})
    public ResponseEntity<PagedResources<Personvo>> findAllByParam (@RequestParam(value = "page",defaultValue = "0")int page , @RequestParam(value = "limit",defaultValue = "12") int limit, @RequestParam(value = "direction",defaultValue = "asc") String direction,
                                                             PagedResourcesAssembler assembler,
                                                             @PathVariable("firstname") String firstname){

        Direction sortDirection="desc".equalsIgnoreCase(direction)? Direction.DESC: Direction.ASC;

        Pageable pageable= PageRequest.of(page,limit, Sort.by(sortDirection,"name"));

            Page<Personvo> personvos= service.findByName(firstname,pageable);
            personvos.stream().forEach(p->p.add(linkTo(methodOn(PersonController.class).findByid(p.getKey())).withSelfRel()));
            return new ResponseEntity<>(assembler.toResource(personvos), HttpStatus.OK);

    }



    @PostMapping
    public Personvo create (@RequestBody Personvo person){

        Personvo personvo= service.update(person);
        personvo.add(linkTo(methodOn(PersonController.class).findByid(personvo.getKey())).withSelfRel());
        return personvo;
    }

    @PutMapping
    public Personvo update (@RequestBody Personvo person){


        Personvo personvo= service.update(person);
        personvo.add(linkTo(methodOn(PersonController.class).findByid(personvo.getKey())).withSelfRel());
        return personvo;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") Long id){
         service.delete(id);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Personvo findByid (@PathVariable("id") Long id)
    {
    Personvo personvo= service.findById(id);
    personvo.add(linkTo(methodOn(PersonController.class).findByid(id)).withSelfRel());
     return personvo;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PATCH,produces = MediaType.APPLICATION_JSON_VALUE)
    public Personvo disablePerson (@PathVariable("id") Long id)
    {
        Personvo personvo= service.disable(id);
        personvo.add(linkTo(methodOn(PersonController.class).disablePerson(id)).withSelfRel());
        return personvo;
    }



}
