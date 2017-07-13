import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Computer Science IB SL
 * @author Shyam Panchal
 *
 * This class contains all user interactions with this
 * application. It takes user input and performs database
 * operations accordingly.
 */

public class App {

    public static void main(String[] args) {

        try {
            Scanner in = new Scanner(System.in);

            //Initial prompts to user
            System.out.println("What would you like to do? Choose from one of the actions below.");
            System.out.println("Type 'Add' to add a new student.");
            System.out.println("Type 'Class' to get a class list.");
            System.out.println("Type 'Conditions' to get a list of students with a specific special condition (allergies, " +
                    "restrictions, etc.).");
            System.out.println("Type 'Guardian' to get information about a student's guardian(s).");
            System.out.println("Type 'Contact' to get information about a student's emergency contact.");
            System.out.println("Type 'Doctor' to get information about a student's family doctor.");
            System.out.println("Type 'Delete' to delete a student.");
            System.out.println("Type 'Exit' to exit this application.");
            System.out.print("Type in your choice: ");
            String decision = in.next().toLowerCase();
            System.out.println();

            do {
                switch (decision) {
                    //User wants to add student
                    case "add":
                        System.out.print("Enter the student's name: ");
                        in.nextLine();
                        String name = in.nextLine();

                        System.out.print("Enter the student's class: ");
                        String classID = in.nextLine().toLowerCase();

                        System.out.print("Enter the student's birthday in the format MM/DD/YY: ");
                        String birthday = in.nextLine();

                        System.out.print("Enter the number of legal guardians the students has: ");
                        int numGuardians = in.nextInt();
                        ArrayList<Guardian> guardians = new ArrayList<>();
                        for (int i = 0; i < numGuardians; i++) {
                            switch (i + 1) {
                                case 1:
                                    System.out.println("1st Guardian");
                                    System.out.println("------------");
                                    break;

                                case 2:
                                    System.out.println("2nd Guardian");
                                    System.out.println("------------");
                                    break;

                                default:
                                    System.out.println((i + 1) + "th Guardian");
                                    System.out.println("------------");
                            }

                            System.out.print("Enter the guardian's name: ");
                            if (i == 0) {
                                in.nextLine();
                            }
                            String guardianName = in.nextLine();

                            System.out.print("Enter the guardian's address: ");
                            String guardianAddress = in.nextLine();

                            System.out.print("Enter the guardian's occupation: ");
                            String guardianOccupation = in.nextLine();

                            System.out.print("Enter the guardian's phone number: ");
                            String guardianPhoneNum = in.nextLine();

                            System.out.print("Enter the guardian's email: ");
                            String guardianEmail = in.nextLine();

                            guardians.add(new Guardian(guardianName, guardianAddress, guardianOccupation,
                                    guardianPhoneNum, guardianEmail));
                        }

                        System.out.print("Enter the student's emergency contact's name: ");
                        String emergencyContactName = in.nextLine();
                        System.out.print("Enter their phone number: ");
                        String emergencyContactPhoneNum = in.nextLine();
                        EmergencyContact emergencyContact = new EmergencyContact(emergencyContactName,
                                emergencyContactPhoneNum);

                        System.out.print("Enter the student's family doctor's name: ");
                        String familyDoctorName = in.nextLine();
                        System.out.print("Enter their address: ");
                        String familyDoctorAddress = in.nextLine();
                        System.out.print("Enter their phone number: ");
                        String familyDoctorPhoneNum = in.nextLine();
                        FamilyDoctor familyDoctor = new FamilyDoctor(familyDoctorName, familyDoctorAddress,
                                familyDoctorPhoneNum);

                        System.out.print("Enter the student's health card number: ");
                        String healthCardNum = in.next();

                        System.out.print("Enter the student's special conditions in a list, separated by commas: ");
                        in.nextLine();
                        String conditions = in.nextLine();

                        Student student = new Student(name, classID, birthday, guardians, emergencyContact,
                                familyDoctor, healthCardNum, conditions);
                        DatabaseOperations.addStudent(student);
                        System.out.println();
                        break;

                    //User wants a class list
                    case "class":
                        System.out.print("Which class would you like to get the list of? ");
                        in.nextLine();
                        String classToSearch = in.nextLine();
                        System.out.println("Class list for " + classToSearch + ":");
                        DatabaseOperations.getClassList(classToSearch);
                        System.out.println();
                        break;

                    //User wants a list of students with specific conditions
                    case "conditions":
                        System.out.print("Which condition would you like to get the list of? ");
                        in.nextLine();
                        String condition = in.nextLine();
                        System.out.println("List for students with a condition of " + condition + ":");
                        DatabaseOperations.getConditionList(condition);
                        System.out.println();
                        break;

                    //User wants guardian details
                    case "guardian":
                        System.out.print("Enter the student's name whose guardians you would like the information of: ");
                        in.nextLine();
                        String studentName = in.nextLine();
                        DatabaseOperations.getGuardianDetails(studentName);
                        System.out.println();
                        break;

                    //User wants emergency contact details
                    case "contact":
                        System.out.print("Enter the student's name whose emergency contact you would like the information of: ");
                        in.nextLine();
                        studentName = in.nextLine();
                        DatabaseOperations.getEmergencyContactDetails(studentName);
                        System.out.println();
                        break;

                    //User wants family doctor details
                    case "doctor":
                        System.out.print("Enter the student's name whose family doctor you would like the information of: ");
                        in.nextLine();
                        studentName = in.nextLine();
                        DatabaseOperations.getFamilyDoctorDetails(studentName);
                        System.out.println();
                        break;

                    //User wants to delete a student
                    case "delete":
                        System.out.print("Enter the student's name who you would like to delete from the database: ");
                        in.nextLine();
                        String studentToDelete = in.nextLine();
                        DatabaseOperations.deleteStudent(studentToDelete);
                        System.out.println();
                        break;

                    //User wants to exit
                    default:
                        System.out.println("Thank you for using this application.");
                        return;
                }

                //Prompts user what to do next
                System.out.println("What would you like to do next?");
                System.out.println("Type 'Add' to add a new student.");
                System.out.println("Type 'Class' to get a class list.");
                System.out.println("Type 'Conditions' to get a list of students with a specific special condition (allergies, " +
                        "restrictions, etc.).");
                System.out.println("Type 'Guardian' to get information about a student's guardian(s).");
                System.out.println("Type 'Delete' to delete a student.");
                System.out.println("Type 'Exit' to exit this application.");
                System.out.print("Type in your choice: ");
                decision = in.next();
                System.out.println();
            } while (true);
        }
        catch (SQLException e) {
            System.out.println("An SQL error occurred.");
        }
        catch (ClassNotFoundException e) {
            System.out.println("The SQLite JDBC driver could not be found.");
        }
    }
}
