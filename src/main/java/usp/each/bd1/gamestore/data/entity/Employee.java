package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="FUNCIONARIO")
public class Employee {

    @Column(name="CPF_GERENTE")
    private String managersCpf;

    @Id
    @Column(name="CPF")
    private String cpf;

    @Column(name="SALARIO")
    private Long salary;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    private Person person;

    public String getManagersCpf() {
        return managersCpf;
    }

    public void setManagersCpf(String managersCpf) {
        this.managersCpf = managersCpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
