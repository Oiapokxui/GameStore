package usp.each.bd1.gamestore.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COMPRA")
public class Purchase {

    @Id
    @Column(name="ID_COMPRA")
    @GeneratedValue
    private long purchaseId;

    @Column(name="HORA")
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(name="METODO_DE_PAGAMENTO")
    private String paymentMethod;

    @Column(name="VALOR_DE_COMPRA")
    private double totalAmount;

    @Column(name="DESCONTO_OBTIDO_POR_PONTOS")
    private double discountUsingPoints;

    @Column(name="PONTOS_A_ATRIBUIR")
    private int attributedPoints;

    @ManyToOne
    @JoinColumn(name="CPF_CAIXA")
    private Cashier cashier;

    @ManyToOne
    @JoinColumn(name="CPF_CLIENTE")
    private Customer customer;

    @OneToMany(mappedBy = "purchase")
    public List<Item> items;
}
