/**
   The BitsPleaseMember class creates a BitsPleaseMember object from info sent from BitsPleaseNewMember GUI
*/

import java.sql.*;

public class BitsPleaseMember extends BitsPleasePerson
{
   private BitsPleaseAddress address;
   private BitsPleaseMembershipType membType;
   //private BitsPleaseBill bill;
   private String memNumber, birthDate, activeDate;
      
   public BitsPleaseMember()
   {
   
   }
   /**
      BitsPleaseMember Constructor
      @param fName A first name, as a String
      @param lName A last name, as a String
      @param ad A BitsPleaseAddress  that holdes contact information
      @param mType A BitsPleaseMemberShipType that is membership plan 
      @param mNum Member number, as a String
      @param bDay Birthday in the format yyyy-mm-dd, as a String
      @param aDay Hire date in teh format yyyy-mm-dd, as a String
      @sx The gender of employee, as a String
   */
   public BitsPleaseMember(String fName, String lName, BitsPleaseAddress ad, BitsPleaseMembershipType mType,
                             String mNum, String bDay, String aDay, String sx)
   {
      super.firstName= fName;
      super.lastName = lName;
      super.sex = sx;
      address = ad;
      membType = mType;
      memNumber = mNum;
      birthDate = bDay;
      activeDate = aDay;
   }
   public void insertToDB()
   {
      try
           {
               Statement stmt  = BitsPlease.conn.createStatement();;
               stmt.execute("INSERT INTO  Members Values ('" +
                     memNumber + "','" + firstName + "','" + lastName + "','" +
                     address.getStAddress() + "','" + address.getCity() + "','" + address.getState() + "','" +
                     address.getZipCode() + "','" + address.getPhoneNum() + "','" +
                     address.getAltPhoneNum() + "','" + membType.getName()  + "','" +
                     birthDate + "','" + activeDate + "','" + sex + "')" );
              
           } 
           catch (Exception x)
           {
            System.out.println(x);
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
      The setMemNumber method sets a member number
      @param eNum An employee number, as a String
   */
   public void setMemNumber(String mNum)
   {
      memNumber = mNum;
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
      The setADate method sets a active date, yyyy-mm-dd
      @param aD A date in the form of a String, yyyy-mm-dd
   */
   public void setADate(String aD)
   {
      activeDate = aD;
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
      the getMemNumber method returns a member number
      @return The member number, as String value
   */
   public String getMemNumber()
   {
      return memNumber;
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
      The getADate method returns active date
      @return The activeDate as a String value, yyyy-mm-dd
   */  
   public String getADate()
   {
      return activeDate;
   }
}