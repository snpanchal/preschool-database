package preschool;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddStudentsController extends SwitchScenes {
	// Student details
	@FXML private TextField studentName;
	@FXML private TextField studentClass;
	@FXML private TextField studentHealthCard;
	@FXML private TextField studentBirthday;
	@FXML private TextField studentConditions;
	// Parent/Guardian details
	@FXML private TextField guardian1Name;
	@FXML private TextField guardian1Address;
	@FXML private TextField guardian1Occupation;
	@FXML private TextField guardian1PhoneNum;
	@FXML private TextField guardian1Email;
	@FXML private TextField guardian2Name;
	@FXML private TextField guardian2Address;
	@FXML private TextField guardian2Occupation;
	@FXML private TextField guardian2PhoneNum;
	@FXML private TextField guardian2Email;
	// Family doctor details
	@FXML private TextField familyDoctorName;
	@FXML private TextField familyDoctorAddress;
	@FXML private TextField familyDoctorPhoneNum;
	// Emergency contact details
	@FXML private TextField emergencyContactName;
	@FXML private TextField emergencyContactPhoneNum;
	
	public void addStudent() throws ClassNotFoundException, SQLException {
		Guardian guardian1 = new Guardian(guardian1Name.getText(), guardian1Occupation.getText(), guardian1Address.getText(),
				guardian1PhoneNum.getText(), guardian1Email.getText());
		Guardian guardian2 = new Guardian(guardian2Name.getText(), guardian2Occupation.getText(), guardian2Address.getText(),
				guardian2PhoneNum.getText(), guardian2Email.getText());
		EmergencyContact emergencyContact = new EmergencyContact(emergencyContactName.getText(), emergencyContactPhoneNum.getText());
		FamilyDoctor familyDoctor = new FamilyDoctor(familyDoctorName.getText(), familyDoctorAddress.getText(), familyDoctorPhoneNum.getText());
		ArrayList<Guardian> guardians = new ArrayList<>();
		guardians.add(guardian1);
		guardians.add(guardian2);
		Student student = new Student(studentName.getText(), studentClass.getText(), studentBirthday.getText(), guardians,
				emergencyContact, familyDoctor, studentHealthCard.getText(), studentConditions.getText());
		
		DatabaseOperations.addStudent(student);
		studentName.clear();
		studentClass.clear();
		studentHealthCard.clear();
		studentBirthday.clear();
		studentConditions.clear();
		guardian1Name.clear();
		guardian1Address.clear();
		guardian1Occupation.clear();
		guardian1PhoneNum.clear();
		guardian1Email.clear();
		guardian2Name.clear();
		guardian2Address.clear();
		guardian2Occupation.clear();
		guardian2PhoneNum.clear();
		guardian2Email.clear();
		familyDoctorName.clear();
		familyDoctorAddress.clear();
		familyDoctorPhoneNum.clear();
		emergencyContactName.clear();
		emergencyContactPhoneNum.clear();
		
	}
}
