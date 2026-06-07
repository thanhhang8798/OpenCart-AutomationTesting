package com.opencart.utilities;

import net.datafaker.Faker;

import java.util.concurrent.TimeUnit;

public class DataFakerConfig {
    private Faker faker = new Faker();

    public static DataFakerConfig getFaker() {
        return new DataFakerConfig();
    }

    public String getEmailAddress() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.credentials().password();
    }

    public String getPasswordAdvanced() {
        return faker.text().text(6,18);
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getFullName() {
        return faker.name().fullName();
    }

    public String getNumberFiveDigits() {
        return faker.number().digits(5);
    }

    public String getDate() {
        return faker.timeAndDate().future(1, TimeUnit.DAYS, "yyyy-MM-dd");
    }

    public String getDateOfBirth() {
        return faker.timeAndDate().past(1, TimeUnit.DAYS, "yyyy-MM-dd");
    }

    public String getNationality() {
        return faker.nation().nationality();
    }

    public String getDescription() {
        return faker.restaurant().description();
    }

    public String getStreetAddress() {
        return faker.address().streetAddress();
    }

    public String getCityAddress() {
        return faker.address().city();
    }

    public String getCountry() {
        return faker.country().name();
    }

    public String getCountryCode() {
        return faker.country().countryCode2();
    }

}



