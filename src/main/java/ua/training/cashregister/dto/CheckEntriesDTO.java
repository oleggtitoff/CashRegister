package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.CheckEntry;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckEntriesDTO {
    @Builder.Default
    private List<CheckEntry> checkEntries = new ArrayList<>();

    public void addCheckEntry(CheckEntry checkEntry) {
        this.checkEntries.add(checkEntry);
    }
}
