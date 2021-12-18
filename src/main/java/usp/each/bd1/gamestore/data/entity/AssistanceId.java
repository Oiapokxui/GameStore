package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AssistanceId implements Serializable {
    @Column(name = "CPF_ATENDENTE")
    @Getter @Setter
    private String salesAssociateCpf;
    @Column(name = "CPF_CLIENTE")
    @Getter @Setter
    private String customerCpf;
    @Column(name = "HORARIO_ATENDIMENTO")
    @Getter @Setter
    private LocalDateTime timestamp = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssistanceId assistanceId = (AssistanceId) o;
        return salesAssociateCpf.equals(assistanceId.salesAssociateCpf) &&
                customerCpf.equals(assistanceId.customerCpf) &&
                timestamp.equals(assistanceId.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesAssociateCpf, customerCpf, timestamp);
    }
}
