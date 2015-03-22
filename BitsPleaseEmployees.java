/**
   The BitsPleaseEmployees class creates teh GUI  that will direct user to either BitsPleaseNewEmployee,
   BitsPleaseEmployeeRecords, or BitsPleaseMainMenu.
*/

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class BitsPleaseEmployees extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private BitsPleaseMMPanel employeePanel, eCenterPanel, eTopPanel, eBottomPanel;  
   private BitsPleaseMMButton cEmployeeButton, nEmployeeButton, mainMenuButton;
   private JLabel pageLabel, optionLabel;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
      
   public BitsPleaseEmployees() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Employees");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      buildPageLabel();
      pageLabel.setBounds(280, 30, 600, 100);
      buildEmployeePanel();
      employeePanel.setBounds(379, 150, 266,333);
      
      add(pageLabel);
      add(employeePanel);
            
      setVisible(true);
   }
   private void buildPageLabel()
   {
      pageLabel = new JLabel("BP Employee MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
   }
   private void buildEmployeePanel()
   {
      cEmployeeButton = new BitsPleaseMMButton("Current Employees");
      cEmployeeButton.addActionListener(new ButtonListener());
      nEmployeeButton = new BitsPleaseMMButton("New Employee");
      nEmployeeButton.addActionListener(new ButtonListener());
      mainMenuButton = new BitsPleaseMMButton("Main Menu");
      mainMenuButton.addActionListener(new ButtonListener());
      employeePanel = new BitsPleaseMMPanel();
      eTopPanel = new BitsPleaseMMPanel();
      eCenterPanel = new BitsPleaseMMPanel();
      eBottomPanel = new BitsPleaseMMPanel();
      
      employeePanel.setLayout(new BorderLayout());
      employeePanel.setPreferredSize(new Dimension (266,290));
           
      eTopPanel.add(cEmployeeButton);
      employeePanel.add(eTopPanel, BorderLayout.NORTH);
      eCenterPanel.add(nEmployeeButton);
      employeePanel.add(eCenterPanel, BorderLayout.CENTER);
      eBottomPanel.add(mainMenuButton);
      employeePanel.add(eBottomPanel, BorderLayout.SOUTH);
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand = e.getActionCommand();
         
         if(actionCommand.equals("Main Menu"))
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
         else if (actionCommand.equals("New Employee"))
         {
            setVisible(false);
            dispose();
            
           try
           {
               BitsPleaseNewEmployee nEmp = new BitsPleaseNewEmployee();
           } 
           catch (Exception x)
           {
           
           }

         }
         else if (actionCommand.equals("Current Employees"))
         {
            setVisible(false);
            dispose();
            
            try
            {
               BitsPleaseEmployeeRecords eRec = new BitsPleaseEmployeeRecords();
            }
            catch (Exception x)
            {
            
            }
         }
      }  
   }
}