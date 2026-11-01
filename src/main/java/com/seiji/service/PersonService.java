package com.seiji.service;

import com.seiji.model.Person;

public class PersonService implements IPersonService {

    @Override
    public Person createPerson(Person person) {
        
        if (person.getCompany() == null ||  person.getCompany().isBlank())
            throw new IllegalArgumentException("The Person email is null or empty");
        
        return person;
    }
    
}
