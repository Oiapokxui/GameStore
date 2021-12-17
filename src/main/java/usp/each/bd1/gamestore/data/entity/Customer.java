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

    @OneToMany(mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private List<Purchase> purchases;
}
