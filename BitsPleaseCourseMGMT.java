/**
   The BitsPleaseCourseMGMT class creates teh GUI  that will direct user to either BitsPleaseNewEquipment,
   BitsPleaseEquipmentRecords, or BitsPleaseMainMenu.
*/

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class BitsPleaseCourseMGMT extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private BitsPleaseMMPanel coursePanel, cCenterPanel, cTopPanel, cBottomPanel;  
   private BitsPleaseMMButton cCourseButton, nCourseButton, mainMenuButton;
   private JLabel pageLabel, optionLabel;
   private String type;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
      
   public BitsPleaseCourseMGMT(String t) throws IOException, SQLException
   {
      type = t;
      setTitle("Bits Please Gym MGMT System -" + type + "Courses");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      buildPageLabel();
      pageLabel.setBounds(280, 30, 600, 100);
      buildEquipmentPanel();
      coursePanel.setBounds(379, 150, 266,333);
      
      add(pageLabel);
      add(coursePanel);
            
      setVisible(true);
   }
   private void buildPageLabel()
   {
      pageLabel = new JLabel("BP " + type + " Course MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
   }
   private void buildEquipmentPanel()
   {
      cCourseButton = new BitsPleaseMMButton("Available Courses");
      cCourseButton.addActionListener(new ButtonListener());
      nCourseButton = new BitsPleaseMMButton("New Course");
      nCourseButton.addActionListener(new ButtonListener());
      mainMenuButton = new BitsPleaseMMButton("Main Menu");
      mainMenuButton.addActionListener(new ButtonListener());
      coursePanel = new BitsPleaseMMPanel();
      cTopPanel = new BitsPleaseMMPanel();
      cCenterPanel = new BitsPleaseMMPanel();
      cBottomPanel = new BitsPleaseMMPanel();
      
      coursePanel.setLayout(new BorderLayout());
      coursePanel.setPreferredSize(new Dimension (266,290));
           
      cTopPanel.add(cCourseButton);
      coursePanel.add(cTopPanel, BorderLayout.NORTH);
      cCenterPanel.add(nCourseButton);
      coursePanel.add(cCenterPanel, BorderLayout.CENTER);
      cBottomPanel.add(mainMenuButton);
      coursePanel.add(cBottomPanel, BorderLayout.SOUTH);
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
         else if (actionCommand.equals("New Course"))
         {
            setVisible(false);
            dispose();
            
           try
           {
               if (type.equals("Indvidual"))
               {
                  BitsPleaseIndCourses iCor = new BitsPleaseIndCourses();
               }
               else if (type.equals("Group"))
               {
                  BitsPleaseGroupCourse gCor = new BitsPleaseGroupCourse();
               }
           } 
           catch (Exception x)
           {
               System.out.println("New" + type + " Course :  " + x);
           }

         }
         else if (actionCommand.equals("Available Courses"))
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