import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Pet {
    private int id;
    private String name;
    private String type;
    private boolean isVaccinated;
    private java.sql.Date dateVaccinated;

    public Pet(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isVaccinated = false;
        this.dateVaccinated = null;
    }

    public Pet(int id, String name, String type, boolean isVaccinated, java.sql.Date dateVaccinated) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isVaccinated = isVaccinated;
        this.dateVaccinated = dateVaccinated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public java.sql.Date getDateVaccinated() {
        return dateVaccinated;
    }

    public void setDateVaccinated(java.sql.Date dateVaccinated) {
        this.dateVaccinated = dateVaccinated;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        if (isVaccinated) {
            return "Pet ID: " + id + "\nName: " + name + "\nType: " + type + "\nDate-time Vaccinated: " + dateFormat.format(dateVaccinated);
        } else {
            return "Pet ID: " + id + "\nName: " + name + "\nType: " + type + "\nNot vaccinated.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabaseManager databaseManager = new PetDatabaseManager("jdbc:mysql://localhost:3306/pets", "root", "");

        while (true) {
            System.out.println("Choose what to do:");
            System.out.println("1.) Register Pet");
            System.out.println("2.) Retrieve Pet");
            System.out.println("3.) Vaccinate Pet");
            System.out.println("0.) Exit");

            System.out.print("Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Pet Details");
                    scanner.nextLine(); // Consume newline
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    boolean registered = databaseManager.registerPet(name, type);
                    if (registered) {
                        System.out.println("Successfully saved!");
                    } else {
                        System.out.println("Something went wrong!");
                    }
                    break;
                case 2:
                    System.out.print("Enter Pet ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Retrieve pet? (Y/n): ");
                    char retrieveChoice = scanner.nextLine().charAt(0);
                    if (retrieveChoice == 'Y' || retrieveChoice == 'y') {
                        Pet pet = databaseManager.retrievePet(id);
                        if (pet != null) {
                            System.out.println("Pet Details");
                            System.out.println(pet);
                        } else {
                            System.out.println("Pet not found!");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Pet ID to vaccinate: ");
                    int idToVaccinate = scanner.nextInt();
                    boolean vaccinated = databaseManager.vaccinatePet(idToVaccinate);
                    if (vaccinated) {
                        System.out.println("The pet has been vaccinated!");
                    } else {
                        System.out.println("Failed to vaccinate the pet!");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

class PetDatabaseManager {
    private String databaseUrl;
    private String username;
    private String password;

    public PetDatabaseManager(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerPet(String name, String type) {
        try (Connection connection = getDatabaseConnection()) {
            String query = "INSERT INTO pet (name, type) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, type);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pet retrievePet(int id) {
        try (Connection connection = getDatabaseConnection()) {
            String query = "SELECT * FROM pet WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                boolean isVaccinated = resultSet.getBoolean("is_vaccinated");
                java.sql.Date dateVaccinated = resultSet.getDate("date_vaccinated");
                return new Pet(id, name, type, isVaccinated, dateVaccinated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean vaccinatePet(int id) {
        try (Connection connection = getDatabaseConnection()) {
            String query = "UPDATE pet SET is_vaccinated = ?, date_vaccinated = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
