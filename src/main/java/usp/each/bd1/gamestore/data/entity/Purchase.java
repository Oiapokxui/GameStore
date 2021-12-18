package usp.each.bd1.gamestore.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AQUISICAO")
public class Purchase {

    @EmbeddedId
    private PurchaseId id;

    @Column(name="VALOR_A_PAGAR")
    @Getter @Setter
    private double totalAmount;

    @ManyToOne
    @MapsId("managerCpf")
    @JoinColumn(name="CPF_GERENTE")
    @Getter @Setter
    private Manager manager;

    @ManyToOne
    @MapsId("supplierCnpj")
    @JoinColumn(name="CNPJ")
    @Getter @Setter
    private ItemSupplier itemSupplier;

    @OneToOne
    @MapsId("itemBarcode")
    @JoinColumn(name = "CODIGO_DE_BARRAS")
    @Getter @Setter
    private Item item;
}
