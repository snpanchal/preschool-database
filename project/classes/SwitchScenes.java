import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Shyam Panchal
 * Copyright Little People's Pre-School 2017
 */

public class SwitchScenes {
    @FXML
    public void allStudentsClicked(ActionEvent event) throws IOException {
        Parent allStudentsParent = FXMLLoader.load(getClass().getResource("allStudents.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();

    }

    @FXML
    public void familyDoctorsClicked(ActionEvent event) throws IOException {
        Parent allStudentsParent = FXMLLoader.load(getClass().getResource("familyDoctors.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();
    }

    @FXML
    public void emergencyContactsClicked(ActionEvent event) throws IOException {
        Parent allStudentsParent = FXMLLoader.load(getClass().getResource("emergencyContacts.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();
    }

    @FXML
    public void deleteStudentsClicked(ActionEvent event) throws IOException {
        Parent allStudentsParent = FXMLLoader.load(getClass().getResource("deleteStudents.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();
    }

    @FXML
    public void classListClicked(ActionEvent event) throws IOException {
        Parent allStudentsParent = FXMLLoader.load(getClass().getResource("classList.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();
    }

    @FXML
    public void parentsClicked(ActionEvent event) throws IOException {
        Parent allStudentsParent = FXMLLoader.load(getClass().getResource("parents.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();
    }
    
    @FXML
    public void addStudentsClicked(ActionEvent event) throws IOException {
    	Parent allStudentsParent = FXMLLoader.load(getClass().getResource("addStudents.fxml"));
        Scene allStudentsScene = new Scene(allStudentsParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(allStudentsScene);
        appStage.show();
	}
}
