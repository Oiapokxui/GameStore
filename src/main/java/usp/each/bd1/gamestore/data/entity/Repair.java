package usp.each.bd1.gamestore.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CONSERTA")
public class Repair {
    @EmbeddedId
    @Getter @Setter
    private RepairId id;

    @ManyToOne
    @MapsId("technicianCpf")
    @JoinColumn(name="CPF_TECNICO")
    @Getter @Setter
    private Technician technician;

    @ManyToOne
    @MapsId("repairItemId")
    @JoinColumn(name="ID_ITEM_REPARO")
    @Getter @Setter
    private RepairItem repairItem;

    @Column(name="VALOR_DO_CONSERTO", nullable = false)
    @Getter @Setter
    private double totalAmount;

    @Column(name="DESCONTO_POR_PONTOS")
    @Getter @Setter
    private double discountUsingPoints;

    @Column(name="TIMESTAMP_DO_PEDIDO")
    @Getter @Setter
    private LocalDateTime timestamp = LocalDateTime.now();
}
