import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Account {
    private int id;
    private String name;
    private double savings;
    private Date datetimeUpdated;

    public Account(int id, String name, double savings, Date datetimeUpdated) {
        this.id = id;
        this.name = name;
        this.savings = savings;
        this.datetimeUpdated = datetimeUpdated;
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

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public Date getDatetimeUpdated() {
        return datetimeUpdated;
    }

    public void setDatetimeUpdated(Date datetimeUpdated) {
        this.datetimeUpdated = datetimeUpdated;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String formattedDate = datetimeUpdated != null ? sdf.format(datetimeUpdated) : "Never";
        return "Account ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Savings: " + savings + "\n" +
               "Date-time Updated: " + formattedDate;
    }
}

class AccountDatabaseManager {
    private String databaseUrl;
    private String username;
    private String password;

    public AccountDatabaseManager(String databaseUrl, String username, String password) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public boolean registerAccount(String name) {
        String sql = "INSERT INTO account (name) VALUES (?)";

        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account retrieveAccount(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";

        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int accountId = rs.getInt("id");
                String name = rs.getString("name");
                double savings = rs.getDouble("savings");
                Date datetimeUpdated = rs.getDate("datetime_updated");

                return new Account(accountId, name, savings, datetimeUpdated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean withdraw(int id, double amount) {
        String sql = "UPDATE account SET savings = savings - ?, datetime_updated = ? WHERE id = ?";

        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deposit(int id, double amount) {
        String sql = "UPDATE account SET savings = savings + ?, datetime_updated = ? WHERE id = ?";

        try (Connection conn = getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

public class AccountManagementApp {
    public static void main(String[] args) {

        AccountDatabaseManager dbManager = null;
        try {
            dbManager = new AccountDatabaseManager("jdbc:sqlite:accounts.db", "", "");
        } catch (Exception e) {
            System.err.println("Error initializing database manager: " + e.getMessage());
            return;
        }


        try (Connection conn = dbManager.getDatabaseConnection();
             Statement stmt = conn.createStatement()) {
            String createTableSql =
                "CREATE TABLE IF NOT EXISTS account (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(255)," +
                "savings REAL DEFAULT 5000.00," +
                "datetime_updated DATETIME DEFAULT NULL" +
                ")";
            stmt.execute(createTableSql); // Create the table if it doesn't exist
        } catch (SQLException e) {
            System.err.println("Error creating or connecting to database: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose from the menu:");
            System.out.println("1.) Create account");
            System.out.println("2.) Open account");
            System.out.println("0.) Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create a new account
                    System.out.print("Enter Account Details\nName: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    boolean success = dbManager.registerAccount(name);
                    if (success) {
                        System.out.println("Successfully registered!");
                    } else {
                        System.out.println("Error registering account.");
                    }
                    break;

                case 2:
                    System.out.print("Enter account ID: ");
                    int id = scanner.nextInt();

                    Account account = dbManager.retrieveAccount(id);

                    if (account == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    boolean openAccountMenu = true;

                    while (openAccountMenu) {
                        System.out.println("Choose from the menu:");
                        System.out.println("1.) Check Account");
                        System.out.println("2.) Deposit");
                        System.out.println("3.) Withdraw");
                        System.out.println("0.) Exit");
                        System.out.print("Choice: ");
                        int subChoice = scanner.nextInt();

                        switch (subChoice) {
                            case 1:
                                System.out.println(account.toString());
                                break;

                            case 2:
                                System.out.print("Amount: ");
                                double depositAmount = scanner.nextDouble();
                                boolean depositSuccess = dbManager.deposit(id, depositAmount);
                                if (depositSuccess) {
                                    System.out.println("Deposit successful.");
                                    account = dbManager.retrieveAccount(id);
                                } else {
                                    System.out.println("Deposit failed.");
                                }
                                break;

                            case 3:

                                System.out.print("Amount: ");
                                double withdrawAmount = scanner.nextDouble();
                                boolean withdrawSuccess = dbManager.withdraw(id, withdrawAmount);
                                if (withdrawSuccess) {
                                    System.out.println("Withdrawal successful.");
                                    account = dbManager.retrieveAccount(id);
                                } else {
                                    System.out.println("Withdrawal failed.");
                                }
                                break;

                            case 0:
                                openAccountMenu = false;
                                break;

                            default:
                                System.out.println("Invalid choice. Try again.");
                                break;
                        }
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("Exited");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}