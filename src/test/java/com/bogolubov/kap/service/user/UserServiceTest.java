package com.bogolubov.kap.service.user;

import com.bogolubov.kap.dao.department.entity.Department;
import com.bogolubov.kap.dao.position.entity.Position;
import com.bogolubov.kap.dao.user.entity.User;
import com.bogolubov.kap.repository.user.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@EnableJpaRepositories(basePackages = {"com.bogolubov.kap.repository.user"})
@EnableTransactionManagement
public class UserServiceTest extends Exception {

    @Mock
    private UserRepository userRepository;
    private static User user;
    private static Position position;
    private static Department department;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        position = new Position(1L, "", "");
        department = new Department(1L, "subdivision", "subdivision", "subdivision");
        user = new User(
                1L,
                "test",
                "a@mail.ru",
                "1234",
                "Test Test Testovich",
                true,
                position,
                department);
    }

    @Test
    public void getUser() {
        Assert.assertNotNull(user);
        Assert.assertNotNull(position);
        Assert.assertNotNull(department);
    }

    @Test
    public void findAll() {
        List<User> findAll = userRepository.findAll();
        Assert.assertNotNull(findAll);

        List<User> findAllUser = new ArrayList<>();
        Assert.assertEquals(findAllUser, userRepository.findAll());
    }
}