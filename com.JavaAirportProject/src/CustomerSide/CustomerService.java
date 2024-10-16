package CustomerSide;

import AirportSide.PrintFunction;

import java.sql.*;
import java.util.Scanner;

public class CustomerService {
    public void customerMain(Connection connectionCustomer, Connection connectionAirport) {
        CustomerBooking customerBooking=new CustomerBooking();
        Scanner sc = new Scanner(System.in);
        CustomerOperation customerOperation=new CustomerOperation();
        customerOperation.createTable(connectionCustomer);

        System.out.println("Welcome to our Airport site! We're delighted to have you here and hope you find everything you need for your travel plans...!!!");
        System.out.println();
        System.out.println("Choose your route: \n\nFrom: ");
        String source = sc.next();
        System.out.println("To: ");
        String destination = sc.next();
        System.out.println("Book your flight now to travel from "+ source + " to "+destination+ "and experience an amazing journey!");

        char op;

        do {
            System.out.println("Enter 1: Find all flights From " + source + " and " + destination);
            System.out.println("Enter 2: Find cheapest flights ");
            System.out.println("Enter 3: Find Short duration Flight ");
            System.out.println("Enter 4: Find flights according to Airline ");
            PrintFunction printData=new PrintFunction();

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    try {
                        String query = "SELECT * FROM Flights WHERE sourceCity = ? AND destinationCity = ?";

                        PreparedStatement preparedStatement = connectionAirport.prepareStatement(query);
                        preparedStatement.setString(1, source);
                        preparedStatement.setString(2, destination);

                        System.out.println("THESE ARE AVAILABLE FLIGHTS FOR YOU :");
                        printData.printFlights(preparedStatement);

                        System.out.println("Do you want to Book any Flight ?(Y/N):");
                            char ch = sc.next().charAt(0);

                            if ((ch == 'Y') || (ch == 'y')) {
                                customerBooking.bookFlight(connectionCustomer, connectionAirport,source,destination);
                                System.out.println("Thank You for Choosing us,Happy Journey...!!!");

                            }
                            else if(ch == 'N' || ch == 'n') {
                                System.out.println("Thank you for visiting our site! We appreciate your time and hope to see you again soon. !!!");
                            }


                    } catch (Exception e) {
                        System.out.println("Error executing SQL query: " + e.getMessage());
                    }

                }
//Check
                case 2 -> {
                    try {

                        String query = "SELECT * FROM Flights WHERE sourceCity = ? AND destinationCity = ? ORDER BY economyClassFare ASC";
                        PreparedStatement preparedStatement = connectionAirport.prepareStatement(query);
                        preparedStatement.setString(1, source);
                        preparedStatement.setString(2, destination);
                        printData.printFlights(preparedStatement);

                    } catch (SQLException e) {
                        System.out.println("Error executing SQL query: " + e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        String query = "SELECT * FROM Flights WHERE sourceCity = ? AND destinationCity = ? ORDER BY travelDuration ASC";
                        PreparedStatement preparedStatement = connectionAirport.prepareStatement(query);
                        preparedStatement.setString(1, source);
                        preparedStatement.setString(2, destination);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        // Display the flight with the shortest travel duration
                        if (resultSet.next()) {
                            System.out.println("Shortest Duration Flight details: " +
                                    resultSet.getString("flightNo") + ", " +
                                    resultSet.getString("airline") + ", " +
                                    resultSet.getString("time") + ", " +
                                    resultSet.getString("sourceCity") + ", " +
                                    resultSet.getString("destinationCity") + ", " +
                                    resultSet.getFloat("travelDuration"));
                        } else {
                            System.out.println("No flights found for the given source and destination.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error executing SQL query: " + e.getMessage());
                    }
                }

                case 4 -> {
                    System.out.println("Enter the airline name:");
                    String airlineName = sc.next();

                    try {
                        String query = "SELECT * FROM Flights WHERE sourceCity = ? AND destinationCity = ? AND airline = ?";
                        PreparedStatement preparedStatement = connectionAirport.prepareStatement(query);
                        preparedStatement.setString(1, source);
                        preparedStatement.setString(2, destination);
                        preparedStatement.setString(3, airlineName);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        // Display flights of the specified airline
                        if (resultSet.next()) {
                            System.out.println("Flights of " + airlineName + ":");
                            do {
                                System.out.println(
                                        resultSet.getString("flightNo") + ", " +
                                                resultSet.getString("airline") + ", " +
                                                resultSet.getString("time") + ", " +
                                                resultSet.getString("sourceCity") + ", " +
                                                resultSet.getString("destinationCity") + ", " +
                                                resultSet.getFloat("travelDuration")
                                );
                            } while (resultSet.next());
                        } else {
                            System.out.println("No flights found for the given source, destination, and airline.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error executing SQL query: " + e.getMessage());
                    }
                }

            }
            System.out.println("Do you want to continue ? ? ?  Y/N");
            while (true) {
                op = sc.next().charAt(0);
                if ((op == 'Y') || (op == 'y') || (op == 'N') || (op == 'n'))
                    break;
                else
                    System.out.println("Enter Y for yes or N for no ! ! !");
            }
        } while (op == 'y' || op == 'Y');

    }

}
