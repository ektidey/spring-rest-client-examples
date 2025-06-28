package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Test
    public void testGetUsers() {
        List<User> users = apiService.getUsers(3);
        assertEquals(3, users.size());
    }
}