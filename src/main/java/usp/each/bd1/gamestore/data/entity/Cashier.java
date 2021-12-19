package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CAIXA")
public class Cashier implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @OneToOne(cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Employee thisEmployee;

    @OneToMany(mappedBy = "cashier")
    @PrimaryKeyJoinColumn
    private List<Sale> sales;
}
