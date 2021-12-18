package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="GERENTE")
public class Manager implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Employee employee;
}
