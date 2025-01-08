package utility;

import com.github.javafaker.Faker;

import java.util.Objects;

public class DataFaker {

    private static final Faker FAKER = new Faker();

    public static String getFirstName() {
        return FAKER.name().firstName();
    }

    public static String getLastName() {
        return FAKER.name().lastName();
    }

    public static String getPhoneNumber() {
        return FAKER.phoneNumber().cellPhone();
    }

    public static String getEmailId() {
        return FAKER.internet().emailAddress();
    }

    public static String getJobTitle() {
        return FAKER.job().title();
    }

    public static String getDepartment(){
        return FAKER.company().industry();
    }
    public static String getDataExceedingTextLimit() {
        return FAKER.lorem().characters(256);
    }

    public static String getAddressLine1() {
        return FAKER.address().buildingNumber();
    }

    public static String getAddressLine2() {
        return FAKER.address().streetAddress();
    }

    public static String getCity() {
        return FAKER.address().city();
    }

    public static String getState() {
        return FAKER.address().state();
    }

    public static String getCountry() {
        return FAKER.address().zipCode();
    }

    public static String getZipcode() {
        return FAKER.address().zipCode();
    }
}