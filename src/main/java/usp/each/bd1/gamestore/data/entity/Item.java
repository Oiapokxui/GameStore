package usp.each.bd1.gamestore.data.entity;

import java.util.Optional;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUTO")
public class Item {
    @Id
    @Column(name = "CODIGO_DE_BARRAS")
    @Getter @Setter
    private String barcode;

    @Column(name = "NOME")
    @Getter @Setter
    private String name;

    @Column(name = "PRECO")
    @Getter @Setter
    private double price;

    @ManyToOne
    @JoinColumn(name = "ID_VENDA")
    @Getter @Setter
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "NOME_ESTOQUE")
    @Setter
    private Storage storage;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private VideoGame thisAsVideoGame;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private VideoGameConsole thisAsVideoGameConsole;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Accessory thisAsAccessory;

    public String getItemType() {
        if (thisAsAccessory != null) return "accessory";
        else if(thisAsVideoGame != null) return "videoGame";
        else if(thisAsVideoGameConsole != null) return "console";
        else return "unassigned";
    }

    public String getSaleIdAsString(){
        return Optional.ofNullable(getSale())
                .flatMap(sale -> Optional.of(sale.getIdAsString()))
                .orElse("");
    }
}
