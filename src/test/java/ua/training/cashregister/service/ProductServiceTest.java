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
import java.util.Optional;

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

    //TODO: add testSaveNewProductIfAlreadyExist

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(products.getProducts());

        Assert.assertEquals(3, productService.getAllProducts().getProducts().size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindProductById() {
        int index = 2;
        Long id = products.getProducts().get(index).getId();

        when(productRepository.findById(id))
                .thenReturn(Optional.of(products.getProducts().get(index)));

        Product product = productService.findProductById(id);

        Assert.assertEquals(products.getProducts().get(index).getId(), product.getId());
        Assert.assertEquals(products.getProducts().get(index).getName(), product.getName());
        verify(productRepository, times(1)).findById(id);
    }

    //TODO: add testFindProductByIdIfNotExist
    //TODO: add testFindProductByNameIfNotExist

    @Test
    public void testFindProductByName() {
        int index = 2;
        String name = products.getProducts().get(index).getName();

        when(productRepository.findByName(name))
                .thenReturn(Optional.of(products.getProducts().get(index)));

        Product product = productService.findProductByName(name);

        Assert.assertEquals(products.getProducts().get(index).getId(), product.getId());
        Assert.assertEquals(products.getProducts().get(index).getName(), product.getName());
        verify(productRepository, times(1)).findByName(name);
    }
}