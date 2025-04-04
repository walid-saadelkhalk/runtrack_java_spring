package com.example.jour4.controller;

import com.example.jour4.model.Person;
import com.example.jour4.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private final PersonRepository personRepository;

    public PersonRestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // ✅ GET /api/persons (accessible à tous les utilisateurs connectés)
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // ✅ POST /api/persons (accessible uniquement aux ADMIN)
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person saved = personRepository.save(person);
        return ResponseEntity.ok(saved);
    }
}
