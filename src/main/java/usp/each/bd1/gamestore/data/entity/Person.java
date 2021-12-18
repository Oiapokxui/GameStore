package usp.each.bd1.gamestore.data.entity;

import static org.hibernate.annotations.CascadeType.DELETE;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PESSOA")
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    //TODO: Set cascade delete
    @Id
    @Column(name="CPF")
    @Getter @Setter
    private String cpf;

    @Column(name="NOME")
    @Getter @Setter
    private String name;

    @OneToOne(mappedBy = "person", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Employee personAsEmployee;

    @OneToOne(mappedBy = "person", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Customer personAsCustomer;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    List<PersonTelephone> telephones;

}
