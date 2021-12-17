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

}
