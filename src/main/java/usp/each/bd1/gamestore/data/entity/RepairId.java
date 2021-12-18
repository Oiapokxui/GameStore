package usp.each.bd1.gamestore.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class RepairId implements Serializable {

    @Column(name = "CPF_TECNICO")
    @Getter @Setter
    private String technicianCpf;

    @Column(name = "ID_ITEM_REPARO")
    @Getter @Setter
    private String repairItemId;

    @Override public boolean equals(final Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        final RepairId repairId = (RepairId)o;
        return technicianCpf.equals(repairId.technicianCpf) && repairItemId.equals(repairId.repairItemId);
    }

    @Override public int hashCode() {
        return Objects.hash(technicianCpf, repairItemId);
    }
}
