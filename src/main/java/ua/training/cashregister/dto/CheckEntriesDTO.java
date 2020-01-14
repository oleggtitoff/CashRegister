package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.CheckEntry;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckEntriesDTO {
    private List<CheckEntry> checkEntries;

    public void addCheckEntry(CheckEntry checkEntry) {
        this.checkEntries.add(checkEntry);
    }
}
