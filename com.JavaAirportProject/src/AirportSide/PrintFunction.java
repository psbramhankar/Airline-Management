package AirportSide;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrintFunction {
    public void printFlights(PreparedStatement preparedStatement) {
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            // Process the resultSet to display flight details using the toString() method
            while (resultSet.next()) {
                // Retrieve flight details from resultSet and create an Airport object
                Airport airport = new Airport(
                        resultSet.getString("flightNo"),
                        resultSet.getString("airline"),
                        resultSet.getString("time"),
                        resultSet.getDouble("travelDuration"),
                        resultSet.getString("sourceCity"),
                        resultSet.getString("destinationCity"),
                        resultSet.getDouble("firstClassFare"),
                        resultSet.getDouble("businessClassFare"),
                        resultSet.getDouble("economyClassFare")
                );
                String objString = airport.toString();
                // Display flight details using the toString() method of the Airport class
                System.out.println("Flight details: " + objString);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
