/**Computer Science IB SL
 * @author Shyam Panchal
 *
 * This class represents a person that can be
 * contacted if the student's guardians cannot
 * be reached in times of an emergency.
 */
class EmergencyContact {

    //Fields for the emergency contact
    String name;
    String phoneNum;

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
}
