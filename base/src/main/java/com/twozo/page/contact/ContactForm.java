package com.twozo.page.contact;

import com.twozo.page.add.form.Form;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ContactForm implements Form {
    private String firstName;
    private String lastName;
    private List<Map<String, String>> emails;
    private List<Map<String, String>> phones;
    private String company;
    private String designation;
    private String salesOwner;
    private String lifecycleStage;
    private String source;
    private String territory;
    private List <String> tags;
    private String dateOfBirth;
    private String department;
    private Address address;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String subscriptionStatus;
    private String subscriptionTypes;
    private String timeZone;


    @Getter
    @Setter
    public static class Address {
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String state;
        private String country;
        private String pincode;
    }

}