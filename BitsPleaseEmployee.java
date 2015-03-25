/**
   The BitsPleaseEmployee class creates and Employee from information sent from the BitsPleaseNewMember GUI.
   
*/
import java.sql.*;

public class BitsPleaseEmployee extends BitsPleasePerson
{
   private BitsPleaseAddress address;
   private String emNumber, birthDate, hireDate, title;
   /**
       Constructor
   */   
   public BitsPleaseEmployee()
   {
   
   }
   /**
      BitsPleaseEmployee Constructor
   */
   public BitsPleaseEmployee(String fName, String lName, BitsPleaseAddress ad, 
                             String eNum, String bDay, String hDay, String job, String sx)
   {
      super.firstName= fName;
      super.lastName = lName;
      super.sex = sx;
      address = ad;
      emNumber = eNum;
      birthDate = bDay;
      hireDate = hDay;
      title = job;
   }
   public void insertToDB()
   {
      try
           {
               Statement stmt  = BitsPlease.conn.createStatement();;
               stmt.execute("INSERT INTO  Employees Values ('" +
                     emNumber + "','" + firstName + "','" + lastName + "','" +
                     address.getStAddress() + "','" + address.getCity() + "','" + address.getState() + "','" +
                     address.getZipCode() + "','" + address.getPhoneNum() + "','" +
                     address.getAltPhoneNum() + "','" + 
                     birthDate + "','" + hireDate + "','" + title + "','" + sex + "')" );
              
           } 
           catch (Exception x)
           {
            System.out.println( "Insert empt err:  " + x);
           }

   }
   public void setMemNumber(String eNum)
   {
      emNumber = eNum;
   }
   public void setBDate(String bD)
   {
      birthDate = bD;
   }
   public void setHDate (String hD)
   {
      hireDate = hD;
   }
   public void setTitle (String t)
   {
      title = t;
   }  
   public String getEmNumber()
   {
      return emNumber;
   }
   public String getBDate()
   {
      return birthDate;
   }
   public String getHDate()
   {
      return hireDate;
   }
   public String getTitle()
   {
      return title;
   }
   
}