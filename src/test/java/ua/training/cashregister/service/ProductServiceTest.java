package ua.training.cashregister.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.entity.ProductType;
import ua.training.cashregister.repository.ProductRepository;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void saveNewProductTest() {
        Product newProduct = Product.builder()
                .name("Potato")
                .price(BigDecimal.valueOf(4.59))
                .productType(ProductType.BULK)
                .totalMass(BigDecimal.valueOf(643.5))
                .build();

        productService.saveNewProduct(newProduct);

        verify(productRepository, times(1)).save(newProduct);
    }
}