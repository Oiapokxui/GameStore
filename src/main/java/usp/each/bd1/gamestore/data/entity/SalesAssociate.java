package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ATENDENTE")
public class SalesAssociate implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Employee thisEmployee;

    @OneToMany(mappedBy = "salesAssociate")
    @PrimaryKeyJoinColumn
    private List<Assistance> assistances;
}
