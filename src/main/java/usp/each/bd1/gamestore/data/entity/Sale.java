package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VENDA")
@NoArgsConstructor
public class Sale {

    @Id
    @Column(name="ID")
    @GeneratedValue
    @Getter @Setter
    private BigInteger id;

    @Column(name="HORA")
    @Getter @Setter
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(name="METODO_DE_PAGAMENTO")
    @Getter @Setter
    private String paymentMethod;

    @Column(name="VALOR_DE_COMPRA")
    @Getter @Setter
    private double totalAmount;

    @Column(name="DESCONTO_OBTIDO_POR_PONTOS")
    @Getter @Setter
    private double discountUsingPoints;

    @Column(name="PONTOS_A_ATRIBUIR")
    @Getter @Setter
    private int attributedPoints;

    @ManyToOne
    @JoinColumn(name="CPF_CAIXA")
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name="CPF_CLIENTE")
    private Customer buyer;

    @OneToMany(mappedBy = "sale")
    private List<Item> items;

    public Sale(final BigInteger id) {
        this.id = id;
    }
}
