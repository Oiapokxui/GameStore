package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ATENDE")
@NoArgsConstructor
@AllArgsConstructor
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

    public LocalDateTime getTimestamp() {
        return id.getTimestamp();
    }
}
