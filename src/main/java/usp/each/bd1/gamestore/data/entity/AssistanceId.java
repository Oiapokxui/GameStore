package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AssistanceId implements Serializable {
    @Column(name = "CPF_ATENDENTE")
    private String salesAssociateCpf;
    @Column(name = "CPF_CLIENTE")
    private String customerCpf;
    @Column(name = "HORARIO_ATENDIMENTO")
    private LocalDateTime timestamp = LocalDateTime.now();

    public AssistanceId() {}

    public AssistanceId(String clientCpf, String customerCpf, LocalDateTime timestamp) {
        this.salesAssociateCpf = clientCpf;
        this.customerCpf = customerCpf;
        this.timestamp = timestamp;
    }

    public String getSalesAssociateCpf() {
        return salesAssociateCpf;
    }

    public void setSalesAssociateCpf(String salesAssociateCpf) {
        this.salesAssociateCpf = salesAssociateCpf;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

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
