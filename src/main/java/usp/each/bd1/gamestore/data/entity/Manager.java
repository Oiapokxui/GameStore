package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="GERENTE")
public class Manager implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Employee thisEmployee;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "manager")
    @PrimaryKeyJoinColumn
    private List<Employee> employeesManaged;
}
