import java.sql.*;

public class BitsPleaseEquipment 
{
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private String eqName;
   private String eqType;
   private String eqId;
   private String eqPurchaseDate;
   private double eqCost;
   private String eqLastMaintDate;
   private double eqExpectedLife;
   private String eqNextMaintDate;
   
   /**
      Constructor
   */
   public BitsPleaseEquipment()  
   {
   
   }
   /**
      BitsPelaseEquipment Constructor
      @param eName The name of equipment
      @param eType The type of equipment
      @param eID The identification number of equipment
      @param ePDate the date of purchase of equipent
      @param eCost The cost of equipment purchase
      @eLMD The date of last maintenence, yyyy-mm-dd
      @eLife The life expectancy of equipment;
      @eNMD The date of next maintenence, yyyy-mm-dd
   */   
   public BitsPleaseEquipment(String eName, String eType, String eID, String ePDate, double eCost,
                              String eLMD, double eLife)
   {
      eqName = eName;
      eqType = eType;
      eqId = eID;
      eqPurchaseDate = ePDate;
      eqCost = eCost;
      eqLastMaintDate = eLMD;
      eqExpectedLife = eLife;
      eqNextMaintDate = setDate(eLMD);
   }
   /**
      The setName method sets the equipment name
      @param eN A String that is equipment name
   */
   public void setName(String eN)
   {
      eqName = eN;
   }
   /**
      The setType method sets the equipment type
      @param t The type of equipment, as a String
   */ 
   public void setType(String t)
   {
      eqType = t;  
   } 
   /**
      The setID method sets the identification of the equiment
      @param i The ID as a String
   */
   public void setID(String i)
   {
      eqId = i;
   } 
   /**
      The setBuyDate method sets the Purchase Date
      @param pD The purchase date, as a String
   */
   public void setBuyDate(String pD)
   {
      eqPurchaseDate = pD;
   }
   /**
      The setCost method sets the cost of  equipment
      @param 
   */
   public void setCost(double c)
   {
      eqCost = c;
   }
   /**
      The setLastMaintDate method sets the last maintenece date of equipment
      @param mD The last maintenence date, as String, yyyy-mm-dd
   */
   public void setLastMaintDate(String mD)
   {
      eqLastMaintDate = mD;
   }
   /**
      The setLife method sets the expected life, in years, of equipment
      @param l The life expectancy of equipment, as double, not formatted.
   */
   public void setLife(double l)
   {
      eqExpectedLife = l;
   }
   /**
      The setNextMaintDate method allows for setting of next maintenence date
      @param nMD The next maintenence date as a String
   */
   public void setNextMaintDate(String nMD)
   {
      eqNextMaintDate = nMD;
   }
   /**
      The setDate method will set next Maintenence date based on 6 month schedule
      The date can be changed via setNextMaintDate method
      @param date The date of most recent maintenenct
   */
   private String setDate(String date)
   {
      int year = Integer.parseInt(date.substring(0,4));
      int month = Integer.parseInt(date.substring(5,7));
      int day = Integer.parseInt(date.substring(8,10));
      
      if (month > 6)
      {
         month = month - 6;
         year++;
      }
      else 
      {
         month = month + 6;
      }
      if ( day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))
      {
         day = 30;
      }
      else if ( day > 28 && month ==2)
      {
         day = 28;
      }
      return Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
   }
   /**
      The getName method returns equipment name
      @return A String value of equipment name
   */
   public String getName()
   {
      return eqName;
   }
   /**
      The getType method returns equipment type
      @return A String valuse of the equipment type.
   */ 
   public String getType()
   {
      return eqType;  
   }
   /**
      The getID method returns equipment id
      @return The id number of equipment, as a String
   */ 
   public String getID()
   {
      return eqId;
   }
   /**
      The getBuyDate method returns the date of purchase of equipment
      @return The date of purchase, as a String , yyyy-mm-dd
   */ 
   public String getBuyDate()
   {
     return  eqPurchaseDate;
   }
   /**
      The getCost method returns the cost of equipment
      @return The cost, as a double, of the equipment.
   */
   public double getCost()
   {
      return eqCost;
   }
   /**
      The getLastMaintDate method returns the date of last maintenenc on equipment
      @return The date of last maintenence, as a String, yyyy-mm-dd
   */
   public String getLastMaintDate()
   {
      return eqLastMaintDate;
   }
   /**
      The getLife method returns the expected life of equipment
      @return The expected life of equipment, as a double, not formatted
   */
   public double getLife()
   {
      return eqExpectedLife;
   }
   /**
      The getNextMaintDate method gets the date of equipments next maintence
      @return The date of next maintenence as a String
   */
   public String getNextMaintDate()
   {
      return eqNextMaintDate;
   }
   public void insertToDB()
   {
      try
           {
               Statement stmt  = BitsPlease.conn.createStatement();;
               stmt.execute("INSERT INTO  Equipment Values ('" + 
                            eqId + "','" + eqName + "','" +
                            eqType + "','" + eqPurchaseDate + "'," +
                            eqCost + ",'" + eqLastMaintDate + "'," +
                            eqExpectedLife + ",'" + eqNextMaintDate + "')" );
              
           } 
           catch (Exception x)
           {
            System.out.println( "Insert EQ err:  " + x);
           }

   }
}
