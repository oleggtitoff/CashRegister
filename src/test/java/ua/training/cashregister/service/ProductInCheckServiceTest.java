package ua.training.cashregister.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.dto.ProductsInCheckDTO;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.ProductInCheck;
import ua.training.cashregister.repository.ProductInCheckRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductInCheckServiceTest {
    @Mock
    ProductInCheckRepository productInCheckRepository;
    @InjectMocks
    ProductInCheckService productInCheckService;
    Check check;
    ProductsInCheckDTO productsInCheck;

    @Before
    public void initCheckAndProductsInCheck() {
        productsInCheck = ProductsInCheckDTO.builder()
                .productsInCheck(Arrays.asList(
                        ProductInCheck.builder()
                                .id(1L)
                                .quantity(BigInteger.valueOf(5))
                                .build(),
                        ProductInCheck.builder()
                                .id(2L)
                                .mass(BigDecimal.valueOf(3.643))
                                .build(),
                        ProductInCheck.builder()
                                .id(3L)
                                .quantity(BigInteger.valueOf(67))
                                .build()
                        )
                ).build();

        check = Check.builder()
                .id(1L)
                .created(LocalDateTime.MIN)
                .build();

        check.addProductInCheck(productsInCheck.getProductsInCheck().get(0));
        check.addProductInCheck(productsInCheck.getProductsInCheck().get(1));
    }

    @Test
    public void testSaveNewProductInCheck() {
        productInCheckService.saveNewProductInCheck(productsInCheck.getProductsInCheck().get(0));

        verify(productInCheckRepository, times(1))
                .save(productsInCheck.getProductsInCheck().get(0));
    }

    @Test
    public void testFindProductsByCheck() {
        when(productInCheckRepository.findByCheck(check)).thenReturn(
                Arrays.asList(productsInCheck.getProductsInCheck().get(0),
                        productsInCheck.getProductsInCheck().get(1)));

        ProductsInCheckDTO products = productInCheckService.findProductsByCheck(check);

        Assert.assertEquals(2, products.getProductsInCheck().size());
        Assert.assertEquals(productsInCheck.getProductsInCheck().get(0).getId(),
                products.getProductsInCheck().get(0).getId());
        Assert.assertEquals(productsInCheck.getProductsInCheck().get(1).getId(),
                products.getProductsInCheck().get(1).getId());
    }

    @Test
    public void testDeleteProductInCheck() {
        productInCheckService.deleteProductInCheck(productsInCheck.getProductsInCheck().get(0));

        verify(productInCheckRepository, times(1))
                .delete(productsInCheck.getProductsInCheck().get(0));
    }

    //TODO: testDeleteProductInCheckNotExists
}