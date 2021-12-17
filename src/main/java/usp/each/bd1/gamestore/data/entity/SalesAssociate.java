package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ATENDENTE")
public class SalesAssociate implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;
}
