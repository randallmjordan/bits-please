/**
   The BitsPleaseEmployeeRecords creates the GUI for dealing with exisitng Employees.
   The Gui is tabbed to show Employee contact info on opening, and second tab is set 
   to contain other pertinent info related to the Employee.
   This class is accessed from BitsPleaseMainMenu.
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
import java.util.*;

public class BitsPleaseEmployeeRecords extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton returnHome, save, edit, close ;
   private JPanel listPanel, contactPanel, otherPanel, topPanel,rowZeroPanel,rowOnePanel, rowTwoPanel, rowThreePanel, 
           rowFourPanel, rowFivePanel, rowSixPanel,datesPanel;
   private JTextField fNameField, lNameField, sAddressField, cityField,  zCodeField, phoneField,
           aPhoneField, eNumField, bDateField, hDateField, titField;
   private JLabel pageLabel,genderLabel, firstName, lastName, birthDate, streetAddress, city, zipCode, phone, state, 
           altPhone, emNum, hireDate, title;
   private JComboBox<String> sexBox, stateBox;
   private JList<String> emList;
   private JScrollPane scroll;
   private JTabbedPane tabPane;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
   public BitsPleaseEmployeeRecords() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Employees Records");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildEmList();
      listPanel.setBounds(20,20,200,708);
      
      buildTab();
      tabPane.setBounds(220,20,784,708);
      
      add(listPanel);
      add(tabPane);
      
      setVisible(true);
   }
   private void buildTab()
   {
      tabPane = new JTabbedPane();
      buildContactPanel();
      buildOtherPanel();
      
      tabPane.addTab("Contact", contactPanel);
      tabPane.addTab("Other Infor", otherPanel);
   }
   private void buildContactPanel()
   {
      contactPanel = new JPanel();
      contactPanel.setBackground(new Color(255,229,153));
      topPanel = new JPanel();
      topPanel.setBackground(new Color(255,229,153));
      topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      contactPanel.setLayout(new BorderLayout());
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
      
      topPanel.add(rowOnePanel);
      topPanel.add(rowTwoPanel);
      topPanel.add(rowThreePanel);
      topPanel.add(rowFourPanel);
      topPanel.add(rowFivePanel);
      topPanel.add(datesPanel);
      contactPanel.add(topPanel,BorderLayout.CENTER);
      contactPanel.add(rowSixPanel,BorderLayout.SOUTH);
   }
   private void buildDatesPanel()
   {
      datesPanel = new JPanel();
      datesPanel.setBackground(new Color(255,229,153));
      datesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      birthDate = new JLabel("Birth Date:          ");
      hireDate = new JLabel("     Active Date:       ");
      bDateField = new JTextField(10);
      hDateField = new JTextField(10);
      datesPanel.add(birthDate);
      datesPanel.add(bDateField);
      datesPanel.add(hireDate);
      datesPanel.add(hDateField);
      
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
      emNum = new JLabel("        Employee Number:   ");
      eNumField = new JTextField(10);
      titField = new JTextField(15);
      title = new JLabel("   Title:   ");
      rowFivePanel.add(genderLabel);
      rowFivePanel.add(sexBox);
      rowFivePanel.add(emNum);
      rowFivePanel.add(eNumField);
      rowFivePanel.add(title);
      rowFivePanel.add(titField);
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
   private void buildOtherPanel()
   {
      otherPanel = new JPanel();
      otherPanel.setBackground(new Color(255,229,153));
      
      otherPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      returnHome = new JButton("Main Menu");
      returnHome.addActionListener(new ButtonListener());
      
      otherPanel.add(returnHome);
   }
   private void buildEmList()
   {  
         String[] names = null;
         int i = 0;  
         ResultSet rs = null;
         ResultSet rs2 = null;
         try
         {
            Statement stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM Employees");
            while (rs.next())
            {
               names = new String[rs.getInt(1)];
            }
            stmt.close();
            stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT lastName, firstName, emID FROM Employees ORDER BY lastName");
            while(rs.next())
            {
               String str = rs.getString(1).trim() + ", " + rs.getString(2).trim() + 
                            " (" + rs.getString(3).trim() + ")";
               
               names[i] = str;
               i++;
            }
           emList = new JList<String>(names);
         }
         catch(SQLException ex)
         {
            System.out.println(ex);
         }
                        
      //memList = new JList<String>(names);
      emList.setOpaque(false);
      emList.setBackground(new Color(255,229,153));
      emList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      emList.addListSelectionListener(new ListListener());
      scroll = new JScrollPane(emList);
      scroll.setOpaque(false);
      scroll.getViewport().setBackground(new Color(255,229,153));
      scroll.getViewport().setOpaque(false);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      listPanel = new JPanel();
      listPanel.setBackground(new Color(255,229,153));
      listPanel.setLayout(new BorderLayout());
      
      
      listPanel.add(scroll,BorderLayout.CENTER);
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         setVisible(false);
         dispose();
         //JOptionPane.showMessageDialog(null, "Good Job, " + userNameField.getText());
        try
        {
            BitsPleaseMainMenu menu = new BitsPleaseMainMenu();
        } 
        catch (Exception x)
        {
        
        }
      }  
   }
   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         String value = emList.getSelectedValue();
         int splitSpot = value.indexOf("(")+1;
         value = value.substring(splitSpot, splitSpot + 5);
         String fN = "";
         String lN = "";
         String ad = "";
         String city = "";
         String st = "";
         String zCode = "";
         String ph = "";
         String aPH = "";
         String eNum ="";
         String bD = "";
         String hD = "";
         String tit = "";
         String sx = "";
         ResultSet rs = null;
         
         try
         {
            Statement stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT * FROM Employees WHERE emID = '"
                                    + value + "'"); 
            
            while(rs.next())
            {
               eNum = rs.getString(1);
               fN = rs.getString(2);
               lN = rs.getString(3);
               ad = rs.getString(4);
               city = rs.getString(5);
               st = rs.getString(6).trim();
               zCode = rs.getString(7);
               ph = rs.getString(8);
               aPH = rs.getString(9);
               bD = rs.getString(10).trim();
               hD = rs.getString(11).trim();
               tit = rs.getString(12).trim();
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
         eNumField.setText(eNum);
         titField.setText(tit);
         bDateField.setText(bD);
         hDateField.setText(hD);
         sexBox.setSelectedItem(sx);
      }
   }
}