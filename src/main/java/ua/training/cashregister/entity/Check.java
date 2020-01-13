package ua.training.cashregister.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "checks")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created")
    private LocalDateTime created;

    //TODO: cashier name

    @Builder.Default
    @Column(name = "is_in_fiscal_memory")
    private Boolean isInFiscalMemory = false;

    @Builder.Default
    @OneToMany(mappedBy = "check", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CheckEntry> checkEntries = new ArrayList<>();

    public void addCheckEntry(CheckEntry checkEntry) {
        checkEntry.setCheck(this);
        checkEntries.add(checkEntry);
    }
}
