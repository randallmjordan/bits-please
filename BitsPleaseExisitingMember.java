/**
   The BitsPleaseExistingMember class creates to GUI that allows the user to manage info pertaining
   to Gym Members. 
*/
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.text.*;
import java.util.Date;

public class BitsPleaseExisitingMember extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton save, edit, close;
   private JList<String> memList;
   private JScrollPane scroll;
   private JComboBox<String> stateBox, sexBox, memOptionBox;
   private JLabel pageLabel,genderLabel, firstName, lastName, birthDate, streetAddress, city, zipCode, phone, state, memOption,
           altPhone, memNum, activeDate;
   private JPanel listPanel, topPanel,rowZeroPanel,rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel,
           rowSixPanel,datesPanel, billPanel;
   private JTextField fNameField, lNameField,
    sAddressField, cityField, zCodeField, phoneField, aPhoneField, 
           mNumField, bDateField, aDateField;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   public BitsPleaseExisitingMember() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Exisiting Members");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildMemList();
      listPanel.setBounds(20,20,200,708);
      
      buildPane();
      //topPanel.setBounds(170,20,824,708);
      buildRowZero();
      rowZeroPanel.setBounds(220, 20, 784, 100);
      buildRowOne();
      rowOnePanel.setBounds(220,120,784,75);
      buildRowTwo();
      rowTwoPanel.setBounds(220,195,784,75);
      buildRowThree();
      rowThreePanel.setBounds(220,270,784,75);
      buildRowFour();
      rowFourPanel.setBounds(220,345,784,75);
      buildRowFive();
      rowFivePanel.setBounds(220,420,784,75);
      buildRowSix();
      rowSixPanel.setBounds(220,653,784,75);
      buildDatesPanel();
      datesPanel.setBounds(220, 495,784,75);
      buildBillPanel();
      billPanel.setBounds(220,570,784,83);
                 
      add(listPanel);
      //add(topPanel);
      add(rowZeroPanel);
      add(rowOnePanel);
      add(rowTwoPanel);
      add(rowThreePanel);
      add(rowFourPanel);
      add(rowFivePanel);
      add(rowSixPanel);
      add(billPanel); 
      add(datesPanel);   
      setVisible(true);
   }
   private void buildMemList()
   {  
       String[] names = null;
       int i = 0;  
         ResultSet rs = null;
         ResultSet rs2 = null;
         try
         {
            Statement stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM Members");
            while (rs.next())
            {
               names = new String[rs.getInt(1)];
            }
            stmt.close();
            stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT lastName, firstName, memID FROM Members ORDER BY lastName");
            while(rs.next())
            {
               String str = rs.getString(1).trim() + ", " + rs.getString(2).trim() + 
                            " (" + rs.getString(3).trim() + ")";
               
               names[i] = str;
               i++;
            }
           memList = new JList<String>(names);
         }
         catch(SQLException ex)
         {
            System.out.println(ex);
         }
                        
      //memList = new JList<String>(names);
      memList.setOpaque(false);
      memList.setBackground(new Color(255,229,153));
      memList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      memList.addListSelectionListener(new ListListener());
      scroll = new JScrollPane(memList);
      scroll.setOpaque(false);
      scroll.getViewport().setBackground(new Color(255,229,153));
      scroll.getViewport().setOpaque(false);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      listPanel = new JPanel();
      listPanel.setBackground(new Color(255,229,153));
      listPanel.setLayout(new BorderLayout());
      
      
      listPanel.add(scroll,BorderLayout.CENTER);
   }
   private void buildDatesPanel()
   {
      datesPanel = new JPanel();
      datesPanel.setBackground(new Color(255,229,153));
      datesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      birthDate = new JLabel("Birth Date:          ");
      activeDate = new JLabel("     Active Date:       ");
      bDateField = new JTextField(10);
      aDateField = new JTextField(10);
      datesPanel.add(birthDate);
      datesPanel.add(bDateField);
      datesPanel.add(activeDate);
      datesPanel.add(aDateField);
      
   }
   private void buildPane()
   {
     topPanel = new JPanel();
         
     topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));     
            
   }
   private void buildRowZero()
   {
      rowZeroPanel = new BitsPleaseMMPanel();
      rowZeroPanel.setBackground(new Color(255,229,153));
      pageLabel = new JLabel("Member MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
      
      rowZeroPanel.add(pageLabel);
   }
   private void buildRowOne()
   {
      rowOnePanel = new JPanel();
      rowOnePanel.setBackground(new Color(255,229,153));
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
      rowTwoPanel = new JPanel();
      rowTwoPanel.setBackground(new Color(255,229,153));
      rowTwoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      streetAddress = new JLabel("Street Address:       "); 
      sAddressField = new JTextField(52);
      rowTwoPanel.add(streetAddress);
      rowTwoPanel.add(sAddressField);
             
   }
   private void buildRowThree()
   {
      rowThreePanel = new JPanel();
      rowThreePanel.setBackground(new Color(255,229,153));
      rowThreePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      String[] statesAbbrv = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI",
                              "ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI",
                              "MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC",
                              "NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX",
                              "UT","VT","VA","WA","WV","WI","WY"};
      stateBox = new JComboBox<String>(statesAbbrv);
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
      rowFourPanel = new JPanel();
      rowFourPanel.setBackground(new Color(255,229,153));
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
      rowFivePanel = new JPanel();
      rowFivePanel.setBackground(new Color(255,229,153));
      rowFivePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      String[] gender = {"male", "female", " ", " ", " "};
      sexBox = new JComboBox<String>(gender);
      genderLabel = new JLabel("Gender:         ");
      String[] stuff= null; 
      int i = 0;
      ResultSet rs = null;
      try
      {
         Statement stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM MemPlans");
         while (rs.next())
         {
            stuff = new String[rs.getInt(1)];
         }
         stmt.close();
         stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT plan_name FROM MemPlans ORDER BY plan_name");
         while(rs.next())
         {
            stuff[i]=rs.getString(1).trim();
            i++;
         }
         memOptionBox = new JComboBox<String>(stuff);
      }
      catch(SQLException e)
      {
         System.out.println("        MemPlans Select: ");
      }
      
      memNum = new JLabel("        Member Number:   ");
      memOption = new JLabel("      Membership Option:    ");
      mNumField = new JTextField(10);
      rowFivePanel.add(genderLabel);
      rowFivePanel.add(sexBox);
      rowFivePanel.add(memOption);
      rowFivePanel.add(memOptionBox);
      rowFivePanel.add(memNum);
      rowFivePanel.add(mNumField);
   }
   private void buildRowSix()
   {
      rowSixPanel = new BitsPleaseMMPanel();
      rowSixPanel.setBackground(new Color(255,229,153));
      rowSixPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      save = new JButton("Save");
      save.addActionListener(new ButtonListener());
      edit = new JButton("Edit");
      //edit.addActionListener(new ButtonListener());
      close = new JButton("Close");
      close.addActionListener(new ButtonListener());
      rowSixPanel.add(edit);     
      rowSixPanel.add(save);
      rowSixPanel.add(close);
   }
   private void buildBillPanel()
   {
      billPanel = new JPanel();
      billPanel.setBackground(new Color(255,229,153));
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

   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         String value = memList.getSelectedValue();
         int splitSpot = value.indexOf("(")+1;
         value = value.substring(splitSpot, splitSpot + 6);
         String fN = "";
         String lN = "";
         String ad = "";
         String city = "";
         String st = "";
         String zCode = "";
         String ph = "";
         String aPH = "";
         String mNum ="";
         String mOption = "";
         String bD = "";
         String aD = "";
         String sx = "";
         ResultSet rs = null;
         
         try
         {
            Statement stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT * FROM Members WHERE memID = '"
                                    + value + "'"); 
            
            while(rs.next())
            {
               mNum = rs.getString(1);
               fN = rs.getString(2);
               lN = rs.getString(3);
               ad = rs.getString(4);
               city = rs.getString(5);
               st = rs.getString(6).trim();
               zCode = rs.getString(7);
               ph = rs.getString(8);
               aPH = rs.getString(9);
               mOption = rs.getString(10).trim();
               bD = rs.getString(11).trim();
               aD = rs.getString(12).trim();
               sx = rs.getString(13).trim();
            }
         }
         catch(SQLException ex)
         {
            System.out.println(ex);
         }
         fNameField.setText(fN);
         lNameField.setText(lN);
         sAddressField.setText(ad);
         cityField.setText(city);
         stateBox.setSelectedItem(st);
         zCodeField.setText(zCode);
         phoneField.setText(ph);
         aPhoneField.setText(aPH);
         mNumField.setText(mNum);
         memOptionBox.setSelectedItem(mOption);
         bDateField.setText(bD);
         aDateField.setText(aD);
         sexBox.setSelectedItem(sx);
      }
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
         String actionCommand = e.getActionCommand();
         if (actionCommand.equals("Edit"))
         {
         
         }
         else if (actionCommand.equals("Save"))
         {
            try
            {
               Date date = dateFormat.parse(bDateField.getText());
               date = dateFormat.parse(aDateField.getText());
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(checkDate(bDateField.getText())) || !(checkDate(aDateField.getText())))
            {
               return;
            }

           try
           {
               Statement stmt  = BitsPlease.conn.createStatement();;
               stmt.execute("UPDATE Members SET firstName ='" + fNameField.getText().trim() +
                            "', lastName ='" + lNameField.getText().trim() +
                            "', address ='" + sAddressField.getText().trim() +
                            "', city ='" + cityField.getText().trim() +
                            "', state ='" + stateBox.getSelectedItem().toString() +
                            "', zipCode ='" + zCodeField.getText().trim() +
                            "', phone ='" + phoneField.getText().trim() +
                            "', aPhone ='" + aPhoneField.getText().trim() + 
                            "', memOption ='" + memOptionBox.getSelectedItem().toString() +
                            "', bDate ='" + bDateField.getText().trim() +
                            "', aDate ='" + aDateField.getText().trim() +
                            "', sex ='" + sexBox.getSelectedItem().toString() +
                            "' WHERE memID ='" + mNumField.getText().trim() + "'");
              
           } 
           catch (Exception x)
           {
               System.out.println("Update member:" + x);
           }

            
         }
         else if(actionCommand.equals("Close"))
         {
               setVisible(false);
               dispose();
               
               try
               {
                  BitsPleaseMainMenu menu = new BitsPleaseMainMenu();
               } 
               catch (Exception x)
               {
              
               }

         }
      }  
   }
}