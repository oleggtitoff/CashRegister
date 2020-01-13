package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.CheckEntry;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckEntryDTO {
    private List<CheckEntry> checkEntries;

    public void addCheckEntry(CheckEntry newProduct) {
        this.checkEntries.add(newProduct);
    }
}
