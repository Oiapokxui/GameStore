package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name="TELEFONE_PESSOA")
public class PersonTelephone implements Serializable {
    @Id
    @Column(name="TELEFONE")
    private String telephoneNumber;

    @Id
    @Column(name="CPF")
    private String ownersCpf;
}