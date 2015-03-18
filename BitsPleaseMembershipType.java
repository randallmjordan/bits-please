public class BitsPleaseMembershipType
{
   private String memTypeName;
   private String memTypeDescription;
   private double memTypeCost;
   private String memTypeStartDate;
   private String memTypeEndDate;
   
   public BitsPleaseMembershipType()
   {
   
   }
   public BitsPleaseMembershipType(String name)
   {
      memTypeName = name;
   }
   public void setName(String name)
   {
      memTypeName = name;
   }  
   public void setDescription(String desc)
   {
      memTypeDescription = desc;
   }
   public void setMemTypeCost(double cost)
   {
      memTypeCost = cost;
   }  
   public void setStartDate(String date)
   {
      memTypeStartDate = date;
   }
   public void setEndDate(String date)
   {
      memTypeEndDate = date;
   }
   public String getName()
   {
      return memTypeName;
   }
   public String getDesc()
   {
      return memTypeDescription;
   }
   public double getCost()
   {
      return memTypeCost;
   }
   public String getStartDate()
   {
      return memTypeStartDate;
   }
   public String getEndDate()
   {
      return memTypeEndDate;
   }
}