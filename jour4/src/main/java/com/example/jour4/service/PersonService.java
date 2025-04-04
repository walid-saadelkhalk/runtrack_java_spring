package com.example.jour4.service;

import com.example.jour4.dto.PersonDTO;
import com.example.jour4.model.Person;
import com.example.jour4.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public void save(Person person) {
        repository.save(person);
    }

    public List<PersonDTO> findAllAsDTO() {
        return repository.findAll().stream()
                .map(p -> new PersonDTO(p.getName(), p.getAge()))
                .toList();
    }
}
