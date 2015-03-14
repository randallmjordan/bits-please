import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.Random;

public class BitsPleaseExisitingMember extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton save, saveAndClose, addAnotherMember, cancel;
   private JList<String> memList;
   private JScrollPane scroll;
   private JComboBox<String> stateBox, memOptionBox;
   private JLabel pageLabel,firstName, lastName, birthDate, streetAddress, city, zipCode, phone, state, memOption,altPhone, memNum;
   private JPanel listPanel, topPanel,rowZeroPanel,rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel,
           rowSixPanel,testPanel;
   private JTextField fNameField, lNameField, bDateField, sAddressField, cityField, zCodeField, phoneField, aPhoneField, mNumField;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   
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
                 
      add(listPanel);
      //add(topPanel);
      add(rowZeroPanel);
      add(rowOnePanel);
      add(rowTwoPanel);
      add(rowThreePanel);
      add(rowFourPanel);
      add(rowFivePanel);
      add(rowSixPanel);    
      setVisible(true);
   }
   private void buildMemList()
   {  
      /*String[] names={
                        "Bunny Buggs",  
                        "Cat, Sylvester ",      
                        "Coyote, Willie E. ",   
                        "Devil, Tasmanian",     
                        "Duck, Daffy",  
                        "Fudd, Elmer",  
                        "LePew, Pepe",  
                        "Martian, MArvin"};*/
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
      Random random = new Random();
      int memNumber = random.nextInt(999999 - 100000) + 100000;
      rowFivePanel = new JPanel();
      rowFivePanel.setBackground(new Color(255,229,153));
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
      rowSixPanel.setBackground(new Color(255,229,153));
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
               st = rs.getString(6);
               zCode = rs.getString(7);
               ph = rs.getString(8);
               aPH = rs.getString(9);
               
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
         
      }
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
}