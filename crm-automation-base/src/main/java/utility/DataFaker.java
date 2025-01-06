package utility;

import com.github.javafaker.Faker;

import java.util.Objects;

public class DataFaker {

    private static final Faker FAKER = new Faker();
    private static DataFaker DATA_FAKER;

    public static DataFaker getInstance() {

        if (Objects.isNull(DATA_FAKER)) {
            DATA_FAKER = new DataFaker();
        }
        return DATA_FAKER;
    }

    public String getFirstName() {
        return FAKER.name().firstName();
    }

    public String getLastName() {
        return FAKER.name().lastName();
    }

    public String getPhoneNumber() {
        return FAKER.phoneNumber().cellPhone();
    }

    public String getEmailId() {
        return FAKER.internet().emailAddress();
    }

    public String getJobTitle() {
        return FAKER.job().title();
    }

    public String getDepartment(){
        return FAKER.company().industry();
    }
    public String getDataExceedingTextLimit() {
        return FAKER.lorem().characters(256);
    }

    public String getAddressLine1() {
        return FAKER.address().buildingNumber();
    }

    public String getAddressLine2() {
        return FAKER.address().streetAddress();
    }

    public String getCity() {
        return FAKER.address().city();
    }

    public String getState() {
        return FAKER.address().state();
    }

    public String getCountry() {
        return FAKER.address().zipCode();
    }

    public String getZipcode() {
        return FAKER.address().zipCode();
    }
}
