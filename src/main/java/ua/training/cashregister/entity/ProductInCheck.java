package ua.training.cashregister.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "products_in_checks")
public class ProductInCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Check check;

    @Builder.Default
    @Column(name = "quantity")
    private BigInteger quantity = BigInteger.ZERO;

    @Builder.Default
    @Column(name = "mass")
    private BigDecimal mass = BigDecimal.ZERO;
}
