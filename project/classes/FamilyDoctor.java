public class FamilyDoctor {
    //Fields for the family doctor
    private String name;
    private String address;
    private String phoneNum;

    /**
     * @author Shyam Panchal
     * Copyright Little People's Preschool 2017
     *
     * This is a constructor method for the FamilyDoctor
     * object.
     * @param name family doctor's name
     * @param address family doctor's address
     * @param phoneNum family doctor's phone number
     */
    FamilyDoctor(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
