package ua.training.cashregister.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.entity.ProductInCheck;
import ua.training.cashregister.repository.ProductInCheckRepository;

import java.math.BigInteger;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductInCheckServiceTest {
    @Mock
    ProductInCheckRepository productInCheckRepository;
    @InjectMocks
    ProductInCheckService productInCheckService;

    @Test
    public void testSaveNewProductInCheck() {
        ProductInCheck newProduct = ProductInCheck.builder()
                .id(1L)
                .quantity(BigInteger.valueOf(5))
                .build();

        productInCheckService.saveNewProductInCheck(newProduct);

        verify(productInCheckRepository, times(1)).save(newProduct);
    }
}