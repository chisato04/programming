import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseManager {
    private String databaseUrl;
    private String username;
    private String password;

    public StudentDatabaseManager(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public StudentDatabaseManager() {
        // Default constructor
        this.databaseUrl = "jdbc:mysql://localhost:3306/codechum1";
        this.username = "root";
        this.password = "";
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }
    public boolean registerStudent(String lastName, String firstName, String address, double tuitionFee) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO student (last_name, first_name, address, tuition_fee) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setString(3, address);
            statement.setDouble(4, tuitionFee);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStudent(int id, String lastName, String firstName, String address, double tuitionFee) {
        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE student SET last_name = ?, first_name = ?, address = ?, tuition_fee = ? WHERE id = ?")) {
            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setString(3, address);
            statement.setDouble(4, tuitionFee);
            statement.setInt(5, id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Choose what to do: ");
            System.out.println("1.) Register Student");
            System.out.println("2.) Update Student");
            System.out.println("0.) Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Student Details");
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Tuition Fee: ");
                    double tuitionFee = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Proceed to save? (Y/n) ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        if (registerStudent(lastName, firstName, address, tuitionFee)) {
                            System.out.println("Successfully saved!");
                        } else {
                            System.out.println("Something went wrong!");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Update Student Details");
                    System.out.print("Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Last Name: ");
                    lastName = scanner.nextLine();
                    System.out.print("First Name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Address: ");
                    address = scanner.nextLine();
                    System.out.print("Tuition Fee: ");
                    tuitionFee = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Proceed to save? (Y/n) ");
                    confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        if (updateStudent(id, lastName, firstName, address, tuitionFee)) {
                            System.out.println("Successfully updated!");
                        } else {
                            System.out.println("Something went wrong!");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        StudentDatabaseManager manager = new StudentDatabaseManager();
        manager.startMenu();
    }
}
