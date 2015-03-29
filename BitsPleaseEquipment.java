

public class BitsPleaseEquipment 
{
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private String eqName;
   private String eqType;
   private String eqId;
   private String eqPurchaseDate;
   private double eqCost;
   private String eqLastMaintDate;
   private double eqExpected Life;
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
                              String eLMD, double eLife, String eNMD)
   {
      eqName = eName;
      eqType = eType;
      eqID = eID;
      eqPurchaseDate = ePDate;
      eqCost = eCost;
      eqLastMaintDate = eLMD;
      eqExpectedLife = eLife;
      eqNextMaintDate = eNMD;
   }
   public void setName(String eN)
   {
      eqName = eN;
   } 
   public void setType(String t)
   {
      eqType = t;  
   } 
   public void setID(String i)
   {
      eqID = i;
   } 
   public void setBuyDate(String pD)
   {
      eqPurchaseDate = pD;
   }
   public void setCost(double c)
   {
      eqCost = e;
   }
   public void setLastMaintDate(String mD)
   {
      eqLastMaintDate = mD;
   }
   public void setLife(double l)
   {
      eqExpectedLife = l;
   }
   public void setNextMaintDate(String nMD)
   {
      eqNextMaintDate = nMD;
   }
  
}