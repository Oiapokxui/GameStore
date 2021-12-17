package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="CAIXA")
public class Cashier implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @OneToMany(mappedBy = "cashier")
    @PrimaryKeyJoinColumn
    private List<Purchase> purchases;
}
