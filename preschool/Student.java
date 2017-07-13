import java.util.ArrayList;

/**
 * Copyright Little Flowers Daycare & Preschool 2017
 * @author Shyam Panchal
 *
 * This class represents a student who has multiple properties
 * that are crucial to the database.
 */
class Student {

    //Fields for each student
    String name;
    String classID;
    String birthday;
    ArrayList<Guardian> guardians;
    EmergencyContact emergencyContact;
    FamilyDoctor familyDoctor;
    String healthCardNum;
    String conditions;

    /**
     * This is a constructor method for the Student object.
     * @param name student name
     * @param classID student's class
     * @param birthday student's birthday
     * @param guardians ArrayList containing student's guardians
     * @param emergencyContact student's emergency contact
     * @param familyDoctor student's family doctor
     * @param healthCardNum student's health card number
     * @param conditions special conditions the student might
     *                   have (e.g. allergies)
     */
    Student(String name, String classID, String birthday, ArrayList<Guardian> guardians,
            EmergencyContact emergencyContact, FamilyDoctor familyDoctor, String healthCardNum,
            String conditions) {
        this.name = name;
        this.classID = classID;
        this.birthday = birthday;
        this.guardians = guardians;
        this.emergencyContact = emergencyContact;
        this.familyDoctor = familyDoctor;
        this.healthCardNum = healthCardNum;
        this.conditions = conditions;
    }
}
