package io.code.spweb;

import io.code.spweb.model.User;
import io.code.spweb.repository.UserRepository;
import io.code.spweb.service.UserService;
import io.code.spweb.utils.FakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = new User(i, FakeUtil.getRandomUserName());
            userService.saveOrUpdate(user);
        }
    }
}
