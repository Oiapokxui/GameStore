package usp.each.bd1.gamestore.web;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usp.each.bd1.gamestore.data.entity.Accessory;
import usp.each.bd1.gamestore.data.entity.Customer;
import usp.each.bd1.gamestore.data.entity.Person;
import usp.each.bd1.gamestore.data.entity.RepairItem;
import usp.each.bd1.gamestore.data.entity.Sale;
import usp.each.bd1.gamestore.data.repository.AccessoryRepository;
import usp.each.bd1.gamestore.data.repository.AssistanceRepository;
import usp.each.bd1.gamestore.data.repository.CustomerRepository;
import usp.each.bd1.gamestore.data.repository.ItemRepository;
import usp.each.bd1.gamestore.data.repository.PersonRepository;
import usp.each.bd1.gamestore.data.repository.PurchaseRepository;
import usp.each.bd1.gamestore.data.repository.RepairItemRepository;
import usp.each.bd1.gamestore.data.repository.RepairRepository;
import usp.each.bd1.gamestore.data.repository.SaleRepository;
import usp.each.bd1.gamestore.data.repository.VideoGameConsoleRepository;
import usp.each.bd1.gamestore.data.repository.VideoGameRepository;
import usp.each.bd1.gamestore.exception.CustomerNotFound;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AssistanceRepository assistanceRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private RepairItemRepository repairItemRepository;
    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private VideoGameConsoleRepository videoGameConsoleRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping
    public Customer getCustomer(@RequestParam("cpf") String cpf) {
        return this.customerRepository.findById(cpf).orElse(null);
    }

    @PostMapping("/delete")
    private void deleteCustomer(@RequestParam("cpf") String cpf) throws CustomerNotFound {
        var customer = this.customerRepository.findById(cpf)
                .orElseThrow(() -> new CustomerNotFound("Customer with CPF: "+ cpf + " not found"));
        this.assistanceRepository.deleteAssistancesByCustomer(cpf);
        var salesId = this.saleRepository.findByCustomer(cpf).stream().map(Sale::getId).toList();
        var barcodes = this.itemRepository.findBySales(salesId);
        this.accessoryRepository.deleteIfInList(barcodes);
        this.videoGameRepository.deleteIfInList(barcodes);
        this.videoGameConsoleRepository.deleteIfInList(barcodes);
        this.purchaseRepository.deleteIfInList(barcodes);
        this.itemRepository.deleteIfInList(salesId);
        this.saleRepository.deleteByCustomer(cpf);
        var repairItemsId = repairItemRepository.selectByCustomer(cpf).stream().map(RepairItem::getId).toList();
        this.repairRepository.deleteRepairsByRepairItem(repairItemsId);
        this.repairItemRepository.deleteWhereCustomer(cpf);
        this.customerRepository.delete(customer);
    }


    @PostMapping("/edit")
    public void updateCustomer(@RequestBody Person updatedCustomerAsPerson) {
        var updatedPerson = updatedCustomerAsPerson;
        var cpf = updatedPerson.getCpf();
        var name = updatedPerson.getName();
        var existingCustomer = customerRepository.findById(cpf).orElse(new Customer(cpf));

        existingCustomer.setName(updatedPerson.getName());

        this.customerRepository.save(existingCustomer);
    }

    @PostMapping("/create")
    public void createCustomer(@RequestBody Person updatedCustomerAsPerson) {
        var updatedPerson = updatedCustomerAsPerson;
        var cpf = updatedPerson.getCpf();
        var existingCustomer = new Customer(cpf);

        existingCustomer.setThisPerson(updatedPerson);

        this.customerRepository.save(existingCustomer);
    }

}
