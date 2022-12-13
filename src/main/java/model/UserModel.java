package model;

public class UserModel {

    String firstName;
    String lastName;
    String address;
    String metro;
    String number;

    public UserModel(String firstName, String lastName, String address, String metro, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getMetro() {
        return metro;
    }

    public String getNumber() {
        return number;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }
}
