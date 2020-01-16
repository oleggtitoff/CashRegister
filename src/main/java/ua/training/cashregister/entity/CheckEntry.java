package ua.training.cashregister.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "check_entries")
public class CheckEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "check_id")
    private Check check;

    @Builder.Default
    @Column(name = "quantity")
    private BigInteger quantity = BigInteger.ZERO;

    @Builder.Default
    @Column(name = "mass")
    private BigDecimal mass = BigDecimal.ZERO;

    //TODO: replace null checking
    @Override
    public String toString() {
        return "CheckEntry{" +
                "id=" + id +
                ", product=" + (product != null ? product.getId() : null) +
                ", checkId=" + (check != null ? check.getId() : null) +
                ", quantity=" + quantity +
                ", mass=" + mass +
                '}';
    }
}
