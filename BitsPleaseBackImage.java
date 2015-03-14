
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

   
class BitsPleaseBackImage extends JComponent 
{
 
      private Image bf = new ImageIcon(this.getClass().getResource("background.png")).getImage();
       
      //Creating Constructer
      public BitsPleaseBackImage()
      {
         this.bf = bf;
      }
       
      //Overriding the paintComponent method
      @Override
      public void paintComponent(Graphics g)
      {
         g.drawImage(bf, 0, 0, null);  // Drawing image using drawImage method
      }
}
