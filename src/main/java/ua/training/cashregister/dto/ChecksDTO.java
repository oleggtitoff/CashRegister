package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.Check;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChecksDTO {
    private List<Check> checks;
}
