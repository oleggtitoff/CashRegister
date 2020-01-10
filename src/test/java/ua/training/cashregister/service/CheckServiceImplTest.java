package ua.training.cashregister.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.training.cashregister.repository.CheckRepository;


@RunWith(MockitoJUnitRunner.class)
public class CheckServiceImplTest {
    @Mock
    CheckRepository checkRepository;
    @InjectMocks
    CheckServiceImpl checkServiceImpl;

    @Test
    public void testAddNewCheck() {
        //TODO: implement test
    }
}