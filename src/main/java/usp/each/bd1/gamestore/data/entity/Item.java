package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUTO")
public class Item {
    //TODO: Relate every product with its storage
    @Id
    @Column(name = "CODIGO_DE_BARRAS")
    private String barcode;

    @Column(name = "NOME_ESTOQUE")
    private String storageName;

    @Column(name = "NOME")
    private String name;

    @Column(name = "PRECO")
    private double price;

    @ManyToOne
    @JoinColumn(name = "ID_COMPRA")
    private Purchase purchase;
}
