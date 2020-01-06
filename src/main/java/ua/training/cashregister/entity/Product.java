package ua.training.cashregister.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "total_quantity")
    private BigInteger totalQuantity;

    @Column(name = "total_mass")
    private BigDecimal totalMass;
}
