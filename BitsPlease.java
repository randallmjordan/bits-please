/**
   The BitsPlease class is the launcher for the Bits Please Gym MGMT software.
   The BitsPlease group of classes is for a Spring 2015 project at Georgia State
   University, class CSc 4350 with Bhola.
   The Bits Please team consists of:
   Zack Dannawi, Randall Jordan, Kang Shin, Patrick Trang, & Chaitanya Vyas.
*/
import java.io.*;
import java.sql.*;

public class BitsPlease 
{
   public static Connection conn = null;       
   
   public static void main(String[] args)throws IOException, SQLException
   {
      BitsPleaseDB db = new BitsPleaseDB();
      
      final String DB_URL = "jdbc:derby:BitsPleaseDB;create=true";

      
      try
      {
         conn = DriverManager.getConnection(DB_URL);
         BitsPleaseLogin bpGo = new BitsPleaseLogin();
      }
      catch (Exception e)
      {
         System.out.println("MainError: " + e.getMessage());
         e.printStackTrace();
      }
   }
}