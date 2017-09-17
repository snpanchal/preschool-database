package preschool;

import javafx.beans.property.SimpleStringProperty;

public class Guardian {
    //Fields for the guardian
    private SimpleStringProperty name;
    private String address;
    private String occupation;
    private String phoneNum;
    private String email;

    public Guardian(String name, String occupation, String address, String phoneNum, String email) {
        this.name = new SimpleStringProperty(name);
        this.address = address;
        this.occupation = occupation;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
