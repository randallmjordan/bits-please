
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

public class BitsPleaseIndCourses extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private JList<String> iCourseList;
   private JScrollPane scroll;
   private JComboBox<String> instrBox;
   private JLabel pageLabel, iCourseName, iCourseDesc, instructor, iCourseStartDate, iCourseEndDate;
   private JPanel listPanel,rowZeroPanel, rowOnePanel, rowTwoPanel, rowThreePanel, rowFourPanel, rowFivePanel, fillerPanel;
   private JTextField iCourseNameField, iCourseDescField,  planSDateField, planEDateField; 
   private JButton createNew, save, close;
   private String value = "", instr="";
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private String[] iCourse = null;
   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
   public BitsPleaseIndCourses() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Trainers");
      
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
         rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM IndCourses");
         while (rs.next())
         {
            iCourse = new String[rs.getInt(1)];
         }
         stmt.close();
         stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT courseName, instructor FROM IndCourses ORDER BY courseName");
         while (rs.next())
         {
            iCourse[i] = rs.getString(1).trim() + " , ( " + rs.getString(2).trim() + " )" ;
            i++;
         }
         iCourseList = new JList<String>(iCourse);
      }
      catch(SQLException e)
      {
         System.out.println("iCourse list error: " + e);
      }
      iCourseList.setBackground(new Color(255,229,153));
      iCourseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      iCourseList.addListSelectionListener(new ListListener());
      scroll = new JScrollPane(iCourseList);
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
      pageLabel = new JLabel("Individual Course MGMT");
      pageLabel.setFont(new Font("Serif", Font.PLAIN, 50));
      rowZeroPanel.add(pageLabel);
   }
   private void buildRowOne()
   {
      rowOnePanel = new JPanel();
      rowOnePanel.setBackground(new Color(255,229,153));
      rowOnePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      iCourseName = new JLabel("Course Name:                 ");
      iCourseNameField =new JTextField(50);
      rowOnePanel.add(iCourseName);
      rowOnePanel.add(iCourseNameField);
   }
   private void buildRowTwo()
   {
      rowTwoPanel = new JPanel();
      rowTwoPanel.setBackground(new Color(255,229,153));
      rowTwoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      iCourseDesc = new JLabel("Course Description:        ");
      iCourseDescField = new JTextField(50);
      rowTwoPanel.add(iCourseDesc);
      rowTwoPanel.add(iCourseDescField); 
   }
   private void buildRowThree()
   {
      rowThreePanel = new JPanel();
      rowThreePanel.setBackground(new Color(255,229,153));
      rowThreePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      instructor = new JLabel("Instructor:                ");
      String[] stuff = null;
      int i = 0;
      ResultSet rs = null;
      try
      {
         Statement stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT COUNT(*) as Total FROM Employees ");
         while (rs.next())
         {
            stuff = new String[rs.getInt(1)];
         }
         stmt.close();
         stmt = BitsPlease.conn.createStatement();;
         rs = stmt.executeQuery("SELECT lastName,firstName  FROM Employees ORDER BY lastName " ); 
         while(rs.next())
         {
            stuff[i]=rs.getString(1).trim()+ " , " + rs.getString(2).trim();
            i++;
         }

         instrBox = new JComboBox<String>(stuff);
         instrBox.addActionListener(new ComboBoxListener());
      }
      catch(SQLException e)
      {
         System.out.println("MemPlans Select: " + e);
      }
      rowThreePanel.add(instructor);
      rowThreePanel.add(instrBox);
   }
   private void buildRowFour()
   {
      rowFourPanel = new JPanel();
      rowFourPanel.setBackground(new Color(255,229,153));
      rowFourPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      iCourseStartDate = new JLabel("Start Date (yyyy-mm-dd):       ");
      iCourseEndDate = new JLabel("       End Date (yyyy-mm-dd)   ");
      planSDateField = new JTextField(15);
      planEDateField = new JTextField(15);
      rowFourPanel.add(iCourseStartDate);
      rowFourPanel.add(planSDateField);
      rowFourPanel.add(iCourseEndDate);
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
         value = iCourseList.getSelectedValue().trim();
         String cDesc = "";
         String instr = "";
         String pSDate = "";
         String pEDate = "";
         String values[] =value.split(" ");
         String instructor = values[3] + " , " + values[5]; 
         ResultSet rs = null;
         
         try
         {
            Statement stmt = BitsPlease.conn.createStatement();;
            
            rs = stmt.executeQuery("SELECT * FROM indCourses WHERE course_name = '" + values[0] + "'" +
                                   "AND instructor ='" + instructor + "'");
            
            while (rs.next())
            {
               cDesc = rs.getString(3);
               
               pSDate = rs.getString(6);
               pEDate = rs.getString(7);
            }
         }
         catch (SQLException ex)
         {
            System.out.println("Plan Select error: " + ex);
         }
         iCourseNameField.setText(values[0]);
         iCourseDescField.setText(cDesc);
         instrBox.setSelectedItem(instructor);
         planSDateField.setText(pSDate);
         planEDateField.setText(pEDate);
      }
   }
    private class ComboBoxListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
        instr = (String) instrBox.getSelectedItem();
                
      }
   }
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String actionCommand = e.getActionCommand();
         if (actionCommand.equals("Create New"))
         {
            /*iCourseNameField.setText("");
            iCourseDescField.setText("");
            instrBox.setSelectedItem("");
            planSDateField.setText("");
            planEDateField.setText("");*/
             setVisible(false);
                    dispose();
             try
                    {
                        BitsPleaseIndCourses iC = new BitsPleaseIndCourses();
                    }
                    catch (Exception k)
                    {
                    }
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
               if (!(BitsPleaseDateCheck.checkDates(planSDateField.getText().trim())) || !(BitsPleaseDateCheck.checkDates(planEDateField.getText().trim())))
               {
                  return;
               }
               if (Arrays.asList(iCourse).contains(value))
               {
                  String values[] =value.split(" ");
                  String instructor = values[3] + " , " + values[5];
                  stmt.execute("UPDATE indCourses SET  " +
                      "courseName ='" + iCourseNameField.getText().trim() +
                      "',instructor ='" + instrBox.getSelectedItem().toString() +
                      "',description ='" + iCourseDescField.getText().trim() +
                      //"',length ='" + .getText().trim() +
                     // "',numOfSession ='" + .getText().trim() +
                      "',start_date ='" + planSDateField.getText().trim() +
                      "',end_date ='" + planEDateField.getText().trim() +
                      "' WHERE courseName ='" + values[0] + "'" +
                       "AND instructor ='" + instructor + "'");               
               }
               else
               {
                  
                  stmt.execute("INSERT INTO indCourses (courseName,instructor, description, start_date,end_date) VALUES (  " +
                      "'" + iCourseNameField.getText().trim() +
                      "','" + instrBox.getSelectedItem().toString() +
                      "','" + iCourseDescField.getText().trim() +
                      //"',length ='" + .getText().trim() +
                     // "',numOfSession ='" + .getText().trim() +
                      "','" + planSDateField.getText().trim() +
                      "','" + planEDateField.getText().trim() +
                       "')");
                    setVisible(false);
                    dispose();
                    try
                    {
                        BitsPleaseIndCourses iC = new BitsPleaseIndCourses();
                    }
                    catch (Exception k)
                    {
                    }
               }
            }
            catch (SQLException ee)
            {
               System.out.println("INDY COURSE/save error: " + ee);
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