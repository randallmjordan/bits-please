/**
   The BitsPleaseMembershipType class creates the BitsPleaseMembershipType objects.
*/

public class BitsPleaseMembershipType
{
   private String memTypeName;
   private String memTypeDescription;
   private double memTypeCost;
   private String memTypeStartDate;
   private String memTypeEndDate;
   
   /**
      Constructor
   */
   public BitsPleaseMembershipType()
   {
   
   }
   /**
      Constructor
      @param name The name of Membership Plan
   */ 
   public BitsPleaseMembershipType(String name)
   {
      memTypeName = name;
   }
   /**
      The setName method sets the name of a Plan type
      @param name The name of a membership plan
   */
   public void setName(String name)
   {
      memTypeName = name;
   }
   /**
      The setDescription method sets description of a plan
      @param desc
   */  
   public void setDescription(String desc)
   {
      memTypeDescription = desc;
   }
   /**
      The setMemTypeCost method sets the monthly cost for plan type
      @param cost Cost as a double. Formating will be required.
   */
   public void setMemTypeCost(double cost)
   {
      memTypeCost = cost;
   } 
   /**
      The setStartDate method sets the date available of plan
      @param date A date in form yyyy-mm-dd, as a String value
   */ 
   public void setStartDate(String date)
   {
      memTypeStartDate = date;
   }
   /**
      The setEndDate method sets the end date for plan
      @param date A date in form yyyy-mm-dd, as a String value
   */
   public void setEndDate(String date)
   {
      memTypeEndDate = date;
   }
   /**
      The getName method returns name of plan
      @return  The name of plan as a String
   */
   public String getName()
   {
      return memTypeName;
   }
   /**
      The getDesc method returns description of plan
      @return The description of plan
   */
   public String getDesc()
   {
      return memTypeDescription;
   }
   /**
      The getCost method returns cost of plan, no format
      @return The cost of plan, not formatted
   */
   public double getCost()
   {
      return memTypeCost;
   }
   /**
      The getStartDate method returns plans start date
      @return The date  plan start in form yyyy-mm-dd
   */
   public String getStartDate()
   {
      return memTypeStartDate;
   }
   /**
      The getEndDate method returns plans end date
      @return The date plane ends in form yyyy-mm-dd
   */
   public String getEndDate()
   {
      return memTypeEndDate;
   }
}