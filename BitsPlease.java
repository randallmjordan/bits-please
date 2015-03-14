import java.io.*;
import java.sql.*;

public class BitsPlease 
{
   public static Connection conn = null;       
   
   public static void main(String[] args)throws IOException, SQLException
   {
      //BitsPleaseDB db = new BitsPleaseDB();
      
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