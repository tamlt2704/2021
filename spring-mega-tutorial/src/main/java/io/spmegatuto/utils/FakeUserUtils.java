package io.spmegatuto.utils;

import com.github.javafaker.Faker;
import io.spmegatuto.model.User;

public class FakeUserUtils {
    private static Faker faker = new Faker();

    public static User getFakeUser() {
        return User.builder()
                .username(faker.name().username())
                .build();
    }
}
