package usp.each.bd1.gamestore.data.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseId implements Serializable {
    @Column(name = "CNPJ")
    @Getter @Setter
    private String supplierCnpj;
    @Column(name = "CPF_GERENTE")
    @Getter @Setter
    private String managerCpf;
    @Column(name = "CODIGO_DE_BARRAS")
    @Getter @Setter
    private String itemBarcode;

    @Override public boolean equals(final Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        final PurchaseId that = (PurchaseId)o;
        return supplierCnpj.equals(that.supplierCnpj) && managerCpf.equals(that.managerCpf) && itemBarcode.equals(that.itemBarcode);
    }

    @Override public int hashCode() {
        return Objects.hash(supplierCnpj, managerCpf, itemBarcode);
    }
}
