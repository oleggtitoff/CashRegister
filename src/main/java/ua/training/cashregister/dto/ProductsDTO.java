package ua.training.cashregister.dto;

import lombok.*;
import ua.training.cashregister.entity.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductsDTO {
    private List<Product> products;
}
