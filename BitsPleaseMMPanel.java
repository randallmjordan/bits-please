/**
   The BitsPleaseMMPanel creates a common opaque panel used on many of the GUI pages throughout Bits Please
   MGMT system.
*/
import javax.swing.*;
import java.awt.*;

public class BitsPleaseMMPanel extends JPanel
{
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   
   public BitsPleaseMMPanel()
   {
      super.setBackground(new Color(204,204,204,150));
   }
   public void setBG()
   {
      super.setBackground(new Color(204,204,204,150));
   }
}