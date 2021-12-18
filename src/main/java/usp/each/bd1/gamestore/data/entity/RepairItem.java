package usp.each.bd1.gamestore.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITEM_A_REPARAR")
public class RepairItem {
    @Id
    @Column(name = "ID_ITEM_REPARO")
    @Getter @Setter
    private int id;

    @ManyToOne
    @JoinColumn(name = "CPF_DONO")
    @Getter @Setter
    private Customer owner;

    @Column(name = "DESCRICAO")
    @Getter @Setter
    private String description;
}
