public class BitsPleasePerson
{
   String firstName;
   String lastName;
   private String sex;
   //left out birthday stuff for now.
   
   public BitsPleasePerson()
   {
   
   }
   public void setFirstName(String fName)
   {
      firstName = fName;
   }
   public void setLastName (String lName)
   {
      lastName = lName;
   }
   public void setSexType (String s)
   {
      sex = s;
   }
   public String getFirsName()
   {
      return firstName;  
   }
   public String getLastName()
   {
      return lastName;
   }
   public String getSexType()
   {
      return sex;
   }
   
}