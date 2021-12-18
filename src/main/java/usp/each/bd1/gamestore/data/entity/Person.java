package usp.each.bd1.gamestore.data.entity;

import java.util.List;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "thisPerson", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Employee personAsEmployee;

    @OneToOne(mappedBy = "thisPerson", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private Customer personAsCustomer;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @Getter @Setter
    List<PersonTelephone> telephones;

}
