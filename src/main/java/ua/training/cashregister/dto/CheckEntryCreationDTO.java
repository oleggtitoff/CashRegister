package ua.training.cashregister.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckEntryCreationDTO {
    @NotBlank
    private String searchBy;

    @Positive(message = "Number must be positive")
    private BigDecimal number;
}
