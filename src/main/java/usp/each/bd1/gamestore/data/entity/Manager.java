package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="GERENTE")
@NoArgsConstructor
@RequiredArgsConstructor
public class Manager implements Serializable {
    @Id
    @Column(name="CPF")
    @NonNull
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

    public Manager(@NonNull final String cpf, final Employee thisEmployee) {
        this.cpf = cpf;
        this.thisEmployee = thisEmployee;
    }
}
