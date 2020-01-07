package ua.training.cashregister.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.dto.ProductsDTO;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.entity.ProductType;
import ua.training.cashregister.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;
    ProductsDTO products;

    @Before
    public void initProducts() {
        products = ProductsDTO.builder()
                .products(Arrays.asList(
                        Product.builder()
                                .id(1L)
                                .name("Potato")
                                .price(BigDecimal.valueOf(4.59))
                                .productType(ProductType.BULK)
                                .totalMass(BigDecimal.valueOf(643.5))
                                .build(),
                        Product.builder()
                                .id(2L)
                                .name("Carrot")
                                .price(BigDecimal.valueOf(6.24))
                                .productType(ProductType.BULK)
                                .totalMass(BigDecimal.valueOf(48.87))
                                .build(),
                        Product.builder()
                                .id(3L)
                                .name("Bread")
                                .price(BigDecimal.valueOf(11.62))
                                .productType(ProductType.PIECE)
                                .totalQuantity(BigInteger.valueOf(43))
                                .build()
                        )
                ).build();
    }

    @Test
    public void testSaveNewProduct() {
        productService.saveNewProduct(products.getProducts().get(0));

        verify(productRepository, times(1)).save(products.getProducts().get(0));
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(products.getProducts());

        Assert.assertEquals(3, productService.getAllProducts().getProducts().size());
        verify(productRepository, times(1)).findAll();
    }
}