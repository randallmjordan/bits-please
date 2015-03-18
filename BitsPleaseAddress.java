public class BitsPleaseAddress
{
   private String streetAddress;
   private String city;
   private String state;
   private String zipCode;
   private String phoneNum;
   private String aPhoneNum;
   //private String eMailAdrress;
  
   public BitsPleaseAddress()
   {
   
   }
   public BitsPleaseAddress(String stAd, String cName, String st, String zCode, String pNum, String aPNum)
   {
      streetAddress = stAd;
      city = cName;
      state = st;
      zipCode = zCode;
      phoneNum = pNum;
      aPhoneNum = aPNum;
   }
   public void setStAddress(String stAd)
   {
      streetAddress = stAd;
   }
   public void setCity(String cName)
   {
      city = cName;
   }
   public void setState (String st)
   {
      state = st;
   }
   public void setZipCode(String zCode)
   {
      zipCode = zCode;
   }
   public void setPhoneNum(String pNum)
   {
      phoneNum = pNum;
   }
   public void setAltPhoneNum(String aNum)
   {
      aPhoneNum = aNum;
   }
   public String getStAddress()
   {
      return streetAddress;
   }
   public String getCity()
   {
      return city;
   }
   public String getState()
   {
      return state;
   }
   public String getZipCode()
   {
      return zipCode;
   }
   public String getPhoneNum()
   {
      return phoneNum;
   }
   public String getAltPhoneNum()
   {
      return aPhoneNum;
   }
}