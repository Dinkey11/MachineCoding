package models.user;

public class Address {

    private Integer zipCode;
    private String streetName;
    private String city;
    private String country;

    public Address(Integer zipCode, String streetName, String city, String country) {
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
    }

    public Address() {
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
