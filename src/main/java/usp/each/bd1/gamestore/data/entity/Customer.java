package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CLIENTE")
public class Customer implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @Id
    @Column(name="ID_CLIENTE")
    private long clientId;

    @Column(name="PONTOS_DE_FIDELIDADE")
    private int fidelityPoints;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    private Person person;
}
