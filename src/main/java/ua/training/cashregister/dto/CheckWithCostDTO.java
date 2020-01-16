package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.Check;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckWithCostDTO {
    private Check check;
    private BigDecimal cost;
}
