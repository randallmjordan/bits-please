
import javax.swing.*;
import java.awt.*;

public class BitsPleaseMMButton extends JButton
{
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   
   public BitsPleaseMMButton(String text)
   {
      this.setText(text);
      super.setPreferredSize(new Dimension(200,75));
      super.setBackground(new Color(51,51,51,240));
      super.setForeground(Color.WHITE);
   }
}