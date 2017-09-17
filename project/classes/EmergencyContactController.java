import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Shyam Panchal
 * Copyright Little People's Pre-School 2017
*/

public class EmergencyContactController extends SwitchScenes {
	@FXML private TextField studentSearch;
	@FXML private Label nameLabel;
	@FXML private Label phoneNumLabel;
	
	public void populateLabels() throws ClassNotFoundException, SQLException {
		String studentName = studentSearch.getText();
		ResultSet rs = DatabaseOperations.getEmergencyContactDetails(studentName);
		nameLabel.setText(rs.getString("Name"));
		phoneNumLabel.setText(rs.getString("PhoneNum"));
	}
}
