package usp.each.bd1.gamestore.data.entity;

import static org.hibernate.annotations.CascadeType.DELETE;

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
    @Cascade(DELETE)
    @Getter @Setter
    private String cpf;

    @Column(name="NOME")
    @Getter @Setter
    private String name;
}
