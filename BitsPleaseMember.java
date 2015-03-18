import java.sql.*;

public class BitsPleaseMember extends BitsPleasePerson
{
   private BitsPleaseAddress address;
   private BitsPleaseMembershipType membType;
   //private BitsPleaseBill bill;
   private String memNumber;
    
   
   public BitsPleaseMember()
   {
   
   }
   public BitsPleaseMember(String fName, String lName, BitsPleaseAddress ad, BitsPleaseMembershipType mType,
                             String mNum)
   {
      super.firstName= fName;
      super.lastName = lName;
      address = ad;
      membType = mType;
      memNumber = mNum;
      
      
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
                     address.getAltPhoneNum() + "','" + membType.getName()  +"')" );
              
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