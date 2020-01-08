package ua.training.cashregister.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.repository.ProductInCheckRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductInCheckServiceTest {
    @Mock
    ProductInCheckRepository productInCheckRepository;
    @InjectMocks
    ProductInCheckService productInCheckService;

    @Test
    public void testAddNewProductInCheck() {
        //TODO: implement test
    }
}