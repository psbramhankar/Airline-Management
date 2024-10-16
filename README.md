# Airline-Management

 The project is a Java application designed to manage airline, airport, and customer 
operations. It consists of several packages and classes organized to handle distinct 
functionalities: 

## 1. Airlines Package: 
• AirlineService: Manages airline related operations.
• AirlineOperation: Handles airline operations like creating tables, inserting flight details, 
updating flight timings, and deleting flights. 
• Airline: Represents airline details. 

## 2. AirportSide Package: 
•  AirportService: Governs airport authority operations. 

•  AirportOperation: Controls airport operations such as creating tables, adding flights, 
changing flight timings, altering flight destinations, and deleting flights. 

•  PrintFunction: Provides methods to print flight details obtained from a ResultSet. 

##3. CustomerSide Package: 
•  CustomerService: Manages customer interactions and flight bookings. 
•  CustomerBooking: Facilitates flight booking for customers based on different class 
types. 
•  CustomerOperation: Handles customer operations like table creation, customer data 
insertion, and displaying customer details. 
•  Customer: Represents customer information. 

4. Main Class: 
•  Main: Acts as the handler class that orchestrates the application. 
•  Loads connection properties from `connection.properties` for database connections 
to airlines, airports, and customers. 
•  Based on user input, instantiates the appropriate service class for airline admin, airport 
authority, or customer roles and executes corresponding functionalities.
 
Functionality Highlights: 
Airline Operations: Allows airline admins to manage flights, modify flight details, and perform 
database operations related to airlines. 
Airport Authority Operations: Provides functionalities for airport authorities to handle flights, 
such as managing flight details, changing timings, and dealing with customer bookings. 
Customer Interactions: Enables customers to search for available flights, select specific 
classes, book flights, and view flight details. Additionally, allows customers to interact with the 
database to retrieve their booking information. 
Technologies Used: 
Java Programming Language: Utilized for application development. 
PostgreSQL Database: Utilized for storing airline, airport, and customer data. 
SQL Queries: Employed for database operations such as creating tables, inserting data, and 
retrieving information. 
Scanner Class: Used for user input throughout the application. 
This application provides a comprehensive system for managing airline, airport, and customer 
operations, allowing various users to perform specific actions based on their roles. 
Steps to Run Project:  
• Load Project files on IntelliJ idea 
• Load PostgreSQL server 
• Open PgAdmin 4 
• Open Main Class which is src folder 
• Click on Run  
• Perform the Operation 
