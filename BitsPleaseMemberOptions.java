/**
   The BitsPleaseMemberOpitions class creates teh GUI that is where a user will enter new information
   pertaining to membership types, where the user can edit said information.
*/

import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import java.text.*;

public class BitsPleaseMemberOptions extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JList<String> planList;
   private JScrollPane scroll;
   private JLabel pageLabel, planName, planDesc, planCost, planStartDate, planEndDate;
   private JPanel listPanel,rowZeroPanel, rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel, fillerPanel;
   private JTextField planNameField, planDescField, planCostField, planSDateField, planEDateField; 
   private JButton createNew, save, close;
   private String value = "";
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private String[] plans = null;
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   public BitsPleaseMemberOptions() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Membership Options");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildPlanList();
      listPanel.setBounds(20,20,200,708);
      buildRowZero();
      rowZeroPanel.setBounds(220,20,784,100);
      buildRowOne();
      rowOnePanel.setBounds(220,120,784,75);
      buildRowTwo();
      rowTwoPanel.setBounds(220,195,784,75);
      buildRowThree();
      rowThreePanel.setBounds(220,270,784,75);
      buildRowFour();
      rowFourPanel.setBounds(220,345,784,75);
      buildRowFive();
      rowFivePanel.setBounds(220,653,784,75);
      buildFillerPanel();
      fillerPanel.setBounds(220,420,784,233);
      
      add(listPanel);
      add(rowZeroPanel);
      add(rowOnePanel);
      add(rowTwoPanel);
      add(rowThreePanel);
      add(rowFourPanel);
      add(rowFivePanel);
      add(fillerPanel);
      
      setVisible(true);
   }
   private void buildPlanList()
   {
      int i = 0;
      ResultSet rs = null;
      
      try
      {
         Statement stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM MemPlans");
         while (rs.next())
         {
            plans = new String[rs.getInt(1)];
         }
         stmt.close();
         stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT plan_name FROM MemPlans ORDER BY plan_name");
         while (rs.next())
         {
            plans[i] = rs.getString(1).trim();
            i++;
         }
         planList = new JList<String>(plans);
      }
      catch(SQLException e)
      {
         System.out.println("Plan list error: " + e);
      }
      planList.setBackground(new Color(255,229,153));
      planList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      planList.addListSelectionListener(new ListListener());
      scroll = new JScrollPane(planList);
      scroll.getViewport().setBackground(new Color(255,229,153));
      scroll.getViewport().setOpaque(false);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      listPanel = new JPanel();
      listPanel.setBackground(new Color(255,229,153));
      listPanel.setLayout(new BorderLayout());
      
      listPanel.add(scroll, BorderLayout.CENTER);
   }
   private void buildRowZero()
   {
      rowZeroPanel = new JPanel();
      rowZeroPanel.setBackground(new Color(255,229,153));
      pageLabel = new JLabel("Membership Plan MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
      rowZeroPanel.add(pageLabel);
   }
   private void buildRowOne()
   {
      rowOnePanel = new JPanel();
      rowOnePanel.setBackground(new Color(255,229,153));
      rowOnePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      planName = new JLabel("Plan Name:                 ");
      planNameField =new JTextField(50);
      rowOnePanel.add(planName);
      rowOnePanel.add(planNameField);
   }
   private void buildRowTwo()
   {
      rowTwoPanel = new JPanel();
      rowTwoPanel.setBackground(new Color(255,229,153));
      rowTwoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      planDesc = new JLabel("Plan Description:        ");
      planDescField = new JTextField(50);
      rowTwoPanel.add(planDesc);
      rowTwoPanel.add(planDescField); 
   }
   private void buildRowThree()
   {
      rowThreePanel = new JPanel();
      rowThreePanel.setBackground(new Color(255,229,153));
      rowThreePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      planCost = new JLabel("Montly Cost:                ");
      planCostField = new JTextField(15);
      rowThreePanel.add(planCost);
      rowThreePanel.add(planCostField);
      
   }
   private void buildRowFour()
   {
      rowFourPanel = new JPanel();
      rowFourPanel.setBackground(new Color(255,229,153));
      rowFourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      planStartDate = new JLabel("Start Date (yyyy-mm-dd):       ");
      planEndDate = new JLabel("       End Date (yyyy-mm-dd)   ");
      planSDateField = new JTextField(15);
      planEDateField = new JTextField(15);
      rowFourPanel.add(planStartDate);
      rowFourPanel.add(planSDateField);
      rowFourPanel.add(planEndDate);
      rowFourPanel.add(planEDateField);
   }
   private void buildRowFive()
   {
      rowFivePanel = new JPanel();
      rowFivePanel.setBackground(new Color(255,229,153));
      rowFivePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      save = new JButton("Save");
      save.addActionListener(new ButtonListener());
      createNew = new JButton("Create New");
      createNew.addActionListener(new ButtonListener());
      close = new JButton("Close");
      close.addActionListener(new ButtonListener());
      rowFivePanel.add(createNew);     
      rowFivePanel.add(save);
      rowFivePanel.add(close);
   }
   private void buildFillerPanel()
   {
      fillerPanel = new JPanel();
      fillerPanel.setBackground(new Color(255,229,153));
   }
   private class ListListener implements ListSelectionListener
   {
      public void valueChanged(ListSelectionEvent e)
      {
         value = planList.getSelectedValue();
         String pDesc = "";
         String pCost = "";
         String pSDate = "";
         String pEDate = "";
         ResultSet rs = null;
         
         try
         {
            Statement stmt = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT * FROM MemPlans WHERE plan_name ='" + value + "'");
            
            while (rs.next())
            {
               pDesc = rs.getString(2);
               pCost = rs.getString(3);
               pSDate = rs.getString(4);
               pEDate = rs.getString(5);
            }
         }
         catch (SQLException ex)
         {
            System.out.println("Plan Select error: " + ex);
         }
         planNameField.setText(value);
         planDescField.setText(pDesc);
         planCostField.setText(pCost);
         planSDateField.setText(pSDate);
         planEDateField.setText(pEDate);
      }
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand = e.getActionCommand();
         if (actionCommand.equals("Create New"))
         {
            planNameField.setText("");
            planDescField.setText("");
            planCostField.setText("");
            planSDateField.setText("");
            planEDateField.setText("");
         }
         else if (actionCommand.equals("Save"))
         {
            try
            {
               Statement stmt = BitsPlease.conn.createStatement();;
               try
            {
               Date date = dateFormat.parse(planSDateField.getText());
               date = dateFormat.parse(planEDateField.getText());
            }
            catch (ParseException p)
            {
               JOptionPane.showMessageDialog(null, "Please use date format yyyy-dd-mm","ILLEGAL FORMAT", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (!(BitsPleaseDateCheck.checkDates(planSDateField.getText())) || !(BitsPleaseDateCheck.checkDates(planEDateField.getText())))
            {
               return;
            }
               if (Arrays.asList(plans).contains(planNameField.getText().trim()))
               {
                  stmt.execute("UPDATE MemPlans SET plan_name ='" + planNameField.getText().trim() +
                               "', description ='" + planDescField.getText().trim() +
                               "', cost ='" + planCostField.getText().trim() +
                               "', start_date ='" + planSDateField.getText().trim() +
                               "', end_date ='" + planEDateField.getText().trim() +
                               "' WHERE plan_name ='" + value + "'");
                 
               }
               else
               {
                    stmt.execute("INSERT INTO MemPlans VALUES ('" + planNameField.getText().trim() +
                               "','" + planDescField.getText().trim() +
                               "','" + planCostField.getText().trim() +
                               "','" + planSDateField.getText().trim() +
                               "','" + planEDateField.getText().trim() +
                               "')");
                    setVisible(false);
                    dispose();
                    try
                    {
                        BitsPleaseMemberOptions memOpts = new BitsPleaseMemberOptions();
                    }
                    catch (Exception k)
                    {
                    }
               }
            }
            catch (SQLException ee)
            {
               System.out.println("Plan change/save error: " + ee);
            }
         }
         else if (actionCommand.equals("Close"))
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