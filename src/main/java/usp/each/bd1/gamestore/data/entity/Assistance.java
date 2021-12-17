package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ATENDE")
public class Assistance {
    @EmbeddedId
    private AssistanceId id;

    @ManyToOne
    @MapsId("salesAssociateCpf")
    @JoinColumn(name="CPF_ATENDENTE")
    private SalesAssociate salesAssociate;

    @ManyToOne
    @MapsId("customerCpf")
    @JoinColumn(name="CPF_CLIENTE")
    private Customer customer;

    public Assistance() {}

    public Assistance(AssistanceId id, SalesAssociate salesAssociate, Customer customer) {
        this.id = id;
        this.salesAssociate = salesAssociate;
        this.customer = customer;
    }

    public LocalDateTime getTimestamp() {
        return id.getTimestamp();
    }
}
