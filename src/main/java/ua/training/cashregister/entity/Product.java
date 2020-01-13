package ua.training.cashregister.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
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

    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Positive(message = "Price must be positive")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @PositiveOrZero(message = "Total quantity cannot be negative")
    @Builder.Default
    @Column(name = "total_quantity")
    private BigInteger totalQuantity = BigInteger.ZERO;

    @PositiveOrZero(message = "Total mass cannot be negative")
    @Builder.Default
    @Column(name = "total_mass")
    private BigDecimal totalMass = BigDecimal.ZERO;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CheckEntry> checkEntries = new ArrayList<>();

    public void addCheckEntry(CheckEntry checkEntry) {
        checkEntry.setProduct(this);
        checkEntries.add(checkEntry);
    }
}
