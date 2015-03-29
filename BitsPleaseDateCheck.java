import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.Random;
import java.text.*;
import java.util.Date;

public class BitsPleaseDateCheck
{
   
   
   public static boolean checkDates(String s)
   {
      //not doing Leap Years
      int year = 0;
      int month = 0;
      int day = 0;
      
      if (s.length()!= 10)
      {
         JOptionPane.showMessageDialog(null, "Illegal format, please enter yyyy-mm-dd","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      try
      {
         year = Integer.parseInt(s.substring(0,4));
         month = Integer.parseInt(s.substring(5,7));
         day = Integer.parseInt(s.substring(8,10));
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null, "Illegal format, please enter yyyy-mm-dd","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      try
      {
         if (month > 12)
         {
            JOptionPane.showMessageDialog(null, month +" is not appropriate Month #","ILLEGAL MONTH", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         if (day > 31)
         {
            JOptionPane.showMessageDialog(null,"There are not " + day +
                                          " days in month " + month,"ILLEGAL", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         else if ( day > 30 && (month == 4 || month == 6 || month == 9 || month == 11))
         {
            JOptionPane.showMessageDialog(null,"There are not " + day +
                                          " days in month " + month,"ILLEGAL", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         else if ( day > 28 && month == 2)
         {
            JOptionPane.showMessageDialog(null,"There are not " + day +
                                          " days in month " + month,"ILLEGAL", JOptionPane.ERROR_MESSAGE);
            return false;
         }
      }
      catch (Exception exc)
      {
         JOptionPane.showMessageDialog(null, "Illegal format, please enter yyyy-mm-dd","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      return true;
   }

}