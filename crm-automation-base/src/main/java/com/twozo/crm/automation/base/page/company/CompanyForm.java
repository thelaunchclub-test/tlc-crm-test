package com.twozo.crm.automation.base.page.company;

import com.twozo.crm.automation.base.page.add.form.Form;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyForm implements Form {
    private String name;
    private String website;
    private String salesOwner;
    private Address address;
    private String facebook;
    private String twitter;
    private String linkedin;
    private AnnualRevenue annualRevenue;
    private String SICCode;
    private String territory;
    private List<String> tags;
    private String employees;
    private String parentCompany;
    private String organizationStatus;
    private String industryType;
    private String businessType;
    private String description;

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

    @Getter
    @Setter
    public static class AnnualRevenue {
        private String value;
        private String currency;
    }
}
