package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.ProductInCheck;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckCreationDTO {
    private List<ProductInCheck> productsInCheck;

    public void addProductToCheck(ProductInCheck newProduct) {
        this.productsInCheck.add(newProduct);
    }
}
