package usp.each.bd1.gamestore.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="FORNECEDOR")
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @Column(name="CNPJ")
    @Getter @Setter
    private String cnpj;

    @Column(name="NOME", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name="ENDERECO_EMAIL")
    @Getter @Setter
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    List<SupplierTelephone> telephones;
}
