package org.example.models;

import lombok.Data;

import static org.example.utils.myUtils.*;

@Data
public class CustomerModel {

    private String firstName = generateRandomFirstName();
    private String lastName = generateRandomLastName();
    private String companyName = generateRandomCompanyName();
    private String country = generateRandomCountry();
    private String street = generateRandomStreet();
    private String houseNumber = generateRandomHouseNumber();
    private String zipCode = generateRandomZipCode();
    private String city = generateRandomCity();
    private String phone = generateRandomPhone();
    private String email = generateRandomEmail();
}
