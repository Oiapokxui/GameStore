package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TECNICO_DE_MANUTENCAO")
public class Technician implements Serializable {
    @Id
    @Column(name="CPF")
    @Getter @Setter
    private String cpf;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Employee thisEmployee;
}
