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
   private JButton returnHome;
   private JPanel listPanel, contactPanel, otherPanel;
   private JTextField fNameField, lNameField, sAddressField, cityField,  zCodeField, phoneField,
           aPhoneField, eNumField, bDateField, hDateField, titField;
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
         /*fNameField.setText(fN);
         lNameField.setText(lN);
         sAddressField.setText(ad);
         cityField.setText(city);
         stateBox.setSelectedItem(st);
         zCodeField.setText(zCode);
         phoneField.setText(ph);
         hPhoneField.setText(aPH);
         NumField.setText(mNum);
         titField.setText(tit);
         bDateField.setText(bD);
         hDateField.setText(hD);
         sexBox.setSelectedItem(sx);*/
      }
   }
}