/**
   BitsPleaseAddress class creates an Address object. It will be used to along side the BitsPleasePerson class
   to assist in making a BitsPleaseMember and BitsPleaseEmployee.
*/

public class BitsPleaseAddress
{
   private String streetAddress;
   private String city;
   private String state;
   private String zipCode;
   private String phoneNum;
   private String aPhoneNum;
   //private String eMailAdrress;
  
   /**
      Constructor
   */
   public BitsPleaseAddress()
   {
   
   }
   /**
      Constructor
      @param stAd A street Address
      @param cName Name of a city
      @param st The name of a state
      @param zCode A 5 digit zip code
      @pNum A phone number
      @aNum An alternate phone number
   */
   public BitsPleaseAddress(String stAd, String cName, String st, String zCode, String pNum, String aPNum)
   {
      streetAddress = stAd;
      city = cName;
      state = st;
      zipCode = zCode;
      phoneNum = pNum;
      aPhoneNum = aPNum;
   }
   /**
      The setStAddress method sets the street address.
      @param stAd The Value, as a String, to store in streetAddress
   */
   public void setStAddress(String stAd)
   {
      streetAddress = stAd;
   }
   /**
      The setCity method sets the city name.
      @param cName The Value, as a String, to store in city
   */
   public void setCity(String cName)
   {
      city = cName;
   }
   /**
      The setState method sets the state.
      @param stAd The Value, as a String, to store in state
   */
   public void setState (String st)
   {
      state = st;
   }
   /**
      The setZipCode method sets teh zip code.
      @param zCode The value, as a String, to store in zipCode
   */
   public void setZipCode(String zCode)
   {
      zipCode = zCode;
   }
   /**
      The setPhoneNum method sets the phone number
      @param pNum The value, as a String, to store in phoneNum
   */
   public void setPhoneNum(String pNum)
   {
      phoneNum = pNum;
   }
   /**
      The setAltPhoneNum method sets the alternate phone number
      @param aNum The value, as a String, to store in aPhoneNum
   */
   public void setAltPhoneNum(String aNum)
   {
      aPhoneNum = aNum;
   }
   /**
      The getStAddress method returns street address
      @return The String value of streetAddress
   */
   public String getStAddress()
   {
      return streetAddress;
   }
   /**
      the getCity method returns city
      @return The String value of city
   */
   public String getCity()
   {
      return city;
   }
   /**
      The getState method returns state
      @return The String value of state.
   */
   public String getState()
   {
      return state;
   }
   /**
      The getZipCode method returns the zip code
      @return The String value of zipCode.
   */
   public String getZipCode()
   {
      return zipCode;
   }
   /**
      The getPhoneNum method returns the phone number
      @return The String value of phoneNum
   */
   public String getPhoneNum()
   {
      return phoneNum;
   }
   /**
      The getAltPhoneNum method returns the alternate phone number
      @return The String value of aPhoneNum
   */
   public String getAltPhoneNum()
   {
      return aPhoneNum;
   }
}