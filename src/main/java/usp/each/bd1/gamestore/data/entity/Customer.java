package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CLIENTE")
public class Customer implements Serializable {
    @Id
    @Column(name="CPF")
    @Getter @Setter
    private String cpf;

    @Column(name="PONTOS_DE_FIDELIDADE")
    @Getter @Setter
    private int fidelityPoints;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Person thisPerson;

    @OneToMany(mappedBy = "buyer")
    @PrimaryKeyJoinColumn
    private List<Sale> purchases;
}
