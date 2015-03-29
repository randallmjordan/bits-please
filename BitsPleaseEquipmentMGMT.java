/**
   The BitsPleaseEquipmentMGMT class creates teh GUI  that will direct user to either BitsPleaseNewEquipment,
   BitsPleaseEquipmentRecords, or BitsPleaseMainMenu.
*/

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class BitsPleaseEquipmentMGMT extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private BitsPleaseMMPanel equipmentPanel, eCenterPanel, eTopPanel, eBottomPanel;  
   private BitsPleaseMMButton cEquipmentButton, nEquipmentButton, mainMenuButton;
   private JLabel pageLabel, optionLabel;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
      
   public BitsPleaseEquipmentMGMT() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Equipment");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      buildPageLabel();
      pageLabel.setBounds(280, 30, 600, 100);
      buildEquipmentPanel();
      equipmentPanel.setBounds(379, 150, 266,333);
      
      add(pageLabel);
      add(equipmentPanel);
            
      setVisible(true);
   }
   private void buildPageLabel()
   {
      pageLabel = new JLabel("BP Equipment MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
   }
   private void buildEquipmentPanel()
   {
      cEquipmentButton = new BitsPleaseMMButton("Current Equipment");
      cEquipmentButton.addActionListener(new ButtonListener());
      nEquipmentButton = new BitsPleaseMMButton("New Equipment");
      nEquipmentButton.addActionListener(new ButtonListener());
      mainMenuButton = new BitsPleaseMMButton("Main Menu");
      mainMenuButton.addActionListener(new ButtonListener());
      equipmentPanel = new BitsPleaseMMPanel();
      eTopPanel = new BitsPleaseMMPanel();
      eCenterPanel = new BitsPleaseMMPanel();
      eBottomPanel = new BitsPleaseMMPanel();
      
      equipmentPanel.setLayout(new BorderLayout());
      equipmentPanel.setPreferredSize(new Dimension (266,290));
           
      eTopPanel.add(cEquipmentButton);
      equipmentPanel.add(eTopPanel, BorderLayout.NORTH);
      eCenterPanel.add(nEquipmentButton);
      equipmentPanel.add(eCenterPanel, BorderLayout.CENTER);
      eBottomPanel.add(mainMenuButton);
      equipmentPanel.add(eBottomPanel, BorderLayout.SOUTH);
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
         else if (actionCommand.equals("New Equipment"))
         {
            setVisible(false);
            dispose();
            
           try
           {
               BitsPleaseNewEquipment nEq = new BitsPleaseNewEquipment();
           } 
           catch (Exception x)
           {
           
           }

         }
         else if (actionCommand.equals("Current Equipment"))
         {
            setVisible(false);
            dispose();
            
            try
            {
               BitsPleaseEquipmentRecords eRec = new BitsPleaseEquipmentRecords();
            }
            catch (Exception x)
            {
            
            }
         }
      }  
   }
}