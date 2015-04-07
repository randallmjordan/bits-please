/**
   The BitsPleaseEquipmentRecords creates the GUI for dealing with exisitng Equipment.
   This class is accessed from BitsPleaseEquipmentMGMT
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

public class BitsPleaseEquipmentRecords extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JButton returnHome, save, edit, close;
   private JPanel topPanel, listPanel, rowZeroPanel, rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel,
            rowSixPanel, buttonPanel;
   private JLabel pageLabel,eqName, eqType, eqID, eqPDate, eqCost, eqLife, eqLastMaint, eqNextMaint;
   private JTextField nameField, typeField, idField, pDateField, costField, lifeField, nextMaintField, lastMaintField;
   private JList<String> eqList;
   private JScrollPane scroll;
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   
   public BitsPleaseEquipmentRecords() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Equipment Records");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildEqList();
      listPanel.setBounds(20,20,200,708);
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
      rowFivePanel.setBounds(220,420,784,233);
      buildButtonPanel();
      buttonPanel.setBounds(220,653,784,75);
                  
      add(listPanel);
      add(rowZeroPanel);
      add(rowOnePanel);
      add(rowTwoPanel);
      add(rowThreePanel);
      add(rowFourPanel);
      add(rowFivePanel);
      add(buttonPanel);
      
      setVisible(true);
   }
   private void buildRowZero()
   {
      rowZeroPanel = new BitsPleaseMMPanel();
      rowZeroPanel.setBackground(new Color(255,229,153));
      pageLabel = new JLabel("Equipment MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
      
      rowZeroPanel.add(pageLabel);
   }
   private void buildRowOne()
   {
      rowOnePanel = new JPanel();
      rowOnePanel.setBackground(new Color(255,229,153));
      rowOnePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      eqName = new JLabel("Equipment Name:       ");
      nameField = new JTextField(25);
      rowOnePanel.add(eqName);
      rowOnePanel.add(nameField);
      
   }
   private void buildRowTwo()
   {
      rowTwoPanel = new JPanel();
      rowTwoPanel.setBackground(new Color(255,229,153));
      rowTwoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      eqType = new JLabel("Equipment Type:        ");
      typeField = new JTextField(25);
      rowTwoPanel.add(eqType);
      rowTwoPanel.add(typeField);
   }
   private void buildRowThree()
   {
      rowThreePanel = new JPanel();
      rowThreePanel.setBackground(new Color(255,229,153));
      rowThreePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      eqID = new JLabel("Equipment ID:            ");
      idField = new JTextField(10);
      rowThreePanel.add(eqID);
      rowThreePanel.add(idField);
   }
   private void buildRowFour()
   {
      rowFourPanel = new JPanel();
      rowFourPanel.setBackground(new Color(255,229,153));
      rowFourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      eqPDate = new JLabel("Purchase Date:       ");
      eqCost = new JLabel("      Cost:      ");
      eqLife = new JLabel("    Expected Life:     ");
      pDateField = new JTextField(12);
      costField = new JTextField(12);
      lifeField = new JTextField(12);
      rowFourPanel.add(eqPDate);
      rowFourPanel.add(pDateField);
      rowFourPanel.add(eqCost);
      rowFourPanel.add(costField);
      rowFourPanel.add(eqLife);
      rowFourPanel.add(lifeField);
      
   }
   private void buildRowFive()
   {
      rowFivePanel = new JPanel();
      rowFivePanel.setBackground(new Color(255,229,153));
      rowFivePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      eqLastMaint = new JLabel("Last Maintenance:           ");
      eqNextMaint = new JLabel("     Next Maintenance:     ");
      lastMaintField = new JTextField(12);
      nextMaintField = new JTextField(12);      
      rowFivePanel.add(eqLastMaint);
      rowFivePanel.add(lastMaintField);
      rowFivePanel.add(eqNextMaint);
      rowFivePanel.add(nextMaintField);
      
   }
   private void buildButtonPanel()
   {
      buttonPanel = new JPanel();
      buttonPanel.setBackground(new Color(255,229,153));
      buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      save = new JButton("Save");
      save.addActionListener(new ButtonListener());
      edit = new JButton("Edit");
      //edit.addActionListener(new ButtonListener());
      close = new JButton("Close");
      close.addActionListener(new ButtonListener());
      buttonPanel.add(edit);     
      buttonPanel.add(save);
      buttonPanel.add(close);
   }
   private void buildEqList()
   {
         String[] eq = null;
         int i = 0;  
         ResultSet rs = null;
         ResultSet rs2 = null;
         try
         {
            Statement stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM Equipment");
            while (rs.next())
            {
               eq = new String[rs.getInt(1)];
            }
            stmt.close();
            stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT eqID, ename FROM Equipment ORDER BY eqID");
            while(rs.next())
            {
               String str = rs.getString(1).trim() + ", " + rs.getString(2).trim();
               
               eq[i] = str;
               i++;
            }
           eqList = new JList<String>(eq);
         }
         catch(SQLException ex)
         {
            System.out.println(ex);
         }
                        
      //memList = new JList<String>(names);
      eqList.setOpaque(false);
      eqList.setBackground(new Color(255,229,153));
      eqList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      eqList.addListSelectionListener(new ListListener());
      scroll = new JScrollPane(eqList);
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
         String actionCommand = e.getActionCommand();
         if (actionCommand.equals("Edit"))
         {
         
         }
         else if (actionCommand.equals("Save"))
         {
            try
            {
               Date date = dateFormat.parse(pDateField.getText());
               date = dateFormat.parse(nextMaintField.getText());
               date = dateFormat.parse(lastMaintField.getText());
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(BitsPleaseDateCheck.checkDates(pDateField.getText())) || !(BitsPleaseDateCheck.checkDates(nextMaintField.getText())) ||
                  !(BitsPleaseDateCheck.checkDates(lastMaintField.getText())))
            {
               return;
            }

           try
           {
               Statement stmt  = BitsPlease.conn.createStatement();;
               stmt.execute("UPDATE Equipment SET eName ='" + nameField.getText().trim() +
                            "', eType ='" + typeField.getText().trim() +
                            "', buyDate ='" + pDateField.getText().trim() +
                            "', lastMaintDate ='" + lastMaintField.getText().trim() +
                            "', nextMaintDate ='" + nextMaintField.getText().trim() +
                            "' WHERE eqID ='" + idField.getText().trim() +"'");
              
              setVisible(false);
               dispose();
              BitsPleaseEquipmentRecords eR = new BitsPleaseEquipmentRecords();
           } 
           catch (Exception x)
           {
               System.out.println("Update equipment:" + x);
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
   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         String value = eqList.getSelectedValue();
         value = value.substring(0,7);
         String eName = "";
         String eType = "";
         String eID = "";
         String pDate = "";
         double cost = 0;
         String lMaintDate = "";
         double eLife = 0;
         String nMaintDate = "";
         ResultSet rs = null;
         try
         {
            Statement stmt = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT * FROM Equipment WHERE eqID = '" +
                                    value  + "'");
            
            while (rs.next())
            {
               eID = rs.getString(1);
               eName = rs.getString(2);
               eType = rs.getString(3).trim();
               pDate = rs.getString(4);
               cost = rs.getDouble(5);
               lMaintDate = rs.getString(6);
               eLife = rs.getDouble(7);
               nMaintDate = rs.getString(8);
            }
         }
         catch (SQLException ex)
         {
            System.out.println("Value Changed error: " + ex);
         }
         
         nameField.setText(eName);
         typeField.setText(eType);
         idField.setText(eID);
         pDateField.setText(pDate);
         costField.setText(Double.toString(cost));
         lifeField.setText(Double.toString(eLife));
         nextMaintField.setText(nMaintDate);
         lastMaintField.setText(lMaintDate);
      }
   }
}