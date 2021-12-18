package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ACESSORIOS")
public class Accessory {
    @Id
    @Column(name = "CODIGO_DE_BARRAS")
    @Getter @Setter
    private String barcode;

    @Column(name = "CATEGORIA")
    @Getter @Setter
    private String developer;

    @Column(name = "GARANTIA")
    @Getter @Setter
    private String franchise;

    @Column(name = "FABRICANTE")
    @Getter @Setter
    private String publisher;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CODIGO_DE_BARRAS")
    private Item item;
}
