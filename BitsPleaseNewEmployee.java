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

public class BitsPleaseNewEmployee extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton save, saveAndClose, addAnotherEmployee, cancel;
   private JLabel pageLabel, firstName, genderLabel, lastName, birthDate, hireDate, streetAddress, city, zipCode, phone, state, altPhone,title, emNum;
   private JTextField fNameField, lNameField, bDateField, hDateField, sAddressField, cityField, zCodeField, phoneField, aPhoneField, titField, eNumField;
   private JComboBox<String> stateBox, sexBox;
   private BitsPleaseMMPanel newEmPanel,rowZeroPanel, rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel,
           rowSixPanel,rowSevenPanel, newEmPanelb;
   private int emNumber;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private String sta = "", gen = "";
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
   public BitsPleaseNewEmployee() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - New Employees");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildNewEmPanel();
      newEmPanel.setBounds(60,570,934,83);
      newEmPanelb.setBounds(20,120,40,608);
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
      
      add(newEmPanel);
      add(newEmPanelb);
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
   public void buildNewEmPanel()
   {
      newEmPanel = new BitsPleaseMMPanel();
      newEmPanel.setPreferredSize(new Dimension(974,158));
      newEmPanelb = new BitsPleaseMMPanel();
      newEmPanel.setPreferredSize(new Dimension(40,608));
      
             
      
   }
   private void buildRowZero()
   {
      rowZeroPanel = new BitsPleaseMMPanel();
      pageLabel = new JLabel("Create New Employee");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
      
      rowZeroPanel.add(pageLabel);
   }

   private void buildRowOne()
   {
      rowOnePanel = new BitsPleaseMMPanel();
      rowOnePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      firstName = new JLabel("First Name:             ");
      lastName = new JLabel("       Last Name:          ");
      fNameField = new JTextField(20);
      lNameField = new JTextField(20);
      rowOnePanel.add(firstName);
      rowOnePanel.add(fNameField);
      rowOnePanel.add(lastName);
      rowOnePanel.add(lNameField);
      
   }
   private void buildRowTwo()
   {
      rowTwoPanel = new BitsPleaseMMPanel();
      rowTwoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      streetAddress = new JLabel("Street Address:       "); 
      sAddressField = new JTextField(52);
      rowTwoPanel.add(streetAddress);
      rowTwoPanel.add(sAddressField);
             
   }
   private void buildRowThree()
   {
      rowThreePanel = new BitsPleaseMMPanel();
      rowThreePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      String[] statesAbbrv = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI",
                              "ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI",
                              "MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC",
                              "NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
                              "UT","VT","VA","WA","WV","WI","WY"};
      stateBox = new JComboBox<String>(statesAbbrv);
      stateBox.addActionListener(new ComboBoxListener());
      city = new JLabel("City:                        ");
      state = new JLabel("     State:   ");
      zipCode = new JLabel("   Zip Code:   ");
      cityField = new JTextField(20);
      zCodeField = new JTextField(10);
      rowThreePanel.add(city);
      rowThreePanel.add(cityField);
      rowThreePanel.add(state);
      rowThreePanel.add(stateBox);
      rowThreePanel.add(zipCode);
      rowThreePanel.add(zCodeField);
   }
   private void buildRowFour()
   {
      rowFourPanel = new BitsPleaseMMPanel();
      rowFourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      phone = new JLabel("Phone Number:       ");
      altPhone = new JLabel("      Alt. Number:      ");
      phoneField = new JTextField(12);
      aPhoneField = new JTextField(12);
      rowFourPanel.add(phone);
      rowFourPanel.add(phoneField);
      rowFourPanel.add(altPhone);
      rowFourPanel.add(aPhoneField);
   }
   private void buildRowFive()
   {
      Random random = new Random();
      emNumber = random.nextInt(99999 - 10000) + 10000;
      rowFivePanel = new BitsPleaseMMPanel();
      rowFivePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      String[] gender = {"female", "male", " ", " ", " ", " "};
      sexBox = new JComboBox<String>(gender);
      sexBox.addActionListener(new ComboBoxListener());
      genderLabel = new JLabel("Gender:           ");
      int i = 0;
      emNum = new JLabel("      Employee Number:   ");
      eNumField = new JTextField(Integer.toString(emNumber), 10);
      title = new JLabel("    Title:    ");
      titField = new JTextField(15);      
      rowFivePanel.add(genderLabel);
      rowFivePanel.add(sexBox);
      rowFivePanel.add(title);
      rowFivePanel.add(titField);
      rowFivePanel.add(emNum);
      rowFivePanel.add(eNumField);
   }
   private void buildRowSix()
   {
      rowSixPanel = new BitsPleaseMMPanel();
      rowSixPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      birthDate = new JLabel("Birth Date (yyyy-mm-dd):       ");
      hireDate = new JLabel("     Hire Date (yyyy-mm-dd):        ");
      bDateField = new JTextField(10);
      hDateField = new JTextField(10);
      rowSixPanel.add(birthDate);
      rowSixPanel.add(bDateField);
      rowSixPanel.add(hireDate);
      rowSixPanel.add(hDateField);
   }
   private void buildRowSeven()
   {
      rowSevenPanel = new BitsPleaseMMPanel();
      rowSevenPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      save = new JButton("Save");
      save.addActionListener(new ButtonListener());
      saveAndClose = new JButton("Save & Close");
      saveAndClose.addActionListener(new ButtonListener());
      addAnotherEmployee = new JButton("Add Another Employee");
      addAnotherEmployee.addActionListener(new ButtonListener());
      cancel = new JButton("Cancel");
      cancel.addActionListener(new ButtonListener());
      rowSevenPanel.add(save);     
      rowSevenPanel.add(saveAndClose);
      rowSevenPanel.add(addAnotherEmployee);
      rowSevenPanel.add(cancel);
   }
   private boolean checkDate(String s)
   {
      //not doing Leap Years
      int month = Integer.parseInt(s.substring(5,7));
      int day = Integer.parseInt(s.substring(8,10));
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
      return true;
   }
   private class ComboBoxListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        sta = (String) stateBox.getSelectedItem();
        gen = (String) sexBox.getSelectedItem();
              
      }
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
               Date date = dateFormat.parse(bDateField.getText());
               date = dateFormat.parse(hDateField.getText());
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(checkDate(bDateField.getText())) || !(checkDate(hDateField.getText())))
            {
               return;
            }
            setVisible(false);
            dispose();
            BitsPleaseAddress ad = new BitsPleaseAddress(sAddressField.getText().trim(), cityField.getText().trim(),
                                    sta, zCodeField.getText().trim(), phoneField.getText().trim(),aPhoneField.getText().trim());
            BitsPleaseEmployee em = new BitsPleaseEmployee(fNameField.getText().trim(), lNameField.getText().trim(), ad, 
                                    Integer.toString(emNumber).trim(), bDateField.getText().trim(), hDateField.getText().trim(),
                                    titField.getText().trim(), gen);

           em.insertToDB();
           try
           {
               
               BitsPleaseNewEmployee newE = new BitsPleaseNewEmployee();
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
               Date date = dateFormat.parse(bDateField.getText());
               date = dateFormat.parse(hDateField.getText());
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(checkDate(bDateField.getText())) || !(checkDate(hDateField.getText())))
            {
               return;
            }
            
            setVisible(false);
            dispose();
            BitsPleaseAddress ad = new BitsPleaseAddress(sAddressField.getText().trim(), cityField.getText().trim(),
                                    sta, zCodeField.getText().trim(), phoneField.getText().trim(),
                                    aPhoneField.getText().trim());
            BitsPleaseEmployee em = new BitsPleaseEmployee(fNameField.getText().trim(), lNameField.getText().trim(), ad, 
                                    Integer.toString(emNumber).trim(), bDateField.getText().trim(), hDateField.getText().trim(),
                                    titField.getText().trim(), gen);
            
           em.insertToDB();
           try
           {
               BitsPleaseMainMenu mM = new BitsPleaseMainMenu();
           } 
           catch (Exception x)
           {
            System.out.println(x);
           }
         }
         if (actionCommand.equals("Add Another Employee"))
         {
            setVisible(false);
            dispose();
            try
           {
               BitsPleaseNewEmployee newEm = new BitsPleaseNewEmployee();
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