/**
   The BitsPleaseDB class consists of the methods that creates, or drops ,or populates
   tables in a Derby Apache DB. method calls are commented out at times to deal with 
   particular tables. This class can be altered to be called from the main in BitsPlease
*/
import java.sql.*;
import javax.swing.*;

public class BitsPleaseDB

{
   public BitsPleaseDB()//static void main(String[] args)
   {
      final String DB_URL = "jdbc:derby:BitsPleaseDB;create=true";
      
      try
      {
         Connection conn = DriverManager.getConnection(DB_URL);
         
         dropTables(conn);
         
         buildUserTable(conn);
         
         buildMembersTable(conn);
         
         buildMemTypeTable(conn);
         
         buildEmployeeTable(conn);
         
         conn.close();
      }
      catch(Exception e)
      {
         System.out.println("Error3: " + e.getMessage());
      }
   }
   
   public static void dropTables(Connection conn)
   {
      try
      {
         Statement stmt = conn.createStatement();;
         
         try
         {
            stmt.execute("DROP TABLE Users");
            stmt.execute("DROP TABLE Members");
            stmt.execute("DROP TABLE MemPlans");
            stmt.execute("DROP TABLE Employees");
            System.out.println(" tables dropped");
         }
         catch(SQLException e)
         {
            System.out.println("DROP ERROR: " + e);
         }
         
      }
      catch(SQLException e)
      {
         System.out.println("Error2: " + e.getMessage());
         e.printStackTrace();
      }
   }
   
   public static void buildUserTable(Connection conn)
   {
      try
      {
         Statement stmt = conn.createStatement();
         
         stmt.execute("CREATE TABLE Users (" +
                      "uID Char(50) NOT NULL PRIMARY KEY," +
                      "Password Char(50) NOT NULL)");
         
         stmt.execute("INSERT INTO Users VALUES (" +
                      "'admin', '123456')");
         System.out.println("Users table created.");
      }
      catch(SQLException e)
      {
         System.out.println("ERROR1: " + e.getMessage());
      } 
      
   }
   public static void buildMembersTable(Connection conn)
   {
      try
      {
         Statement stmt = conn.createStatement();
         
         stmt.execute("CREATE TABLE Members (" +
                      "memID Char(10) NOT NULL PRIMARY KEY," +
                      "firstName Char(50) NOT NULL," +
                      "lastName Char(50) NOT NULL," +
                      "address Char(75)," +
                      "city Char(20)," +
                      "state Char(20)," +
                      "zipCode Char(10)," +
                      "phone Char(15)," +
                      "aPhone Char(15)," +
                      "memOption Char(25)," +
                      "bDate Char(10)," +
                      "aDate Char(10)," + 
                      "sex Char(7))");
         
         //stmt.execute("INSERT INTO Users VALUES (" +
                     // "'admin', '123456')");
      }
      catch(SQLException e)
      {
         System.out.println("ERROR1: " + e.getMessage());
      } 
      System.out.println("Members  table created.");
   }
   public static void buildEmployeeTable(Connection conn)
   {
      try
      {
         Statement stmt = conn.createStatement();
         
         stmt.execute("CREATE TABLE Employees (" +
                      "emID Char(10) NOT NULL PRIMARY KEY," +
                      "firstName Char(50) NOT NULL," +
                      "lastName Char(50) NOT NULL," +
                      "address Char(75)," +
                      "city Char(20)," +
                      "state Char(20)," +
                      "zipCode Char(10)," +
                      "phone Char(15)," +
                      "aPhone Char(15)," +
                      "bDate Char(10)," +
                      "hDate Char(10)," +
                      "title Char(50)," + 
                      "sex Char(7))");
         System.out.println("Employee  table created.");
      }
      catch(SQLException e)
      {
         System.out.println("ERROR emp: " + e.getMessage());
      } 
      
   }
   public static void buildMemTypeTable(Connection conn)
   {
      try
      {
         Statement stmt = conn.createStatement();
         
         stmt.execute("CREATE table MemPlans (" +
                      "plan_name Char(30) NOT NULL PRIMARY KEY," +
                      "description VarChar(255)," +
                      "cost Char(10)," +
                      "start_date Char(15)," +
                      "end_date Char(15))");
           
         stmt.execute("INSERT INTO MemPlans VALUES ('Free Trial', 'A free look at what the Gym offers.'," +
                      "'00.00', '2015-03-19', '2099-12-31')");
         System.out.println("MemPlans  table created.");
      }
      catch (SQLException e)
      {
         System.out.println("MemType Error:" + e.getMessage());
         
      }
   }
   
}