/**Computer Science IB SL
 * @author Shyam Panchal
 *
 * This class represents a family doctor for each
 * student.
 */
class FamilyDoctor {

    //Fields for the family doctor
    String name;
    String address;
    String phoneNum;

    /**
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
}
