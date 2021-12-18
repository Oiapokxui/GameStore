package usp.each.bd1.gamestore.data.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TELEFONE_PESSOA")
public class PersonTelephone {
    @Id
    @Column(name="TELEFONE")
    @Getter @Setter
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "CPF")
    @Getter @Setter
    private Person owner;
}