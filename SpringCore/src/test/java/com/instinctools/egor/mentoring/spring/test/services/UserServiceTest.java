package com.instinctools.egor.mentoring.spring.test.services;

import com.instinctools.egor.mentoring.spring.config.AppConfig;
import com.instinctools.egor.mentoring.spring.core.entity.User;
import com.instinctools.egor.mentoring.spring.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createDeleteServiceTest() {
        Integer totalCount = userService.getAllUsers().size();
        User user = new User("aaa", new Date(System.currentTimeMillis()));
        userService.createUser(user);
        Assert.assertNotNull(user.getId());
        Assert.assertNotEquals(totalCount, (Integer) userService.getAllUsers().size());
        userService.deleteUser(user);
        Assert.assertEquals(totalCount, (Integer) userService.getAllUsers().size());

    }

    @Test
    public void updateGetByIdServiceTest() {
        User user = new User("aaa", new Date(System.currentTimeMillis()));
        userService.createUser(user);
        String oldname = user.getUserName();
        user.setUserName("newUserName");
        userService.updateUser(user);
        User userFromDB = userService.getUserById(user.getId());
        Assert.assertFalse(oldname.equals(userFromDB.getUserName()));
        userService.deleteUser(userFromDB);
    }
}
