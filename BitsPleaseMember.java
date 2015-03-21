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
   public void setMemNumber(String mNum)
   {
      memNumber = mNum;
   }
   public String getMemNumber()
   {
      return memNumber;
   }
}