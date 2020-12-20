package io.code.spweb.utils;

import com.github.javafaker.Faker;

public class FakeUtil {
    static Faker faker = new Faker();

    public static String getRandomUserName() {
        return faker.name().username();
    }
}
