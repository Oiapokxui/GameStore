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
@Table(name="CLIENTE")
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer implements Serializable {
    @Id
    @Column(name="CPF")
    @Getter @Setter
    private String cpf;

    @Column(name="PONTOS_DE_FIDELIDADE")
    @Getter @Setter
    @NonNull
    private int fidelityPoints;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Person thisPerson;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private List<Sale> purchases;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private List<Assistance> assistances;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private List<RepairItem> repairItems;

    public Customer(final String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return thisPerson.getName();
    }

    public void setName(String name) {
        thisPerson.setName(name);
    }

}
