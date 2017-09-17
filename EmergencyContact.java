package preschool;

public class EmergencyContact {
    //Fields for the emergency contact
    private String name;
    private String phoneNum;

    /**
     * This is a constructor method for the
     * EmergencyContact object.
     * @param name emergency contact's name
     * @param phoneNum emergency contact's phone
     *                 number
     */
    EmergencyContact(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
