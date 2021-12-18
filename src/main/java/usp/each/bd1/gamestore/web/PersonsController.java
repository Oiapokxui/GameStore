package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Person;
import usp.each.bd1.gamestore.data.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonsController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public Iterable<Person> getPersons() {
        var items = this.personRepository.findAll();
        return items;
    }
}
