package usp.each.bd1.gamestore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usp.each.bd1.gamestore.data.entity.Employee;
import usp.each.bd1.gamestore.data.entity.Manager;
import usp.each.bd1.gamestore.data.entity.Person;
import usp.each.bd1.gamestore.data.repository.AssistanceRepository;
import usp.each.bd1.gamestore.data.repository.CashierRepository;
import usp.each.bd1.gamestore.data.repository.EmployeeRepository;
import usp.each.bd1.gamestore.data.repository.ManagerRepository;
import usp.each.bd1.gamestore.data.repository.PurchaseRepository;
import usp.each.bd1.gamestore.data.repository.RepairRepository;
import usp.each.bd1.gamestore.data.repository.SalesAssociateRepository;
import usp.each.bd1.gamestore.data.repository.SaleRepository;
import usp.each.bd1.gamestore.data.repository.TechnicianRepository;

@RestController
@RequestMapping("/employee")
public class EmployeesController {
    static class UpdateEmployeePayload {
        Employee employee;
        String managersCpf;
        String type;
        String name;

        public UpdateEmployeePayload(final Employee employee, final String managersCpf, final String type, final String name) {
            this.employee = employee;
            this.managersCpf = managersCpf;
            this.type = type;
            this.name = name;
        }
    }

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private SalesAssociateRepository salesAssociateRepository;

    @Autowired
    private CashierRepository cashierRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping
    public Iterable<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    private void unassignManager(String cpf) {
        this.employeeRepository.unassignManager(cpf);
        this.purchaseRepository.deleteByManager(cpf);
        this.managerRepository.deleteById(cpf);
    }

    private void unassignCashier(String cpf) {
        this.saleRepository.unassignCashier(cpf);
        this.cashierRepository.deleteById(cpf);
    }

    private void unassignSalesAssociate(String cpf) {
        this.assistanceRepository.deleteAssistances(cpf);
        this.salesAssociateRepository.deleteById(cpf);
    }

    private void unassignTechnician(String cpf) {
        this.repairRepository.deleteRepairs(cpf);
        this.technicianRepository.deleteById(cpf);
    }
    private void unassignEmployee(String employeeCpf, String oldType) {
        switch(oldType) {
            case "manager" -> unassignManager(employeeCpf);
            case "salesAssociate" -> unassignSalesAssociate(employeeCpf);
            case "technician" -> unassignTechnician(employeeCpf);
            case "cashier" -> unassignCashier(employeeCpf);
        }
    }

    private void updateEmployeeType(String employeeCpf, String oldType, String newType) {
        unassignEmployee(employeeCpf, oldType);
        switch(newType) {
            case "manager" -> this.managerRepository.insert(employeeCpf);
            case "salesAssociate" -> this.salesAssociateRepository.insert(employeeCpf);
            case "technician" -> this.technicianRepository.insert(employeeCpf);
            case "cashier" -> this.cashierRepository.insert(employeeCpf);
        }
    }

    @PostMapping("/delete")
    private void deleteEmployee(@RequestParam("cpf") String cpf, @RequestParam("type") String type){
        if (!type.equals("unassigned")) unassignEmployee(cpf, type);
        this.employeeRepository.deleteById(cpf);
    }


    @PostMapping("/edit")
    public void updateEmployee(@RequestBody UpdateEmployeePayload payload) {
        var updatedEmployee = payload.employee;
        var newEmployeeName = payload.name;
        var newEmployeeType = payload.type;
        var managersCpf = payload.managersCpf;

        updatedEmployee.setThisPerson(new Person(updatedEmployee.getCpf(), newEmployeeName));
        var existingEmployee = this.employeeRepository.findById(updatedEmployee.getCpf()).orElse(payload.employee);
        var manager = this.managerRepository.findByCpf(managersCpf).orElse(null);

        existingEmployee.getThisPerson().setName(payload.employee.getThisPerson().getName());
        existingEmployee.setManager(manager);
        existingEmployee.setRg(updatedEmployee.getRg());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        this.employeeRepository.save(existingEmployee);

        boolean typeHasChanged = !existingEmployee.getEmployeeType().equals(newEmployeeType);

        if (typeHasChanged)
            updateEmployeeType(existingEmployee.getCpf(), existingEmployee.getEmployeeType(), newEmployeeType);
    }
}
