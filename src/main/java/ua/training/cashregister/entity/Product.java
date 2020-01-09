package ua.training.cashregister.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    @Column(name = "total_quantity")
    private BigInteger totalQuantity = BigInteger.ZERO;

    @Builder.Default
    @Column(name = "total_mass")
    private BigDecimal totalMass = BigDecimal.ZERO;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductInCheck> productsInChecks = new ArrayList<>();

    public void addProductInCheck(ProductInCheck productInCheck) {
        productInCheck.setProduct(this);
        productsInChecks.add(productInCheck);
    }
}
