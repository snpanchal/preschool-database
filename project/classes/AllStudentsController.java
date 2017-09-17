package preschool;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AllStudentsController extends SwitchScenes implements Initializable {
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> classColumn;
    @FXML private TableColumn<Student, String> birthdayColumn;
    @FXML private TableColumn<Student, String> guardian1Column;
    @FXML private TableColumn<Student, String> guardian2Column;
    @FXML private TableColumn<Student, String> healthCardColumn;
    @FXML private TableColumn<Student, String> conditionsColumn;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("classID"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        guardian1Column.setCellValueFactory(new PropertyValueFactory<>("guardian1Name"));
        guardian2Column.setCellValueFactory(new PropertyValueFactory<>("guardian2Name"));
        healthCardColumn.setCellValueFactory(new PropertyValueFactory<>("healthCardNum"));
        conditionsColumn.setCellValueFactory(new PropertyValueFactory<>("conditions"));
        
        try {
            studentTable.setItems(getStudents());
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public ObservableList<Student> getStudents() throws SQLException, ClassNotFoundException {
        ObservableList<Student> students = FXCollections.observableArrayList();
        ResultSet rs = DatabaseOperations.getStudentDetails();
        while (rs.next()) {
            ResultSet parentsDetails = DatabaseOperations.getGuardianDetails(rs.getString("Name"));
            Guardian guardian1 = new Guardian(parentsDetails.getString("Name1"),
                    parentsDetails.getString("Occupation1"), parentsDetails.getString("Address1"),
                    parentsDetails.getString("PhoneNum1"), parentsDetails.getString("Email1"));
            Guardian guardian2 = new Guardian(parentsDetails.getString("Name2"),
                    parentsDetails.getString("Occupation2"), parentsDetails.getString("Address2"),
                    parentsDetails.getString("PhoneNum2"), parentsDetails.getString("Email2"));
            ArrayList<Guardian> guardians = new ArrayList<>();
            guardians.add(guardian1);
            guardians.add(guardian2);

            ResultSet emergencyContactDetails = DatabaseOperations.getEmergencyContactDetails(rs.getString("Name"));
            EmergencyContact emergencyContact = new EmergencyContact(emergencyContactDetails.getString("Name"),
                    emergencyContactDetails.getString("PhoneNum"));

            ResultSet familyDoctorDetails = DatabaseOperations.getFamilyDoctorDetails(rs.getString("Name"));
            FamilyDoctor familyDoctor = new FamilyDoctor(familyDoctorDetails.getString("Name"),
                    familyDoctorDetails.getString("Address"), familyDoctorDetails.getString("PhoneNum"));

            Student student = new Student(rs.getString("Name"), rs.getString("Class"),
                    rs.getString("Birthday"), guardians, emergencyContact, familyDoctor,
                    rs.getString("HealthCardNum"), rs.getString("Conditions"));

            students.add(student);
        }

        return students;
    }
}
