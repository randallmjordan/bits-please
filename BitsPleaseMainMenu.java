/**
     The BitsPleaseMainMenu creates the Main Menu GUI for Bits Please Gym MGMT system. It is accessed
     once a user successfully logs in from the BitsPleaseLogin GUI.
*/

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class BitsPleaseMainMenu extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private BitsPleaseMMPanel membershipPanel, mTopPanel, mCenterPanel, mBottomPanel;
   private BitsPleaseMMPanel coursePanel,cTopPanel, cCenterPanel, cBottomPanel;
   private BitsPleaseMMPanel reportsPanel, rTopPanel, rCenterPanel, rBottomPanel;
   private BitsPleaseMMPanel facilityPanel, fTopPanel, fCenterPanel,fLeft, fMid, fRight;
   private JLabel membershipLabel;
   private BitsPleaseMMButton newMemberButton;
   private BitsPleaseMMButton existingMemberButton;
   private BitsPleaseMMButton memberOptionsButton;
   private JLabel courseLabel;
   private BitsPleaseMMButton indCourseButton;
   private BitsPleaseMMButton groupCourseButton;
   private JLabel reportsLabel;
   private BitsPleaseMMButton memberReportButton;
   private BitsPleaseMMButton courseReportButton;
   private BitsPleaseMMButton businessReportButton;
   private JLabel facilityLabel;
   private BitsPleaseMMButton employeeButton;
   private BitsPleaseMMButton spaceButton;
   private BitsPleaseMMButton equipmentButton;
   private JLabel pageLabel;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
      
   public BitsPleaseMainMenu()throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setResizable(false);
       
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      setContentPane(new BitsPleaseBackImage());
      
      buildPageLabel();
      
      pageLabel.setBounds(280, 30, 600, 100);
      
      buildMembershipPanel();
      
      membershipPanel.setBounds(53, 150, 266, 333);
      
      buildCoursePanel();
      
      coursePanel.setBounds(379, 150, 266,333);
      
      buildReportsPanel();
      
      reportsPanel.setBounds(705, 150, 266,333);
      
      buildFacilityPanel();
      
      facilityPanel.setBounds(53, 550, 918,150);
      
      add(pageLabel);
      add(membershipPanel);
      add(coursePanel);
      add(reportsPanel);
      add(facilityPanel);
      
      setVisible(true);

   }
   
   private void buildPageLabel()
   {
      pageLabel = new JLabel("BP Gym Management System");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
   }
   
   private void buildMembershipPanel()
   {
      
      membershipLabel = new JLabel("Memebership");
      membershipLabel.setHorizontalAlignment(JLabel.CENTER);
      membershipLabel.setVerticalAlignment(JLabel.CENTER);
      newMemberButton = new BitsPleaseMMButton("New Member");
      newMemberButton.addActionListener(new ButtonListener());
      existingMemberButton = new BitsPleaseMMButton("Existing Member");
      existingMemberButton.addActionListener(new ButtonListener());
      memberOptionsButton = new BitsPleaseMMButton("Membership Options");
      memberOptionsButton.addActionListener(new ButtonListener());
                 
      membershipPanel = new BitsPleaseMMPanel();
      mTopPanel = new BitsPleaseMMPanel();
      mCenterPanel = new BitsPleaseMMPanel();
      mBottomPanel = new BitsPleaseMMPanel();
      
      membershipPanel.setLayout(new BorderLayout());
      
      membershipPanel.setPreferredSize(new Dimension(266,333));
                    
      mTopPanel.add(membershipLabel);
      membershipPanel.add(mTopPanel, BorderLayout.NORTH);
      mCenterPanel.add(newMemberButton);
      mCenterPanel.add(existingMemberButton);
      membershipPanel.add(mCenterPanel, BorderLayout.CENTER);
      mCenterPanel.add(memberOptionsButton);
      membershipPanel.add(mBottomPanel, BorderLayout.SOUTH);
      
   }
   private void buildCoursePanel()
   {
      courseLabel = new JLabel("Courses");
      courseLabel.setHorizontalAlignment(JLabel.CENTER);
      courseLabel.setVerticalAlignment(JLabel.CENTER);
      indCourseButton = new BitsPleaseMMButton("Individual");
      indCourseButton.addActionListener(new ButtonListener());
      groupCourseButton = new BitsPleaseMMButton("Group");
      groupCourseButton.addActionListener(new ButtonListener());

      coursePanel = new BitsPleaseMMPanel();
      cTopPanel = new BitsPleaseMMPanel();
      cCenterPanel = new BitsPleaseMMPanel();
      cBottomPanel = new BitsPleaseMMPanel();
      
      coursePanel.setLayout(new BorderLayout());
      
      coursePanel.setPreferredSize(new Dimension (266,333));
           
      cTopPanel.add(courseLabel);
      coursePanel.add(cTopPanel, BorderLayout.NORTH); 
      cCenterPanel.add(indCourseButton);
      coursePanel.add(cCenterPanel, BorderLayout.CENTER);
      cCenterPanel.add(groupCourseButton);
      coursePanel.add(cBottomPanel, BorderLayout.SOUTH);
   }
   private void buildReportsPanel()
   {
      reportsLabel = new JLabel("Reports");
      reportsLabel.setHorizontalAlignment(JLabel.CENTER);
      reportsLabel.setVerticalAlignment(JLabel.CENTER);
      memberReportButton = new BitsPleaseMMButton("Member Reports");
      memberReportButton.addActionListener(new ButtonListener());
      courseReportButton = new BitsPleaseMMButton("Course Reports");
      courseReportButton.addActionListener(new ButtonListener());
      businessReportButton = new BitsPleaseMMButton("Business Reports");
      businessReportButton.addActionListener(new ButtonListener());
      
      reportsPanel = new BitsPleaseMMPanel();
      rTopPanel = new BitsPleaseMMPanel();
      rCenterPanel = new BitsPleaseMMPanel();
      rBottomPanel = new BitsPleaseMMPanel();
      
      reportsPanel.setLayout(new BorderLayout());
      
      reportsPanel.setPreferredSize(new Dimension (266,333));
            
      rTopPanel.add(reportsLabel);
      reportsPanel.add(rTopPanel,BorderLayout.NORTH); 
      rCenterPanel.add(memberReportButton);
      rCenterPanel.add(courseReportButton);
      reportsPanel.add(rCenterPanel, BorderLayout.CENTER);
      rCenterPanel.add(businessReportButton);
      reportsPanel.add(rBottomPanel, BorderLayout.SOUTH);
   }
   private void buildFacilityPanel()
   {
      facilityLabel = new JLabel("Facility MGMT");
      facilityLabel.setHorizontalAlignment(JLabel.CENTER);
      facilityLabel.setVerticalAlignment(JLabel.CENTER);
      employeeButton = new BitsPleaseMMButton("Employees");
      employeeButton.addActionListener(new ButtonListener());
      spaceButton = new BitsPleaseMMButton("Reservable Space");
      spaceButton.addActionListener(new ButtonListener());
      equipmentButton = new BitsPleaseMMButton("Equipment");
      equipmentButton.addActionListener(new ButtonListener());
      
      facilityPanel = new BitsPleaseMMPanel();
      fTopPanel = new BitsPleaseMMPanel();
      fCenterPanel = new BitsPleaseMMPanel();
      fLeft = new BitsPleaseMMPanel();
      fMid = new BitsPleaseMMPanel();
      fRight = new BitsPleaseMMPanel();
      
      facilityPanel.setLayout(new BorderLayout());
            
      facilityPanel.setPreferredSize(new Dimension(918,150));
            
      fTopPanel.add(facilityLabel);
      facilityPanel.add(fTopPanel, BorderLayout.NORTH);
      fLeft.add(employeeButton);
      facilityPanel.add(fLeft,BorderLayout.WEST);
      fMid.add(spaceButton);
      facilityPanel.add(fMid, BorderLayout.CENTER);
      fRight.add(equipmentButton);
      facilityPanel.add(fRight, BorderLayout.EAST);
      
   }
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand = e.getActionCommand();
         
         if (actionCommand.equals("New Member"))
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
         else if (actionCommand.equals("Existing Member"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseExisitingMember eMem = new BitsPleaseExisitingMember();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Membership Options"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseMemberOptions memOpts = new BitsPleaseMemberOptions();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Individual"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseIndCourses iCourse = new BitsPleaseIndCourses();
           } 
           catch (Exception x)
           {
           
           };
         }
         else if (actionCommand.equals("Group"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseGroupCourse gCourse = new BitsPleaseGroupCourse();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Member Reports"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseMemberReport mReport = new BitsPleaseMemberReport();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Course Reports"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseCourseReport cReport = new BitsPleaseCourseReport();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Business Reports"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseBusinessReport bReport = new BitsPleaseBusinessReport();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Employees"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseEmployees emp = new BitsPleaseEmployees();
           } 
           catch (Exception x)
           {
           
           };
         }
         else if (actionCommand.equals("Reservable Space"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseSpace room = new BitsPleaseSpace();
           } 
           catch (Exception x)
           {
           
           }
         }
         else if (actionCommand.equals("Equipment"))
         {
             setVisible(false);
            dispose();
            try
           {
               BitsPleaseEquipmentMGMT eq = new BitsPleaseEquipmentMGMT();
           } 
           catch (Exception x)
           {
           
           }
         }
      }  
      
   }
   
}