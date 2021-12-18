package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TELEFONE_FORNECEDOR")
public class ItemSupplierTelephone {
    @Id
    @Column(name="TELEFONE")
    @Getter @Setter
    private String telephoneNumber;

    @ManyToOne
    @JoinColumn(name = "CNPJ")
    @Getter @Setter
    private ItemSupplier owner;
}