package Airlines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Authentication {
    static PreparedStatement prepStat;
    Scanner sc=new Scanner(System.in);
    public void createAuthenticationTable(Connection conn) {
        try {
            prepStat = conn.prepareStatement("CREATE TABLE authentication (Sr SERIAL, airlineName VARCHAR(50) PRIMARY KEY, passWord VARCHAR(50))");
            prepStat.executeUpdate();
            System.out.println("Authentication Table is created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    String getPassword(Connection conn,String airlineName){
        String pass=null;
        try {
            prepStat = conn.prepareStatement("SELECT PassWord FROM authentication WHERE airlineName = ?");
            prepStat.setString(1,airlineName);
            ResultSet resultSet = prepStat.executeQuery();

            if (resultSet.next()) {
                 pass = resultSet.getString("passWord");
            } else {
                System.out.println("Invalid Username..!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pass;
    }

    public void createAirline(Connection conn,String airlineName) {
        try {
            prepStat = conn.prepareStatement("insert into authentication(airlineName,passWord) values(?, ?)");

            prepStat.setString(1, airlineName);
            System.out.println("Enter Password");
            String pass = sc.next();
            prepStat.setString(2, pass);
            prepStat.executeUpdate();
            System.out.println("Airline Company registered");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
