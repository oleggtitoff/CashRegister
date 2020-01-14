package ua.training.cashregister.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.ProductNotFoundException;
import ua.training.cashregister.entity.Product;
import ua.training.cashregister.entity.ProductType;
import ua.training.cashregister.repository.ProductRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productServiceImpl;
    List<Product> products;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initProducts() {
        products = Arrays.asList(
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
        );
    }

    @Test
    public void testSaveNewProduct() {
        productServiceImpl.saveNewProduct(products.get(0));

        verify(productRepository, times(1)).save(products.get(0));
    }

    //TODO: add testSaveNewProductIfAlreadyExist

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(products);

        Assert.assertEquals(3, productServiceImpl.getAllProducts().size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindProductByIdOrName() {
        String searchById = "3";
        String searchByName = "Carrot";

        when(productRepository.findById(Long.parseLong(searchById)))
                .thenReturn(Optional.of(products.get(2)));
        when(productRepository.findByName(searchByName))
                .thenReturn(Optional.of(products.get(1)));

        Product resultById = productServiceImpl.findProductByIdOrName(searchById);
        Product resultByName = productServiceImpl.findProductByIdOrName(searchByName);

        Assert.assertEquals(products.get(2).getId(), resultById.getId());
        Assert.assertEquals(products.get(1).getName(), resultByName.getName());
        verify(productRepository, times(1)).findById(3L);
        verify(productRepository, times(1)).findByName(searchByName);
    }

    @Test
    public void testFindProductByIdOrNameIfNotExist() {
        expectedException.expect(ProductNotFoundException.class);
        String searchBy = "eaohe";

        when(productRepository.findByName(searchBy)).thenReturn(Optional.empty());

        productServiceImpl.findProductByIdOrName(searchBy);
        verify(productRepository, times(1))
                .findByName(searchBy);
    }

    @Test
    public void testFindProductById() {
        int index = 2;
        Long id = products.get(index).getId();

        when(productRepository.findById(id))
                .thenReturn(Optional.of(products.get(index)));

        Product product = productServiceImpl.findProductById(id);

        Assert.assertEquals(products.get(index).getId(), product.getId());
        Assert.assertEquals(products.get(index).getName(), product.getName());
        verify(productRepository, times(1)).findById(id);
    }

    //TODO: add testFindProductByIdIfNotExist
    //TODO: add testFindProductByNameIfNotExist

    @Test
    public void testFindProductByName() {
        int index = 2;
        String name = products.get(index).getName();

        when(productRepository.findByName(name))
                .thenReturn(Optional.of(products.get(index)));

        Product product = productServiceImpl.findProductByName(name);

        Assert.assertEquals(products.get(index).getId(), product.getId());
        Assert.assertEquals(products.get(index).getName(), product.getName());
        verify(productRepository, times(1)).findByName(name);
    }
}