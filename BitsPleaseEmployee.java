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
      @param fName A first name, as a String
      @param lName A last name, as a String
      @param ad A BitsPleaseAddress  that holdes contact information 
      @param eNum Employee number, as a String
      @param bDay Birthday in the format yyyy-mm-dd, as a String
      @param hDay Hire date in teh format yyyy-mm-dd, as a String
      @job An employee title, as a String
      @sx The gender of employee, as a String
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
   /**
      The setFirstName method sets first name
      @param fName A first name, as a String
   */
   public void setFirstName(String fName)
   {
      super.firstName = fName;
   }
   /**
      The setLastName method sets last name
      @param lName A last name, as a String
   */
   public void setLastName(String lName)
   {
      super.lastName = lName;
   }
   /**
      The setGender method sets gender of person
      @param sx The sex of a person
   */
   public void setGender(String sx)
   {
      super.sex = sx;
   }
   /**
      The setEmNumber method sets a employee number
      @param eNum An employee number, as a String
   */
   public void setEmNumber(String eNum)
   {
      emNumber = eNum;
   }
   /**
      The setBDate method sets a birthday, yyyy-mm-dd
      @param bD A date in the form of a String, yyyy-mm-dd
   */
   public void setBDate(String bD)
   {
      birthDate = bD;
   }
   /**
      The setHDate method sets a hire date, yyyy-mm-dd
      @param hD A date in the form of a String, yyyy-mm-dd
   */
   public void setHDate (String hD)
   {
      hireDate = hD;
   }
   /**
      The setTitle methods sets employee title
      @prama t A title of employee, in the form of a String
   */
   public void setTitle (String t)
   {
      title = t;
   }
   /**
      The getFristName method returns first name
      @return The first name, as String value
   */ 
   public String getFirstName()
   {
      return super.firstName;
   }
   /**
      the getLastName method retruns last name
      @return The last name, as String value
   */ 
   public String getLastName()
   {
      return super.lastName;
   }
   /**
      The getGender method returns gender
      @return The gender of employee, as String value
   */
   public String getGender()
   {
      return super.sex;
   }
   /**
      the getEmNuber method returns an employee number
      @return The employee number, as String value
   */
   public String getEmNumber()
   {
      return emNumber;
   }
   /**
      The getBDate method returns birthdate
      @return The birthDate as a String value, yyyy-mm-dd
   */
   public String getBDate()
   {
      return birthDate;
   }
   /**
      The getHDate method returns hire date
      @return The hireDate as a String value, yyyy-mm-dd
   */
   public String getHDate()
   {
      return hireDate;
   }
   /**
      The getTitle method returns employee title
      @return The title as a String value
   */
   public String getTitle()
   {
      return title;
   }
   
}