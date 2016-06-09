package pl.elka.pw.pik.shop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.elka.pw.pik.shop.domain.model.User;
import pl.elka.pw.pik.shop.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService roomService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDummyTest() {
        //given
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        List<User> users = userRepository.findAll();

        //then
        Assert.assertEquals(users.size(), 0);
    }
}
