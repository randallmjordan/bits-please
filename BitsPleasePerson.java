/**
   The BitsPleasePerson is the superclass for BitsPleaseMember and BitsPleaseEmployee
*/
public class BitsPleasePerson
{
   String firstName;
   String lastName;
   String sex;
   
   
   /**
      Constructor
   */
   public BitsPleasePerson()
   {
   
   }
   /**
      The setFirstName method sets a first name
      @param fName The first name, as a String
   */
   public void setFirstName(String fName)
   {
      firstName = fName;
   }
   /**
      The setLastName method sets a last name
      @param lName The last name, as a String
   */
   public void setLastName (String lName)
   {
      lastName = lName;
   }
   /**
      The setSexType method sets a gender value
      @param s The gender is set, as a String value
   */
   public void setSexType (String s)
   {
      sex = s;
   }
   /**
      The getFirstName method returns the String value firstName
      @return The firstName, as a String
   */
   public String getFirstName()
   {
      return firstName;  
   }
   /**
      The getLastName method returns the String value lastName
      @return The lastName, as a String
   */
   public String getLastName()
   {
      return lastName;
   }
   /**
      The getSexType method returns the gender value
      @return The sex, as a String 
   */
   public String getSexType()
   {
      return sex;
   }
   
}