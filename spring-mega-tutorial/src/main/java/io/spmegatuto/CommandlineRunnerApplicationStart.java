package io.spmegatuto;

import com.github.javafaker.Faker;
import io.spmegatuto.model.User;
import io.spmegatuto.service.UserService;
import io.spmegatuto.utils.FakeUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class CommandlineRunnerApplicationStart implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = FakeUserUtils.getFakeUser();
            userService.save(user);
        }

        System.out.println("you should see me when application start!!!");
    }
}
