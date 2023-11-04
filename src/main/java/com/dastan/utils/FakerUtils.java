package com.dastan.utils;

import com.github.javafaker.Faker;

public final class FakerUtils {
    private FakerUtils() {
    }

    static final Faker faker = new Faker();

    static int getNumber(int startValue, int endValue) {

        return faker.number().numberBetween(startValue, endValue);
    }

    static String getFirstName() {
        return faker.name().firstName();
    }

    static String getLastName() {
        return faker.name().lastName();
    }
}
