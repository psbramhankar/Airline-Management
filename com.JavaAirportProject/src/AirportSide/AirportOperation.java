package AirportSide;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AirportOperation {

    private static double getFirstClassRate(Connection connection, String airlineTable, String sourceCity, String destinationCity) {

        String query = "SELECT firstClass FROM " + airlineTable + " WHERE source = ? AND destination = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sourceCity);
            statement.setString(2, destinationCity);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("firstClass");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0.0;
    }

    private static double getBusinessClassRate(Connection connection, String airlineTable, String sourceCity, String destinationCity) {

        String query = "SELECT businessClass FROM " + airlineTable + " WHERE source = ? AND destination = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sourceCity);
            statement.setString(2, destinationCity);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("businessClass");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }

    private static double getEconomyClassRate(Connection connection, String airlineTable, String sourceCity, String destinationCity) {

        String query = "SELECT economyClass FROM " + airlineTable + " WHERE source = ? AND destination = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sourceCity);
            statement.setString(2, destinationCity);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("economyClass");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }

    public void createFlight(Connection connPort, Connection connLine) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter flight number:");
        String flightNo = sc.next();

        sc.nextLine(); // Consume newline character after nextInt()

        System.out.println("Enter airline:");
        String airline = sc.nextLine();

        System.out.println("Enter travel duration in hours:");
        Double travelDuration = sc.nextDouble();

        sc.nextLine(); // Consume newline character after nextFloat()

        System.out.println("Enter source city:");
        String sourceCity = sc.nextLine();

        System.out.println("Enter destination city:");
        String destinationCity = sc.nextLine();

        System.out.println("Enter Date and Time of flight in (yyyy-MM-dd HH:mm:ss): ");
        String dateTime = sc.nextLine();

        LocalDateTime dateTimeObj;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTimeObj = LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date/time format. Please enter in yyyy-MM-dd HH:mm:ss format.");
            return;
        }
        String formattedDateTime = dateTimeObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        double firstClassFare;
        double economyClassFare;
        double businessClassFare;

        try {
            firstClassFare = getFirstClassRate(connLine, airline, sourceCity, destinationCity);
            businessClassFare = getBusinessClassRate(connLine, airline, sourceCity, destinationCity);
            economyClassFare = getEconomyClassRate(connLine, airline, sourceCity, destinationCity);

            // Proceed with inserting the flight into the database
            Airport airport = new Airport(flightNo, airline, formattedDateTime, travelDuration, sourceCity, destinationCity, firstClassFare, businessClassFare, economyClassFare);
            String sql = "INSERT INTO Flights (flightNo, sourceCity, destinationCity, airline, travelDuration, time, firstClassFare, businessClassFare, economyClassFare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connPort.prepareStatement(sql)) {

                ps.setString(1, airport.getFlightNo());
                ps.setString(2, airport.getSourceCity());
                ps.setString(3, airport.getDestinationCity());
                ps.setString(4, airport.getAirline());
                ps.setDouble(5, airport.getTravelDuration());
                ps.setString(6, airport.getTime());
                ps.setDouble(7, firstClassFare);
                ps.setDouble(8, businessClassFare);
                ps.setDouble(9, economyClassFare);

                ps.executeUpdate();
                System.out.println("Flight created successfully!");
            } catch (SQLException e) {
                System.out.println("Error creating flight: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error fetching rates from the database: " + e.getMessage());
        }
    }

    public void changeFlightTiming(Connection connPort) {
        //Print Flights table because due to this airport admin can enter data properly
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter flight number:");
        String flightNo = sc.next();
        sc.nextLine(); // Consume newline character


        System.out.println("Enter Date and Time of flight in (yyyy-MM-dd HH:mm:ss): ");
        String dateTime = sc.nextLine();

        LocalDateTime dateTimeObj;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTimeObj = LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date/time format. Please enter in yyyy-MM-dd HH:mm:ss format.");
            return;
        }
        String formattedDateTime = dateTimeObj.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Update the flight timing in the database
        String sql = "UPDATE Flights SET time = ? WHERE flightNo = ?";
        try {
            // Create a prepared statement
            PreparedStatement ps = connPort.prepareStatement(sql);
            // Set the parameters
            ps.setString(1, formattedDateTime);
            ps.setString(2, flightNo);
            // Execute the statement
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Flight timing updated successfully!");
            } else {
                System.out.println("Flight not found. Please check the flight details.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating flight timing: " + e.getMessage());
        }
    }


    public void changeFlightsDestination(Connection connPort) {

        //Print Flights table because due to this airport admin can enter data properly

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter flight number:");
        String flightNo = sc.next();
        sc.nextLine(); // Consume newline character


        System.out.println("Enter new Destination of flight " + flightNo);
        String destination = sc.nextLine();

        String sql = "UPDATE Flights SET destinationCity = ? WHERE flightNo = ?";
        try {
            // Create a prepared statement
            PreparedStatement ps = connPort.prepareStatement(sql);
            // Set the parameters
            ps.setString(1, destination);
            ps.setString(2, flightNo);
            // Execute the statement
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Flight " + flightNo + " destination updated successfully!");
            } else {
                System.out.println("Flight not found. Please check the flight details.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating flight timing: " + e.getMessage());
        }
    }

    public void deleteFlight(Connection connPort) {
        // Get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Fetch flights from the database
        String sql = "SELECT flightNo, time FROM Flights";

        try (Statement statement = connPort.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String flightNo = resultSet.getString("flightNo");
                String flightTimeStr = resultSet.getString("time");

                LocalDateTime flightTime = LocalDateTime.parse(flightTimeStr, formatter);

                // Check if the current time is greater than the flight time
                if (currentDateTime.isAfter(flightTime)) {
                    // Flight has departed, delete the corresponding row from the table
                    String deleteSQL = "DELETE FROM Flights WHERE flightNo = ?";
                    try (PreparedStatement deleteStatement = connPort.prepareStatement(deleteSQL)) {
                        deleteStatement.setString(1, flightNo);
                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Flight " + flightNo + " has departed. Deleted from database.");
                        } else {
                            System.out.println("Failed to delete flight " + flightNo);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error deleting flight: " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching flights: " + e.getMessage());
        }
    }

}
