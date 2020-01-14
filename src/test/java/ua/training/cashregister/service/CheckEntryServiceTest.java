package ua.training.cashregister.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.dto.CheckEntriesDTO;
import ua.training.cashregister.entity.Check;
import ua.training.cashregister.entity.CheckEntry;
import ua.training.cashregister.repository.CheckEntryRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckEntryServiceTest {
    @Mock
    CheckEntryRepository checkEntryRepository;
    @InjectMocks
    CheckEntryService checkEntryService;
    Check check;
    CheckEntriesDTO productsInCheck;

    @Before
    public void initCheckAndProductsInCheck() {
        productsInCheck = CheckEntriesDTO.builder()
                .checkEntries(Arrays.asList(
                        CheckEntry.builder()
                                .id(1L)
                                .quantity(BigInteger.valueOf(5))
                                .build(),
                        CheckEntry.builder()
                                .id(2L)
                                .mass(BigDecimal.valueOf(3.643))
                                .build(),
                        CheckEntry.builder()
                                .id(3L)
                                .quantity(BigInteger.valueOf(67))
                                .build()
                        )
                ).build();

        check = Check.builder()
                .id(1L)
                .created(LocalDateTime.MIN)
                .build();

        check.addCheckEntry(productsInCheck.getCheckEntries().get(0));
        check.addCheckEntry(productsInCheck.getCheckEntries().get(1));
    }

    @Test
    public void testSaveNewProductInCheck() {
        checkEntryService.saveNewProductInCheck(productsInCheck.getCheckEntries().get(0));

        verify(checkEntryRepository, times(1))
                .save(productsInCheck.getCheckEntries().get(0));
    }

    @Test
    public void testFindProductsByCheck() {
        when(checkEntryRepository.findByCheck(check)).thenReturn(
                Arrays.asList(productsInCheck.getCheckEntries().get(0),
                        productsInCheck.getCheckEntries().get(1)));

        CheckEntriesDTO products = checkEntryService.findProductsByCheck(check);

        Assert.assertEquals(2, products.getCheckEntries().size());
        Assert.assertEquals(productsInCheck.getCheckEntries().get(0).getId(),
                products.getCheckEntries().get(0).getId());
        Assert.assertEquals(productsInCheck.getCheckEntries().get(1).getId(),
                products.getCheckEntries().get(1).getId());
    }

    @Test
    public void testDeleteProductInCheck() {
        checkEntryService.deleteProductInCheck(productsInCheck.getCheckEntries().get(0));

        verify(checkEntryRepository, times(1))
                .delete(productsInCheck.getCheckEntries().get(0));
    }

    //TODO: testDeleteProductInCheckNotExists
}