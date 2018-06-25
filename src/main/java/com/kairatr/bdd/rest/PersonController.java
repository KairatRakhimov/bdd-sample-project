package com.kairatr.bdd.rest;

import com.kairatr.bdd.entity.Person;
import com.kairatr.bdd.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable(value = "id") Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePerson(@PathVariable(value = "id") Long id, @RequestBody Person person) {
        Person savedPerson = personRepository.getOne(id);
        savedPerson.setFirstName(person.getFirstName());
        savedPerson.setLastName(person.getLastName());
        personRepository.save(savedPerson);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
