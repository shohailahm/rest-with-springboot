package org.mvn.convertor.mocks;

import org.mvn.data.model.Person;
import org.mvn.data.vo.Personvo;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {
    public Person mockentity(){
        return mockentity(0);
    }

    public Personvo mockvo(){
        return  mockvo(0);
    }

    public List<Person> mockEntityList(){
        List<Person> person=new ArrayList<Person>();
        for (int i = 0; i <14 ; i++) {
            person.add(mockentity(i));
        }
        return person;
    }

    public List<Personvo> mockvoList(){
        List<Personvo> person=new ArrayList<Personvo>();
        for (int i = 0; i <14 ; i++) {
            person.add(mockvo(i));
        }
        return person;
    }

    private Person mockentity(Integer i){
        Person person=new Person();
        person.setAddress("Address"+i);
        person.setGender("male"+i);
        person.setName("name"+i);
        person.setLastName("Lname"+i);
        person.setId(i.longValue());
        return person;
    }
    private Personvo mockvo(Integer i){
        Personvo person=new Personvo();
        person.setAddress("Address"+i);
        person.setGender("male"+i);
        person.setName("name"+i);
        person.setLastName("Lname"+i);
        person.setKey(i.longValue());
        return person;
    }

}
