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

public class BitsPleaseNewEquipment extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton save, saveAndClose, addAnotherEquipment, cancel;
   private JLabel pageLabel, nameLabel, typeLabel, idLabel, pDateLabel, costLabel, lifeLabel, maintLabel, nextMaintLabel;
   private JTextField nameField,typeField, idField, pDateField, maintField, nextMaintField, costField, lifeField;
   private BitsPleaseMMPanel newEqPanel,rowZeroPanel, rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel,
           rowSixPanel,rowSevenPanel, newEqPanelb;
   private int eqNumber;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

   
   public BitsPleaseNewEquipment() throws IOException, SQLException
   {
      setTitle("Bits Please Gym MGMT System - New Equipment");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildNewEqPanel();
      newEqPanel.setBounds(60,570,934,83);
      newEqPanelb.setBounds(20,120,40,608);
      buildRowZero();
      rowZeroPanel.setBounds(20, 20, 974, 100);
      buildRowOne();
      rowOnePanel.setBounds(60,120,934,75);
      buildRowTwo();
      rowTwoPanel.setBounds(60,195,934,75);
      buildRowThree();
      rowThreePanel.setBounds(60,270,934,75);
      buildRowFour();
      rowFourPanel.setBounds(60,345,934,75);
      buildRowFive();
      rowFivePanel.setBounds(60,420,934,75);
      buildRowSix();
      rowSixPanel.setBounds(60,495,934,75);
      buildRowSeven();
      rowSevenPanel.setBounds(60,653,934,75);
      
      add(newEqPanel);
      add(newEqPanelb);
      add(rowZeroPanel);
      add(rowOnePanel);
      add(rowTwoPanel);
      add(rowThreePanel);
      add(rowFourPanel);
      add(rowFivePanel);
      add(rowSixPanel);
      add(rowSevenPanel);
      
      setVisible(true);
   }
   public void buildNewEqPanel()
   {
      newEqPanel = new BitsPleaseMMPanel();
      newEqPanel.setPreferredSize(new Dimension(974,158));
      newEqPanelb = new BitsPleaseMMPanel();
      newEqPanel.setPreferredSize(new Dimension(40,608));
      
            
      
   }
   private void buildRowZero()
   {
      rowZeroPanel = new BitsPleaseMMPanel();
      pageLabel = new JLabel("Create New Equipment");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
      
      rowZeroPanel.add(pageLabel);
      
   }

   private void buildRowOne()
   {
      rowOnePanel = new BitsPleaseMMPanel();
      rowOnePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      nameLabel = new JLabel("Equipment Name:             ");
      nameField = new JTextField(25);
      
      rowOnePanel.add(nameLabel);
      rowOnePanel.add(nameField);
      
   }
   private void buildRowTwo()
   {
      rowTwoPanel = new BitsPleaseMMPanel();
      rowTwoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      typeLabel = new JLabel("Type of Equipment:       "); 
      typeField = new JTextField(25);
      rowTwoPanel.add(typeLabel);
      rowTwoPanel.add(typeField);
             
   }
   private void buildRowThree()
   {
      Random random = new Random();
      eqNumber = random.nextInt(99999 - 10000) + 10000;

      rowThreePanel = new BitsPleaseMMPanel();
      rowThreePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      idLabel= new JLabel("Equipment ID:                  ");
      idField = new JTextField("e" + Integer.toString(eqNumber) + "q", 10);
      rowThreePanel.add(idLabel);
      rowThreePanel.add(idField);
      
   }
   private void buildRowFour()
   {
      rowFourPanel = new BitsPleaseMMPanel();
      rowFourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      pDateLabel = new JLabel("Purchase Date:       ");
      costLabel = new JLabel("      Cost:      ");
      lifeLabel = new JLabel("    Expected Life:     ");
      pDateField = new JTextField(12);
      costField = new JTextField(12);
      lifeField = new JTextField(12);
      rowFourPanel.add(pDateLabel);
      rowFourPanel.add(pDateField);
      rowFourPanel.add(costLabel);
      rowFourPanel.add(costField);
      rowFourPanel.add(lifeLabel);
      rowFourPanel.add(lifeField);
      
   }
   private void buildRowFive()
   {
      rowFivePanel = new BitsPleaseMMPanel();
      /*rowFivePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      maintLabel = new JLabel("Last Maintenance:           ");
      nextMaintLabel = new JLabel("     Next Maintenance:     ");
      maintField = new JTextField(25);
      nextMaintField = new JTextField(25);      
      rowFivePanel.add(maintLabel);
      rowFivePanel.add(maintField);
      rowFivePanel.add(nextMaintLabel);
      rowFivePanel.add(nextMaintField);*/
      
   }
   private void buildRowSix()
   {
     rowSixPanel = new BitsPleaseMMPanel();
   }
   private void buildRowSeven()
   {
      rowSevenPanel = new BitsPleaseMMPanel();
      rowSevenPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      save = new JButton("Save");
      save.addActionListener(new ButtonListener());
      saveAndClose = new JButton("Save & Close");
      saveAndClose.addActionListener(new ButtonListener());
      addAnotherEquipment = new JButton("Add Another Equipment");
      addAnotherEquipment.addActionListener(new ButtonListener());
      cancel = new JButton("Cancel");
      cancel.addActionListener(new ButtonListener());
      rowSevenPanel.add(save);     
      rowSevenPanel.add(saveAndClose);
      rowSevenPanel.add(addAnotherEquipment);
      rowSevenPanel.add(cancel);
      
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand = e.getActionCommand();
         if (actionCommand.equals("Save"))        
         {           
            try
            {
               Date date = dateFormat.parse(pDateField.getText());
               date = dateFormat.parse(maintField.getText());
               date = dateFormat.parse(nextMaintField.getText());
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(BitsPleaseDateCheck.checkDates(pDateField.getText())) || !(BitsPleaseDateCheck.checkDates(maintField.getText()))
                || !(BitsPleaseDateCheck.checkDates(nextMaintField.getText())))
            {
               return;
            }
            setVisible(false);
            dispose();
            
            BitsPleaseEquipment eq = new BitsPleaseEquipment(nameField.getText().trim(), typeField.getText().trim(), 
                                     idField.getText().trim(), pDateField.getText().trim(), Double.parseDouble(costField.getText().trim()),
                                     pDateField.getText().trim(), Double.parseDouble(lifeField.getText().trim()));        

           eq.insertToDB();
           
           try
           {
               
               BitsPleaseNewEquipment newE = new BitsPleaseNewEquipment();
           } 
           catch (Exception x)
           {
            System.out.println("Save error: " + x);
           }
         }
         else if (actionCommand.equals("Save & Close"))
         {
            try
            {
               Date date = dateFormat.parse(pDateField.getText());
               //date = dateFormat.parse(maintField.getText());
               //date = dateFormat.parse(nextMaintField.getText()); 
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(BitsPleaseDateCheck.checkDates(pDateField.getText())))
            {
               return;
            }
            
            setVisible(false);
            dispose();
            BitsPleaseEquipment eq = new BitsPleaseEquipment(nameField.getText().trim(), typeField.getText().trim(), 
                                     idField.getText().trim(), pDateField.getText().trim(), Double.parseDouble(costField.getText().trim()),
                                     pDateField.getText().trim(), Double.parseDouble(lifeField.getText().trim()));
            
            
           eq.insertToDB();
           try
           {
               BitsPleaseMainMenu mM = new BitsPleaseMainMenu();
           } 
           catch (Exception x)
           {
            System.out.println(x);
           }
         }
         if (actionCommand.equals("Add Another Equipment"))
         {
            setVisible(false);
            dispose();
            try
           {
               BitsPleaseNewEquipment newEq = new BitsPleaseNewEquipment();
           } 
           catch (Exception x)
           {
           
           }
         }
         if (actionCommand.equals("Cancel"))
         {
            setVisible(false);
            dispose();
            try
           {
               BitsPleaseMainMenu mM = new BitsPleaseMainMenu();
           } 
           catch (Exception x)
           {
           
           }
         }
      }
   }
}