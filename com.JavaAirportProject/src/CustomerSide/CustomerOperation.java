package CustomerSide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class CustomerOperation {
    Scanner sc=new Scanner(System.in);
    public void createTable(Connection connection) {
        try {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS CustomerDetail (" +
                    "id serial PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "gender CHAR(1) NOT NULL," +
                    "dob DATE NOT NULL," +
                    "age INT NOT NULL," +
                    "phoneNumber VARCHAR(20) NOT NULL," +
                    "travellingFrom VARCHAR(100) NOT NULL," +
                    "travellingTo VARCHAR(100) NOT NULL," +
                    "preferredClass VARCHAR(50) NOT NULL" +
                    ")";
            PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
            System.out.println("CustomerDetail table created successfully!");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    static private int calculateAge(String dob) {
        LocalDate birthDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
    // Method to insert customer data into the CustomerDetail table using Customer POJO class
    public int createCustomer(Connection connection, String flightNo, String classType, String source,String destination) {
        int customerId=0;
        try {
            String insertQuery = "INSERT INTO CustomerDetail (name, gender, dob, age, phoneNumber, travellingFrom, travellingTo, preferredClass) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            // Collect customer information from user input
            System.out.println("Enter Customer Name:");
            String name = sc.nextLine();

            System.out.println("Enter Customer Gender (M/F):");
            char gender = sc.next().charAt(0);

            System.out.println("Enter Customer Date of Birth (YYYY-MM-DD):");
            String dobString = sc.next();
            // Convert String to java.sql.Date
            java.sql.Date dob = java.sql.Date.valueOf(dobString);
            // Calculate age based on date of birth
            int age = calculateAge(dobString);

            System.out.println("Enter Customer Phone Number:");
            String phoneNumber = sc.next();

            // Set values in the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, String.valueOf(gender));
            preparedStatement.setDate(3, dob);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, source);
            preparedStatement.setString(7, destination);
            preparedStatement.setString(8, classType);

            // Execute the SQL insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer data added to the database successfully!");

                // Retrieve the generated keys (auto-generated ID)
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customerId = generatedKeys.getInt(1);

                    System.out.println("Generated Customer ID: " + customerId);
                    // Use customerId as needed (e.g., store it in a variable or perform further operations)
                } else {
                    System.out.println("Failed to retrieve the generated ID for the customer.");
                }
            } else {
                System.out.println("Failed to add customer data to the database.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting data into the database: " + e.getMessage());
        }
        return customerId;
    }

    public void customerDetailsAirPort(Connection connection) {
        try {
            String selectQuery = "SELECT * FROM CustomerDetail";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                char gender = resultSet.getString("gender").charAt(0);
                String dob = resultSet.getString("dob");
                int age = resultSet.getInt("age");
                String phoneNumber = resultSet.getString("phoneNumber");
                String travellingFrom = resultSet.getString("travellingFrom");
                String travellingTo = resultSet.getString("travellingTo");
                String preferredClass = resultSet.getString("preferredClass");

                System.out.println("Customer ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Gender: " + gender);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Age: " + age);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Travelling From: " + travellingFrom);
                System.out.println("Travelling To: " + travellingTo);
                System.out.println("Preferred Class: " + preferredClass);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error fetching customer details: " + e.getMessage());
        }
    }

    public void ticketGenerator(PreparedStatement preparedStatement, double fare) {
        try {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                char gender = resultSet.getString("gender").charAt(0);
                String dob = resultSet.getString("dob");
                int age = resultSet.getInt("age");
                String phoneNumber = resultSet.getString("phoneNumber");
                String travellingFrom = resultSet.getString("travellingFrom");
                String travellingTo = resultSet.getString("travellingTo");
                String preferredClass = resultSet.getString("preferredClass");

                System.out.println("Customer ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Gender: " + gender);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Age: " + age);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Travelling From: " + travellingFrom);
                System.out.println("Travelling To: " + travellingTo);
                System.out.println("Preferred Class: " + preferredClass);
                System.out.println("Fare: "+fare);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error fetching customer details: " + e.getMessage());
        }
    }



}
