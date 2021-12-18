package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="FORNECEDOR")
@NoArgsConstructor
@AllArgsConstructor
public class ItemSupplier {
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
}
