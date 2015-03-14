import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.Random;

public class BitsPleaseNewMember extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton save, saveAndClose, addAnotherMember, cancel;
   private JLabel pageLabel, firstName, lastName, birthDate, streetAddress, city, zipCode, phone, state, memOption,altPhone, memNum;
   private JTextField fNameField, lNameField, bDateField, sAddressField, cityField, zCodeField, phoneField, aPhoneField, mNumField;
   private JComboBox<String> stateBox, memOptionBox;
   private BitsPleaseMMPanel newMemPanel,rowZeroPanel, rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel,
           rowSixPanel, newMemPanelb;
   private int memNumber;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private String sta = "";
   public BitsPleaseNewMember() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - New Member Form");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildNewMemPanel();
      newMemPanel.setBounds(60,495,934,158);
      newMemPanelb.setBounds(20,120,40,608);
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
      rowSixPanel.setBounds(60,653,934,75);
      
      add(newMemPanel);
      add(newMemPanelb);
      add(rowZeroPanel);
      add(rowOnePanel);
      add(rowTwoPanel);
      add(rowThreePanel);
      add(rowFourPanel);
      add(rowFivePanel);
      add(rowSixPanel);
      
      setVisible(true);
   }
   
   public void buildNewMemPanel()
   {
      newMemPanel = new BitsPleaseMMPanel();
      newMemPanel.setPreferredSize(new Dimension(974,158));
      newMemPanelb = new BitsPleaseMMPanel();
      newMemPanel.setPreferredSize(new Dimension(40,608));
      
             
      
   }
   private void buildRowZero()
   {
      rowZeroPanel = new BitsPleaseMMPanel();
      pageLabel = new JLabel("Create New Member");
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
      memNumber = random.nextInt(999999 - 100000) + 100000;
      rowFivePanel = new BitsPleaseMMPanel();
      rowFivePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      String[] stuff={"getRidofThis", "Or Don't You Twat", "WTF is going on", "don't fuck me box"};
      memOptionBox = new JComboBox<String>(stuff);
      memNum = new JLabel("             Member Number:   ");
      memOption = new JLabel("Membership Option:    ");
      mNumField = new JTextField(Integer.toString(memNumber), 10);
      rowFivePanel.add(memOption);
      rowFivePanel.add(memOptionBox);
      rowFivePanel.add(memNum);
      rowFivePanel.add(mNumField);
   }
   private void buildRowSix()
   {
      rowSixPanel = new BitsPleaseMMPanel();
      rowSixPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      save = new JButton("Save");
      save.addActionListener(new ButtonListener());
      saveAndClose = new JButton("Save & Close");
      saveAndClose.addActionListener(new ButtonListener());
      addAnotherMember = new JButton("Add Another Member");
      addAnotherMember.addActionListener(new ButtonListener());
      cancel = new JButton("Cancel");
      cancel.addActionListener(new ButtonListener());
      rowSixPanel.add(save);     
      rowSixPanel.add(saveAndClose);
      rowSixPanel.add(addAnotherMember);
      rowSixPanel.add(cancel);
   }
   private class ComboBoxListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        sta = (String) stateBox.getSelectedItem(); 
      }
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand = e.getActionCommand();
         
         if (actionCommand.equals("Save"))
         {
            setVisible(false);
            dispose();
           try
           {
               
               BitsPleaseNewMember newMem = new BitsPleaseNewMember();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Save & Close"))
         {
            setVisible(false);
            dispose();
            try
           {
               Statement stmt  = BitsPlease.conn.createStatement();;
               stmt.execute("INSERT INTO  Members Values ('" +
                     Integer.toString(memNumber).trim() + "','" + fNameField.getText().trim() + "','" + lNameField.getText().trim() + "','" +
                     sAddressField.getText().trim() + "','" + cityField.getText().trim() + "'," + sta + ",'" +
                     zCodeField.getText().trim() + "','" + phoneField.getText().trim() + "','" +
                     aPhoneField.getText().trim() + "','member')" );

               BitsPleaseMainMenu mM = new BitsPleaseMainMenu();
           } 
           catch (Exception x)
           {
            System.out.println(x);
           }
         }
         if (actionCommand.equals("Add Another Member"))
         {
            setVisible(false);
            dispose();
            try
           {
               BitsPleaseNewMember newMem = new BitsPleaseNewMember();
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