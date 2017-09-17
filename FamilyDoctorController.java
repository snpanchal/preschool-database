package preschool;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FamilyDoctorController extends SwitchScenes {
	@FXML private TextField studentSearch;
	@FXML private Label	nameLabel;
	@FXML private Label addressLabel;
	@FXML private Label phoneNumLabel;
	
	@FXML
	public void populateLabels() throws ClassNotFoundException, SQLException {
		String studentName = studentSearch.getText();
		ResultSet rs = DatabaseOperations.getFamilyDoctorDetails(studentName);
		nameLabel.setText(rs.getString("Name"));
		addressLabel.setText(rs.getString("Address"));
		phoneNumLabel.setText(rs.getString("PhoneNum"));
	}
}
