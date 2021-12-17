package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="CLIENTE")
public class Customer implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @Column(name="PONTOS_DE_FIDELIDADE")
    private int fidelityPoints;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    private Person person;

    @OneToMany(mappedBy = "customer")
    private List<Assistance> assistances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return fidelityPoints == customer.fidelityPoints && cpf.equals(customer.cpf) && person.equals(customer.person) && Objects.equals(assistances, customer.assistances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, fidelityPoints, person, assistances);
    }
}
