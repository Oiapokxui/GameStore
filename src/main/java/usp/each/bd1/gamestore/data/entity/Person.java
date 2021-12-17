package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;

@Entity
@Table(name="PESSOA")
public class Person {
    //TODO: Set cascade delete
    @Id
    @Column(name="CPF")
    private String cpf;

    @Column(name="NOME")
    private String name;

    @OneToOne(mappedBy="person")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(mappedBy="person")
    @PrimaryKeyJoinColumn
    private Customer customer;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
