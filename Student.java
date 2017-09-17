package preschool;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Student {

    //Fields for each student
    private SimpleStringProperty name;
    private SimpleStringProperty classID;
    private SimpleStringProperty birthday;
    private ArrayList<Guardian> guardians;
    private SimpleStringProperty guardian1Name;
    private SimpleStringProperty guardian2Name;
    private EmergencyContact emergencyContact;
    private FamilyDoctor familyDoctor;
    private SimpleStringProperty healthCardNum;
    private SimpleStringProperty conditions;

    public Student(String name, String classID, String birthday, ArrayList<Guardian> guardians,
                   EmergencyContact emergencyContact, FamilyDoctor familyDoctor,
                   String healthCardNum, String conditions) {
        this.name = new SimpleStringProperty(name);
        this.classID = new SimpleStringProperty(classID);
        this.birthday = new SimpleStringProperty(birthday);
        this.guardians = guardians;
        this.emergencyContact = emergencyContact;
        this.familyDoctor = familyDoctor;
        this.healthCardNum = new SimpleStringProperty(healthCardNum);
        this.conditions = new SimpleStringProperty(conditions);
        this.guardian1Name = new SimpleStringProperty(guardians.get(0).getName());
        this.guardian2Name = new SimpleStringProperty(guardians.get(1).getName());
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

    public String getClassID() {
        return classID.get();
    }

    public SimpleStringProperty classIDProperty() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID.set(classID);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public SimpleStringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public ArrayList<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(ArrayList<Guardian> guardians) {
        this.guardians = guardians;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public FamilyDoctor getFamilyDoctor() {
        return familyDoctor;
    }

    public void setFamilyDoctor(FamilyDoctor familyDoctor) {
        this.familyDoctor = familyDoctor;
    }

    public String getHealthCardNum() {
        return healthCardNum.get();
    }

    public SimpleStringProperty healthCardNumProperty() {
        return healthCardNum;
    }

    public void setHealthCardNum(String healthCardNum) {
        this.healthCardNum.set(healthCardNum);
    }

    public String getConditions() {
        return conditions.get();
    }

    public SimpleStringProperty conditionsProperty() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions.set(conditions);
    }

    public String getGuardian1Name() {
        return guardian1Name.get();
    }

    public SimpleStringProperty guardian1NameProperty() {
        return guardian1Name;
    }

    public void setGuardian1Name(String guardian1Name) {
        this.guardian1Name.set(guardian1Name);
    }

    public String getGuardian2Name() {
        return guardian2Name.get();
    }

    public SimpleStringProperty guardian2NameProperty() {
        return guardian2Name;
    }

    public void setGuardian2Name(String guardian2Name) {
        this.guardian2Name.set(guardian2Name);
    }
}