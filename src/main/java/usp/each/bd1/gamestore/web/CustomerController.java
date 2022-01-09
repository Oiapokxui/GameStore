package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Customer;
import usp.each.bd1.gamestore.data.entity.Person;
import usp.each.bd1.gamestore.data.repository.CustomerRepository;
import usp.each.bd1.gamestore.data.repository.PersonRepository;
import usp.each.bd1.gamestore.exception.CustomerNotFound;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Iterable<Customer> getCustommer() {
        return this.customerRepository.findAll();
    }

    @PostMapping("/delete")
    private void deleteCustomer(@RequestParam("cpf") String cpf) throws CustomerNotFound {
        var customer = this.customerRepository.findById(cpf)
                .orElseThrow(() -> new CustomerNotFound("Customer with CPF: "+ cpf + " not found"));
        this.customerRepository.delete(customer);
    }


    @PostMapping("/edit")
    public void updateCustomer(@RequestBody Person updatedCustomerAsPerson) {
        var updatedPerson = updatedCustomerAsPerson;
        var cpf = updatedPerson.getCpf();
        var existingCustomer = customerRepository.findById(cpf).orElse(new Customer(cpf));

        existingCustomer.setThisPerson(updatedPerson);

        this.customerRepository.save(existingCustomer);
    }
}
