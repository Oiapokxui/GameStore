package usp.each.bd1.gamestore.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public void create(@RequestBody String body) {
        final String cpf = body.split(",")[0];
        final String name = body.split(",")[1];
        personRepository.save(new Person(cpf, name));
    }

    @RequestMapping("/del")
    @PostMapping
    public void delete(@RequestBody String id) {
        this.personRepository.deleteById(id);
    }
}
