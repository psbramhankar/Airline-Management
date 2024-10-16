package Airlines;


import java.sql.Connection;
import java.util.Scanner;

public class AirlineService {
    private static final Scanner sc = new Scanner(System.in);
    AirlineOperation airlineOperation = new AirlineOperation();
   Authentication auth=new Authentication();

    public void airlineMain(Connection connection) {

//        auth.createAuthenticationTable(connection);

        char op;
        do {

            System.out.println("Enter 1 to create a new Airline table: ");
            System.out.println("Enter 2 for Insert data into a airline: ");
            System.out.println("Enter 3 to update a Pricing table: ");
            System.out.println("Enter 4 to for delete destination: ");

            int choice = sc.nextInt();
            switch (choice) {

                case 1 -> {
                    System.out.println("Enter AirlineName");
                    String airlineName = sc.next();
                    auth.createAirline(connection,airlineName);
                    airlineOperation.createPricingTable(connection, airlineName);
                }

                case 2 -> {
                    //Insert data
                    System.out.println("Enter Airline Name: ");
                    String airlineName = sc.next();
                    System.out.println("Enter PassWord");
                    String pass=sc.next();
                    String getPass=auth.getPassword(connection,airlineName);

                    if(pass.equals(getPass)){
                        System.out.println("Login Successful");
                        airlineOperation.insertData(connection, airlineName);
                    }
                    else{
                        System.out.println("Error: 401.1,  Incorrect password!!Login failed");
                    }
                }
                case 3 -> {
                    //update price
                    System.out.println("Enter Airline Name: ");
                    String airlineName = sc.next();

                    System.out.println("Enter Password");
                    String pass=sc.next();
                    String getPass=auth.getPassword(connection,airlineName);

                    if(pass.equals(getPass)){
                        System.out.println("Login Successful");
                        airlineOperation.updatePrice(connection, airlineName);
                    }
                    else{
                        System.out.println("Error: 401.1,  Incorrect password!!Login failed");
                    }
                }
                case 4 -> {
                    System.out.println("Enter Airline Name: ");
                    String airlineName = sc.next();
                    System.out.println("Enter Password");
                    String pass=sc.next();
                    String getPass=auth.getPassword(connection,airlineName);

                    if(pass.equals(getPass)){
                        System.out.println("Login Successful");
                        airlineOperation.deleteDestination(connection,airlineName);
                    }
                    else{
                        System.out.println("Error: 401.1,  Incorrect password!!Login failed");
                    }
                }

            }
            System.out.println("\n You are in Airline Main class !! Do you want to continue ? ? ?  Y/N");
            while (true) {
                op = sc.next().charAt(0);
                if ((op == 'Y') || (op == 'y') || (op == 'N') || (op == 'n'))
                    break;
                else
                    System.out.println("Invalid Input !! Enter Y for yes or N for no ! ! !");
            }
        } while (op == 'y' || op == 'Y');
    }


}
