package io.spmegatuto.utilities;

import com.github.javafaker.Faker;

public class FakeUtil {
    private static Faker faker = new Faker();

    public static String getFakeUserName() {
        return faker.name().username();
    }
}