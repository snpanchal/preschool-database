package preschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
    private static Connection conn;//Connection to the database

    /**
     * This method establishes a connection with the database file
     * using drivers built specifically for SQLite.
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static void getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:preschoolDB.db");
    }

    /**
     * This method builds four tables that will be used to organize
     * the information. These tables are 'Students', 'Guardians',
     * 'EmergencyContacts', and 'FamilyDoctors'.
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static void buildTables() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        Statement stmt = conn.createStatement();
        //Creates students table
        stmt.execute("CREATE TABLE IF NOT EXISTS Students (StudentID INTEGER NOT NULL, Name VARCHAR(70), Class VARCHAR(70), " +
                "Birthday VARCHAR(70), GuardianID INTEGER, EmergencyContactID INTEGER, FamilyDoctorID INTEGER, HealthCardNum " +
                "VARCHAR(70), Conditions VARCHAR(70), PRIMARY KEY (StudentID), FOREIGN KEY (GuardianID) REFERENCES Guardians" +
                "(GuardianID), FOREIGN KEY (EmergencyContactID) REFERENCES EmergencyContacts(EmergencyContactID), FOREIGN KEY " +
                "(FamilyDoctorID) REFERENCES FamilyDoctors(FamilyDoctorID));");

        //Creates guardians table
        stmt.execute("CREATE TABLE IF NOT EXISTS Guardians (GuardianID INTEGER NOT NULL, Name1 VARCHAR(70), Address1 VARCHAR(70), " +
                "Occupation1 VARCHAR(70), PhoneNum1 VARCHAR(70), Email1 VARCHAR(70), Name2 VARCHAR(70), Address2 VARCHAR(70), " +
                "Occupation2 VARCHAR(70), PhoneNum2 VARCHAR(70), Email2 VARCHAR(70), PRIMARY KEY(GuardianID));");

        //Creates emergency contacts table
        stmt.execute("CREATE TABLE IF NOT EXISTS EmergencyContacts (EmergencyContactID INTEGER NOT NULL, Name VARCHAR(70), " +
                "PhoneNum VARCHAR(70), PRIMARY KEY (EmergencyContactID));");

        //Create family doctors table
        stmt.execute("CREATE TABLE IF NOT EXISTS FamilyDoctors (FamilyDoctorID INTEGER NOT NULL, Name VARCHAR(70), Address " +
                "VARCHAR(70), PhoneNum VARCHAR(70), PRIMARY KEY (FamilyDoctorID));");
    }

    /**
     * This method adds a student into the database and populates
     * every table with relevant information.
     * @param student the Student object, which's details need to
     *                be added to the database
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static void addStudent(Student student) throws SQLException, ClassNotFoundException {
        buildTables();

        //Fill information into Guardians table
        PreparedStatement prep1 = conn.prepareStatement("INSERT INTO Guardians(Name1, Address1, Occupation1, PhoneNum1, Email1, " +
                "Name2, Address2, Occupation2, PhoneNum2, Email2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        //Populating the PreparedStatement with relevant parameters
        prep1.setString(1, student.getGuardians().get(0).getName());
        prep1.setString(2, student.getGuardians().get(0).getAddress());
        prep1.setString(3, student.getGuardians().get(0).getOccupation());
        prep1.setString(4, student.getGuardians().get(0).getPhoneNum());
        prep1.setString(5, student.getGuardians().get(0).getEmail());
        if (student.getGuardians().size() > 1) {
            prep1.setString(6, student.getGuardians().get(1).getName());
            prep1.setString(7, student.getGuardians().get(1).getAddress());
            prep1.setString(8, student.getGuardians().get(1).getOccupation());
            prep1.setString(9, student.getGuardians().get(1).getPhoneNum());
            prep1.setString(10, student.getGuardians().get(1).getEmail());
        }
        prep1.executeUpdate();

        //Fill information into EmergencyContacts table
        PreparedStatement prep2 = conn.prepareStatement("INSERT INTO EmergencyContacts(Name, PhoneNum) VALUES(?, ?);");
        prep2.setString(1, student.getEmergencyContact().getName());
        prep2.setString(2, student.getEmergencyContact().getPhoneNum());
        prep2.executeUpdate();

        //Fill information into FamilyDoctors table
        PreparedStatement prep3 = conn.prepareStatement("INSERT INTO FamilyDoctors(Name, Address, PhoneNum) VALUES (?, ?, ?);");
        prep3.setString(1, student.getFamilyDoctor().getName());
        prep3.setString(2, student.getFamilyDoctor().getAddress());
        prep3.setString(3, student.getFamilyDoctor().getPhoneNum());
        prep3.executeUpdate();

        //Get GuardianID to connect to Students table as a foreign key
        PreparedStatement prep4 = conn.prepareStatement("SELECT GuardianID FROM Guardians WHERE Name1 = ? OR Name2 = ?;");
        prep4.setString(1, student.getGuardians().get(0).getName());
        //If more than one guardian
        if (student.getGuardians().size() > 1) {
            prep4.setString(2, student.getGuardians().get(1).getName());
        }
        int guardianID = 0;
        ResultSet rs1 = prep4.executeQuery();
        while (rs1.next()) {
            guardianID = rs1.getInt("GuardianID");
        }

        //Get EmergencyContactID to connect to Students table as a foreign key
        PreparedStatement prep5 = conn.prepareStatement("SELECT EmergencyContactID FROM EmergencyContacts WHERE Name = ?;");
        prep5.setString(1, student.getEmergencyContact().getName());
        ResultSet rs2 = prep5.executeQuery();
        int emergencyContactID = 0;
        while (rs2.next()) {
            emergencyContactID = rs2.getInt("EmergencyContactID");
        }

        //Get FamilyDoctorID to connect to Students table as a foreign key
        PreparedStatement prep6 = conn.prepareStatement("SELECT FamilyDoctorID FROM FamilyDoctors WHERE Name = ?;");
        prep6.setString(1, student.getFamilyDoctor().getName());
        ResultSet rs3 = prep6.executeQuery();
        int familyDoctorID = 0;
        while (rs3.next()) {
            familyDoctorID = rs3.getInt("FamilyDoctorID");
        }

        //Fill in all information into Students table
        PreparedStatement prep7 = conn.prepareStatement("INSERT INTO Students(Name, Class, Birthday, GuardianID, EmergencyContactID, " +
                "FamilyDoctorID, HealthCardNum, Conditions) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
        prep7.setString(1, student.getName());
        prep7.setString(2, student.getClassID());
        prep7.setString(3, student.getBirthday());
        prep7.setInt(4, guardianID);
        prep7.setInt(5, emergencyContactID);
        prep7.setInt(6, familyDoctorID);
        prep7.setString(7, student.getHealthCardNum());
        prep7.setString(8, student.getConditions());
        prep7.executeUpdate();
    }

    /**
     * This method deletes a student from the database that
     * is specified by the user.
     * @param name name of the student to be deleted
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static void deleteStudent(String name) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        PreparedStatement prep1 = conn.prepareStatement("SELECT GuardianID, EmergencyContactID, FamilyDoctorID FROM Students " +
                "WHERE Name = ?;");
        prep1.setString(1, name);
        ResultSet rs = prep1.executeQuery();
        int guardianID = 0;
        int emergencyContactID = 0;
        int familyDoctorID = 0;
        while (rs.next()) {
            guardianID = rs.getInt("GuardianID");
            emergencyContactID = rs.getInt("EmergencyContactID");
            familyDoctorID = rs.getInt("FamilyDoctorID");
        }

        //Delete student from the Students table
        PreparedStatement prep2 = conn.prepareStatement("DELETE FROM Students WHERE Name = ?;");
        prep2.setString(1, name);
        prep2.executeUpdate();

        //Delete relevant guardians
        PreparedStatement prep3 = conn.prepareStatement("DELETE FROM Guardians WHERE GuardianID = ?;");
        prep3.setInt(1, guardianID);
        prep3.executeUpdate();

        //Delete relevant emergency contact
        PreparedStatement prep4 = conn.prepareStatement("DELETE FROM EmergencyContacts WHERE EmergencyContactID = ?;");
        prep4.setInt(1, emergencyContactID);
        prep4.executeUpdate();

        //Delete relevant family doctor
        PreparedStatement prep5 = conn.prepareStatement("DELETE FROM FamilyDoctors WHERE FamilyDoctorID = ?;");
        prep5.setInt(1, familyDoctorID);
        prep5.executeUpdate();
    }

    /**
     * This method prints out a list of people in a class that is
     * specified by the user.
     * @param classID the class which's list needs to be printed out
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static ResultSet getClassList(String classID) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        classID = classID.toLowerCase();
        PreparedStatement prep = conn.prepareStatement("SELECT * FROM Students WHERE LOWER(Class) = ?;");
        prep.setString(1, classID);
        return prep.executeQuery();
    }

    /**
     * This method prints out a list of students with a specified
     * special condition.
     * @param condition condition that needs to be queried in the
     *                  database
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static void getConditionList(String condition) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        condition = condition.toLowerCase();
        //Searches Students table for any student with that condition
        PreparedStatement prep = conn.prepareStatement("SELECT Name FROM Students WHERE LOWER(Conditions) LIKE ?;");
        prep.setString(1, "%" + condition + "%");
        ResultSet rs = prep.executeQuery();

        //Prints out list of students
        while (rs.next()) {
            System.out.println(rs.getString("Name"));
        }
    }

    static ResultSet getStudentDetails() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM Students");
    }

    /**
     * This method prints all details for a student's guardian
     * (name, address, phone number, etc.).
     * @param name name of the student whose guardians needs to
     *             be looked up
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static ResultSet getGuardianDetails(String name) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        //Find guardian ID associated with student
        PreparedStatement guardianIDQuery = conn.prepareStatement("SELECT GuardianID FROM Students WHERE Name = ?;");
        guardianIDQuery.setString(1, name);
        ResultSet rs = guardianIDQuery.executeQuery();
        int guardianID = 0;
        while (rs.next()) {
            guardianID = rs.getInt("GuardianID");
        }

        if (guardianID == 0) {
            System.out.println("This student is not in the database.");
        }

        PreparedStatement guardianInfoQuery = conn.prepareStatement("SELECT * FROM Guardians WHERE GuardianID = ?;");
        guardianInfoQuery.setInt(1, guardianID);
        return guardianInfoQuery.executeQuery();
    }

    /**
     * This method prints out details for an emergency contact
     * associated with a student.
     * @param name name of student
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static ResultSet getEmergencyContactDetails(String name) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        PreparedStatement prep1 = conn.prepareStatement("SELECT EmergencyContactID FROM Students WHERE Name = ?;");
        prep1.setString(1, name);
        ResultSet rs = prep1.executeQuery();
        int emergencyContactID = 0;
        while (rs.next()) {
            emergencyContactID = rs.getInt("EmergencyContactID");
        }

        PreparedStatement prep2 = conn.prepareStatement("SELECT * FROM EmergencyContacts WHERE EmergencyContactID = ?;");
        prep2.setInt(1, emergencyContactID);
        return prep2.executeQuery();
    }

    /**
     * This method prints out a student's family doctor's
     * details.
     * @param name name of student
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    static ResultSet getFamilyDoctorDetails(String name) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        PreparedStatement prep1 = conn.prepareStatement("SELECT FamilyDoctorID FROM Students WHERE Name = ?;");
        prep1.setString(1, name);
        ResultSet rs = prep1.executeQuery();
        int familyDoctorID = 0;
        while (rs.next()) {
            familyDoctorID = rs.getInt("FamilyDoctorID");
        }

        //Print out family doctor details
        PreparedStatement prep2 = conn.prepareStatement("SELECT * FROM FamilyDoctors WHERE FamilyDoctorID = ?");
        prep2.setInt(1, familyDoctorID);
        return prep2.executeQuery();
    }
}
