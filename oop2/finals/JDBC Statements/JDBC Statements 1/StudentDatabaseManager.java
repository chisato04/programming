import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerStudent(String lastName, String firstName, String address, double tuitionFee) {
        try (Connection connection = getDatabaseConnection()) {
            String sql = "INSERT INTO student (last_name, first_name, address, tuition_fee) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, lastName);
                statement.setString(2, firstName);
                statement.setString(3, address);
                statement.setDouble(4, tuitionFee);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Successfully saved!");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Something went wrong!");
        return false;
    }

    public static void main(String[] args) {
        StudentDatabaseManager manager = new StudentDatabaseManager("jdbc:mysql://localhost:3306/codechum1", "root", "");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Student Details");
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Tuition Fee: ");
        double tuitionFee = scanner.nextDouble();

        System.out.print("Proceed to save? (Y/N) ");
        String proceed = scanner.next();

        if (proceed.equalsIgnoreCase("Y")) {
            // Register the student
            manager.registerStudent(lastName, firstName, address, tuitionFee);
        } else {
            System.out.println("Operation aborted.");
        }
    }
}
