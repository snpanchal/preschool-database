import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Shyam Panchal
 * Copyright Little People's Pre-School 2017
*/

public class ParentController extends SwitchScenes {
	@FXML private TextField studentSearch;
	// Guardian 1 labels
	@FXML private Label guardian1Name;
	@FXML private Label guardian1Address;
	@FXML private Label guardian1Occupation;
	@FXML private Label guardian1PhoneNum;
	@FXML private Label guardian1Email;
	// Guardian 2 labels
	@FXML private Label guardian2Name;
	@FXML private Label guardian2Address;
	@FXML private Label guardian2Occupation;
	@FXML private Label guardian2PhoneNum;
	@FXML private Label guardian2Email;

	@FXML
	public void populateParentInfo() throws ClassNotFoundException, SQLException {
		String studentName = studentSearch.getText();
		ResultSet rs = DatabaseOperations.getGuardianDetails(studentName);
		// Populate guardian 1 labels
		guardian1Name.setText(rs.getString("Name1"));
		guardian1Address.setText(rs.getString("Address1"));
		guardian1Occupation.setText(rs.getString("Occupation1"));
		guardian1PhoneNum.setText(rs.getString("PhoneNum1"));
		guardian1Email.setText(rs.getString("Email1"));
		// Populate guardian 2 labels
		guardian2Name.setText(rs.getString("Name2"));
		guardian2Address.setText(rs.getString("Address2"));
		guardian2Occupation.setText(rs.getString("Occupation2"));
		guardian2PhoneNum.setText(rs.getString("PhoneNum2"));
		guardian2Email.setText(rs.getString("Email2"));
	}
	
}
