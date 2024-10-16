package AirportSide;

import CustomerSide.CustomerOperation;

import java.sql.*;
import java.util.Scanner;

public class AirportService {
    public Scanner sc = new Scanner(System.in);
    AirportOperation obj=new AirportOperation();
    public void createTable(Connection conn, String tableName) {
        Statement statement;
        try {
            String query = "CREATE TABLE " + tableName + " (flightNo VARCHAR(10) NOT NULL, " +
                    "sourceCity VARCHAR(255) NOT NULL, destinationCity VARCHAR(255) NOT NULL, " +
                    "airline VARCHAR(255) NOT NULL, travelDuration DECIMAL(10,2) NOT NULL, " +
                    "time VARCHAR(255) NOT NULL, firstClassFare DECIMAL(10,2) NOT NULL, " +
                    "businessClassFare DECIMAL(10,2) NOT NULL, economyClassFare DECIMAL(10,2) NOT NULL, " +
                    "PRIMARY KEY (flightNo))";

            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table " + tableName + " is created.");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void operations(Connection connPort, Connection connLine,Connection ConnCustomer) {

        AirportService obj1 = new AirportService();
//          obj1.createTable(connPort, "Flights");
        char op;
        do {

            System.out.println("Enter 1 for Create a flight:");
            System.out.println("Enter 2 for change flight timing:");
            System.out.println("Enter 3 for change flight's Destination:");
            System.out.println("Enter 4 for customer's details list");
            System.out.println("Enter 5 for Delete flights: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    obj.createFlight(connPort, connLine);
                }
                case 2 -> {
                    //change flight timing
                    try {
                        obj.changeFlightTiming(connPort);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    //change flight's destination
                    obj.changeFlightsDestination(connPort);
                }
                case 4 -> {
                    System.out.println("complete customer's details:");

                    CustomerOperation object=new CustomerOperation();
                    object.customerDetailsAirPort(ConnCustomer);
                }
                case 5 -> {
                    obj.deleteFlight(connPort);
                }

            }
            System.out.println("You are in Airport Main class, Do you want to continue ? ? ?  Y/N");
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
