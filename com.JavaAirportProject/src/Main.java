import Airlines.AirlineService;
import AirportSide.AirportService;
import CustomerSide.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException, SQLException {

        Properties prop = new Properties();
        prop.load(new FileInputStream("connection.properties"));

        Connection connectionAirline = DriverManager.getConnection(prop.getProperty("url1"), prop.getProperty("username1"), prop.getProperty("password1"));
        Connection connectionAirport = DriverManager.getConnection(prop.getProperty("url2"), prop.getProperty("username2"), prop.getProperty("password2"));
        Connection connectionCustomer = DriverManager.getConnection(prop.getProperty("url3"), prop.getProperty("username3"), prop.getProperty("password3"));

        Scanner sc = new Scanner(System.in);
        System.out.println("SELECT YOUR ROLL ?: ");
        System.out.println("Enter 1 FOR Airline Admin: ");
        System.out.println("Enter 2 FOR Airport authority: ");
        System.out.println("Enter 3 FOR customer: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> {
                //Airline company
                AirlineService obj1 = new AirlineService();
                obj1.airlineMain(connectionAirline);
            }
            case 2 -> {
                //Airport authority
                AirportService obj2 = new AirportService();
                obj2.operations(connectionAirport, connectionAirline,connectionCustomer);

            }
            case 3 -> {
                //customer
                CustomerService obj3=new CustomerService();
                obj3.customerMain(connectionCustomer,connectionAirport);
            }

        }

    }
}