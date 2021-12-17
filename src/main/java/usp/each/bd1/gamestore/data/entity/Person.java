package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PESSOA")
public class Person implements Serializable  {
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

}
