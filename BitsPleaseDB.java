import java.sql.*;

public class BitsPleaseDB

{
   public /*BitsPleaseDB()*/static void main(String[] args)
   {
      final String DB_URL = "jdbc:derby:BitsPleaseDB;create=true";
      
      try
      {
         Connection conn = DriverManager.getConnection(DB_URL);
         
         dropTables(conn);
         
         buildUserTable(conn);
         
         buildMembersTable(conn);
         
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
            System.out.println("Users table dropped");
         }
         catch(SQLException e)
         {
            
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
      }
      catch(SQLException e)
      {
         System.out.println("ERROR1: " + e.getMessage());
      } 
      System.out.println("Users table created.");
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
                      "memOption Char(25))");
         
         //stmt.execute("INSERT INTO Users VALUES (" +
                     // "'admin', '123456')");
      }
      catch(SQLException e)
      {
         System.out.println("ERROR1: " + e.getMessage());
      } 
      System.out.println("Members  table created.");
   }
}