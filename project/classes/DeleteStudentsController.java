import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
* @author Shyam Panchal
* Copyright Little People's Pre-School 2017
*/

public class DeleteStudentsController extends SwitchScenes {
	@FXML private TextField studentField;
	@FXML private Label label;
	
	public void deleteStudent() throws ClassNotFoundException, SQLException {
		String studentName = studentField.getText();
		DatabaseOperations.deleteStudent(studentName);
		label.setText("The student has been deleted from the database.");
	}
}
