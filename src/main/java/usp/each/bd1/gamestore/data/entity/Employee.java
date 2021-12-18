package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="FUNCIONARIO")
public class Employee {

    @ManyToOne
    @JoinColumn(name="CPF_GERENTE")
    @Getter @Setter
    private Manager manager;

    @Id
    @Column(name="CPF")
    @Getter @Setter
    private String cpf;

    @Column(name="RG")
    @Getter @Setter
    private String rg;

    @Column(name="SALARIO")
    @Getter @Setter
    private Long salary;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Person person;

}
