package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="TECNICO_DE_MANUTENCAO")
public class Technician implements Serializable {
    @Id
    @Column(name="CPF")
    private String cpf;
}
