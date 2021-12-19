package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="FUNCIONARIO")
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    @Id
    @Column(name="CPF")
    @Getter @Setter
    @NonNull
    private String cpf;

    @Column(name="RG")
    @Getter @Setter
    @NonNull
    private String rg;

    @Column(name="SALARIO")
    @Getter @Setter
    @NonNull
    private Double salary;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    @NonNull
    private Person thisPerson;

    @ManyToOne
    @JoinColumn(name="CPF_GERENTE")
    @Getter @Setter
    private Manager manager;

    @OneToOne(mappedBy = "thisEmployee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Manager thisAsManager;

    @OneToOne(mappedBy = "thisEmployee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SalesAssociate thisAsSalesAssociate;

    @OneToOne(mappedBy = "thisEmployee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Technician thisAsTechnician;

    @OneToOne(mappedBy = "thisEmployee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Cashier thisAsCashier;

    public Employee(@NonNull final String cpf, final String name, @NonNull final String rg, @NonNull final Double salary) {
        this.cpf = cpf;
        this.rg = rg;
        this.salary = salary;
        this.thisPerson = new Person(cpf, name);
    }

    public Employee(@NonNull final String cpf, @NonNull final String rg, @NonNull final Double salary, @NonNull final Person thisPerson, final Manager manager) {
        this.cpf = cpf;
        this.rg = rg;
        this.salary = salary;
        this.thisPerson = thisPerson;
        this.manager = manager;
    }
}
