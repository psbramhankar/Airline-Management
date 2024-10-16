package CustomerSide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//import static CustomerSide.CustomerOperation.ticketGenerator;

public class CustomerBooking {
     void bookSpecificClass(Connection connectionCustomer, Connection connectionAirport, String flightNo, String classType, String source, String destination ) {
             CustomerOperation custOperation=new CustomerOperation();
         try {
            double fare = 0.0; // Initialize the fare variable

            switch (classType) {
                case "First"-> {
                    int cust_id=custOperation.createCustomer(connectionCustomer,flightNo,classType, source,destination);

                    String selectQuery = "SELECT firstClassFare FROM Flights WHERE flightNo=?";
                    PreparedStatement ps = connectionAirport.prepareStatement(selectQuery);
                    ps.setString(1, flightNo);
                    ResultSet resultSet = ps.executeQuery();

                    if (resultSet.next()) {
                        fare = resultSet.getDouble("firstClassFare");
                    } else {
                        System.out.println("No fare found for the specified flight and class type.");
                    }

                    String selectAll="SELECT * FROM  CustomerDetail WHERE id=?";
                    PreparedStatement p = connectionCustomer.prepareStatement(selectAll);
                    p.setInt(1,cust_id);
                    custOperation.ticketGenerator(p,fare);

                }
                case "Business" -> {
                    int cust_id=custOperation.createCustomer(connectionCustomer,flightNo,classType, source,destination);

                    String selectQuery = "SELECT businessClassFare FROM Flights WHERE flightNo=?";
                    PreparedStatement ps = connectionAirport.prepareStatement(selectQuery);
                    ps.setString(1, flightNo);
                    ResultSet resultSet = ps.executeQuery();

                    if (resultSet.next()) {
                        fare = resultSet.getDouble("businessClassFare");
                    } else {
                        System.out.println("No fare found for the specified flight and class type.");
                    }

                    String selectAll="SELECT * FROM  CustomerDetail WHERE id=?";
                    PreparedStatement p = connectionCustomer.prepareStatement(selectAll);
                    p.setInt(1,cust_id);
                    custOperation.ticketGenerator(p,fare);
                }
                case "Economy" -> {
                    int cust_id=custOperation.createCustomer(connectionCustomer,flightNo,classType, source,destination);

                    String selectQuery = "SELECT economyClassFare FROM Flights WHERE flightNo=?";
                    PreparedStatement ps = connectionAirport.prepareStatement(selectQuery);
                    ps.setString(1, flightNo);
                    ResultSet resultSet = ps.executeQuery();

                    if (resultSet.next()) {
                        fare = resultSet.getDouble("economyClassFare");
                    } else {
                        System.out.println("No fare found for the specified flight and class type.");
                    }

                    String selectAll="SELECT * FROM  CustomerDetail WHERE id=?";
                    PreparedStatement p = connectionCustomer.prepareStatement(selectAll);
                    p.setInt(1,cust_id);
                    custOperation.ticketGenerator(p,fare);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void bookFlight(Connection connectionCustomer, Connection connectionAirport,String source,String destination){
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter flight number which you want to Book: ");
        String flightNo = sc.next();
        System.out.println("Which class you Prefer: \nEnter 1 for First Class\nEnter 2 for Business Class \nEnter 3 for Economy class");
        try {
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    bookSpecificClass(connectionCustomer,connectionAirport, flightNo,"First", source, destination);
                    break;
                case 2:
                    bookSpecificClass(connectionCustomer,connectionAirport, flightNo,"Business", source, destination);
                    break;
                case 3:
                    bookSpecificClass(connectionCustomer,connectionAirport, flightNo,"Economy", source, destination);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error booking the flight: " + e.getMessage());
        }
    }

}

