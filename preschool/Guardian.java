package preschool;

/**
 * Copyright Little Flowers Daycare & Preschool 2017
 * @author Shyam Panchal
 *
 * This class represents a guardian to an added
 * student.
 */
class Guardian {

    //Fields for the guardian
    String name;
    String address;
    String occupation;
    String phoneNum;
    String email;

    /**
     * This is a constructor method for the Guardian
     * object.
     * @param name guardian's name
     * @param address guardian's address
     * @param occupation guardian's occupation
     * @param phoneNum guardian's phone number
     * @param email guardian's email
     */
    Guardian(String name, String address, String occupation, String phoneNum, String email) {
        this.name = name;
        this.address = address;
        this.occupation = occupation;
        this.phoneNum = phoneNum;
        this.email = email;
    }
}
