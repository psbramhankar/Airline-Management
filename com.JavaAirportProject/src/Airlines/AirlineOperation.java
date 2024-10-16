package Airlines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AirlineOperation {
    Scanner sc=new Scanner(System.in);
    public PreparedStatement prepStat;
    public void createPricingTable(Connection conn, String tableName) {
        try {
            PreparedStatement prepStat=conn.prepareStatement("CREATE TABLE " + tableName +
                    " (Sr SERIAL, source VARCHAR(50), destination VARCHAR(50), firstClass DOUBLE PRECISION, " +
                    "businessClass DOUBLE PRECISION, economyClass DOUBLE PRECISION, " +
                    "PRIMARY KEY(source, destination));");

            prepStat.executeUpdate();
            System.out.println("Airline Rate Table is created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertData(Connection conn, String airlineName) {
        char ch;

        do {
            try {
                prepStat = conn.prepareStatement("insert into " + airlineName + "(source, destination, firstClass,businessClass,economyClass) values(?, ?, ?, ?, ?);");

                System.out.println("Enter source ");
                String source = sc.next();
                prepStat.setString(1, source);

                System.out.println("Enter destination ");
                String dest = sc.next();
                prepStat.setString(2, dest);

                System.out.println("Enter First class Rate: ");
                double fc = sc.nextDouble();
                prepStat.setDouble(3, fc);

                System.out.println("Enter business class Rate: ");
                double bc = sc.nextDouble();
                prepStat.setDouble(4, bc);

                System.out.println("Enter name of economy class Rate: ");
                double ec = sc.nextDouble();
                prepStat.setDouble(5, ec);

                prepStat.executeUpdate();

                System.out.println("Route is created..!!");
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
            System.out.println("Enter Y for continue else Enter N for exit");
            ch = sc.next().charAt((0));

        } while (ch == 'y' || ch == 'Y');
    }

    public void updatePrice(Connection conn, String tableName) {
        try {
            prepStat = conn.prepareStatement("UPDATE " + tableName + " SET firstClass =?, businessClass =?, economyClass =? WHERE source= ? AND destination =?;");

            System.out.println("Enter source:");
            prepStat.setString(4, sc.next());

            System.out.println("Enter destination ");
            prepStat.setString(5, sc.next());

            System.out.println("Enter First class Rate: ");
            prepStat.setDouble(1, sc.nextInt());

            System.out.println("Enter business class Rate: ");
            prepStat.setDouble(2, sc.nextInt());

            System.out.println("Enter economy class Rate: ");
            prepStat.setDouble(3, sc.nextInt());

            prepStat.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void deleteDestination(Connection conn, String airline) {
        try {
            prepStat = conn.prepareStatement("DELETE FROM " + airline + " WHERE source =? AND destination =?");

            System.out.println("Enter Source: ");
            String source = sc.next();
            prepStat.setString(1, source);

            System.out.println("Enter Destination");
            String destination = sc.next();
            prepStat.setString(2, destination);
            prepStat.executeUpdate();
            System.out.println("All fights from " + source + " To " + destination + " are cancelled");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
