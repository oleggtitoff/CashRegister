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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.training.cashregister.entity.Role;
import ua.training.cashregister.entity.User;
import ua.training.cashregister.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

//    **For user appending**
//
//@RunWith(SpringRunner.class)
//@SpringBootTest

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {
    @Mock
//    **For user appending**
//    @Autowired

    UserRepository userRepository;

//    **For user appending**
//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;
    User user;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initUser() {
        user = User.builder()
                .username("John")
                .password("pass")
                .authorities(Arrays.asList(Role.CASHIER, Role.ADMIN))
                .build();
    }

//    **For user appending**
//
//    @Test
//    public void appendUser() {
//        System.out.println("------------------------------------");
//        try {
//            userRepository.save(User.builder()
//                    .username("c")
//                    .password(passwordEncoder.encode("c"))
//                    .authorities(Arrays.asList(Role.CASHIER))
//                    .build());
//        } catch (Exception ex) {
//            System.out.println("Something wrong...");
//        }
//    }

    @Test
    public void testLoadUserByUsername() {
        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.of(user));

        UserDetails found = userDetailsService.loadUserByUsername(user.getUsername());

        Assert.assertEquals(user.getUsername(), found.getUsername());
        verify(userRepository, times(1)).findByUsername(user.getUsername());
    }

    @Test
    public void testLoadUserByUsernameIfNotExist() {
        expectedException.expect(UsernameNotFoundException.class);

        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(Optional.empty());

        userDetailsService.loadUserByUsername(user.getUsername());
    }
}