package usp.each.bd1.gamestore.data.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Estoque")
@NoArgsConstructor
public class Storage {
    @Id
    @Column(name = "NOME")
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "storage")
    @PrimaryKeyJoinColumn
    private List<Item> items;

    public Storage(final String name) {
        this.name = name;
    }
}
