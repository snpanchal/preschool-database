import java.sql.*;

/**
 * Copyright Little Flowers Daycare & Preschool 2017
 * @author Shyam Panchal
 *
 * This class contains methods for all operations and interactions
 * with the database. The library SQLite is used to create and
 * manage tables in the database.
 */
class DatabaseOperations {

    private static Connection conn;//Connection to the database

    /**
     * This method establishes a connection with the database file
     * using drivers built specifically for SQLite.
     * @throws SQLException if a database access error occurs or the
     * url is null
     * @throws ClassNotFoundException if the JDBC SQLite class cannot
     * be located
     */
    private static void getConnection() throws SQLException, ClassNotFoundException {
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
    private static void buildTables() throws SQLException, ClassNotFoundException {
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
        prep1.setString(1, student.guardians.get(0).name);
        prep1.setString(2, student.guardians.get(0).address);
        prep1.setString(3, student.guardians.get(0).occupation);
        prep1.setString(4, student.guardians.get(0).phoneNum);
        prep1.setString(5, student.guardians.get(0).email);
        if (student.guardians.size() > 1) {
            prep1.setString(6, student.guardians.get(1).name);
            prep1.setString(7, student.guardians.get(1).address);
            prep1.setString(8, student.guardians.get(1).occupation);
            prep1.setString(9, student.guardians.get(1).phoneNum);
            prep1.setString(10, student.guardians.get(1).email);
        }
        prep1.executeUpdate();

        //Fill information into EmergencyContacts table
        PreparedStatement prep2 = conn.prepareStatement("INSERT INTO EmergencyContacts(Name, PhoneNum) VALUES(?, ?);");
        prep2.setString(1, student.emergencyContact.name);
        prep2.setString(2, student.emergencyContact.phoneNum);
        prep2.executeUpdate();

        //Fill information into FamilyDoctors table
        PreparedStatement prep3 = conn.prepareStatement("INSERT INTO FamilyDoctors(Name, Address, PhoneNum) VALUES (?, ?, ?);");
        prep3.setString(1, student.familyDoctor.name);
        prep3.setString(2, student.familyDoctor.address);
        prep3.setString(3, student.familyDoctor.phoneNum);
        prep3.executeUpdate();

        //Get GuardianID to connect to Students table as a foreign key
        PreparedStatement prep4 = conn.prepareStatement("SELECT GuardianID FROM Guardians WHERE Name1 = ? OR Name2 = ?;");
        prep4.setString(1, student.guardians.get(0).name);
        //If more than one guardian
        if (student.guardians.size() > 1) {
            prep4.setString(2, student.guardians.get(1).name);
        }
        int guardianID = 0;
        ResultSet rs1 = prep4.executeQuery();
        while (rs1.next()) {
            guardianID = rs1.getInt("GuardianID");
        }

        //Get EmergencyContactID to connect to Students table as a foreign key
        PreparedStatement prep5 = conn.prepareStatement("SELECT EmergencyContactID FROM EmergencyContacts WHERE Name = ?;");
        prep5.setString(1, student.emergencyContact.name);
        ResultSet rs2 = prep5.executeQuery();
        int emergencyContactID = 0;
        while (rs2.next()) {
            emergencyContactID = rs2.getInt("EmergencyContactID");
        }

        //Get FamilyDoctorID to connect to Students table as a foreign key
        PreparedStatement prep6 = conn.prepareStatement("SELECT FamilyDoctorID FROM FamilyDoctors WHERE Name = ?;");
        prep6.setString(1, student.familyDoctor.name);
        ResultSet rs3 = prep6.executeQuery();
        int familyDoctorID = 0;
        while (rs3.next()) {
            familyDoctorID = rs3.getInt("FamilyDoctorID");
        }

        //Fill in all information into Students table
        PreparedStatement prep7 = conn.prepareStatement("INSERT INTO Students(Name, Class, Birthday, GuardianID, EmergencyContactID, " +
                "FamilyDoctorID, HealthCardNum, Conditions) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
        prep7.setString(1, student.name);
        prep7.setString(2, student.classID);
        prep7.setString(3, student.birthday);
        prep7.setInt(4, guardianID);
        prep7.setInt(5, emergencyContactID);
        prep7.setInt(6, familyDoctorID);
        prep7.setString(7, student.healthCardNum);
        prep7.setString(8, student.conditions);
        prep7.executeUpdate();

        System.out.println("The student has been added.");
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

        System.out.println("The student has been deleted from the database.");
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
    static void getClassList(String classID) throws SQLException, ClassNotFoundException {
        if (conn == null) {
            getConnection();
        }

        classID = classID.toLowerCase();
        PreparedStatement prep = conn.prepareStatement("SELECT Name FROM Students WHERE LOWER(Class) = ?;");
        prep.setString(1, classID);
        ResultSet rs = prep.executeQuery();

        //Prints out all names
        while (rs.next()) {
            System.out.println(rs.getString("Name"));
        }
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
    static void getGuardianDetails(String name) throws SQLException, ClassNotFoundException {
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
        ResultSet guardianInfo = guardianInfoQuery.executeQuery();

        //Print guardian information
        while (guardianInfo.next()) {
            System.out.println("Guardian 1");
            System.out.println("----------");
            System.out.println("Name: " + guardianInfo.getString("Name1"));
            System.out.println("Address: " + guardianInfo.getString("Address1"));
            System.out.println("Occupation: " + guardianInfo.getString("Occupation1"));
            System.out.println("Phone Number: " + guardianInfo.getString("PhoneNum1"));
            System.out.println("Email: " + guardianInfo.getString("Email1"));
            if (guardianInfo.getString("Name2") != null) {
                System.out.println();
                System.out.println("Guardian 2");
                System.out.println("----------");
                System.out.println("Name: " + guardianInfo.getString("Name2"));
                System.out.println("Address: " + guardianInfo.getString("Address2"));
                System.out.println("Occupation: " + guardianInfo.getString("Occupation2"));
                System.out.println("Phone Number: " + guardianInfo.getString("PhoneNum2"));
                System.out.println("Email: " + guardianInfo.getString("Email2"));
            }
        }
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
    static void getEmergencyContactDetails(String name) throws SQLException, ClassNotFoundException {
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
        ResultSet emergencyContactInfo = prep2.executeQuery();

        //Print out emergency contact details
        while(rs.next()) {
            System.out.println("Emergency Contact");
            System.out.println("-----------------");
            System.out.println("Name: " + emergencyContactInfo.getString("Name"));
            System.out.println("Phone Number: " + emergencyContactInfo.getString("PhoneNum"));
        }
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
    static void getFamilyDoctorDetails(String name) throws SQLException, ClassNotFoundException {
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
        ResultSet familyDoctorInfo = prep2.executeQuery();
        while (familyDoctorInfo.next()) {
            System.out.println("Family Doctor");
            System.out.println("-------------");
            System.out.println("Name: " + familyDoctorInfo.getString("Name"));
            System.out.println("Address: " + familyDoctorInfo.getString("Address"));
            System.out.println("Phone Number: " + familyDoctorInfo.getString("PhoneNum"));
        }
    }
}
