package usp.each.bd1.gamestore.data.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="PESSOA")
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {
    @Id
    @Column(name="CPF")
    @Getter @Setter
    @NonNull
    private String cpf;

    @Column(name="NOME")
    @Getter @Setter
    @NonNull
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
