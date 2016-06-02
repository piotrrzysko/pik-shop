package pl.elka.pw.pik.shop.dto;

import pl.elka.pw.pik.shop.domain.model.orders.Order;

public class AddressData {
    private String houseNumber;
    private String postalCode;
    private String street;
    private String city;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public AddressData() {}

    public AddressData(Order order) {
        this.firstName = order.getDeliveryFirstName();
        this.lastName = order.getDeliveryLastName();
        this.city = order.getDeliveryCity();
        this.phoneNumber = order.getDeliveryPhoneNumber();
        this.street = order.getDeliveryStreet();
        this.postalCode = order.getDeliveryPostalCode();
        this.houseNumber = order.getDeliveryHouseNumber();
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
