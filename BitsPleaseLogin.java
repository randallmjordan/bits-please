/**
   BitsPleaseLogin class creates the GUI for the Login screen for the Bits Please Gym
   MGMT system. 
*/

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class BitsPleaseLogin extends JFrame 
{
   private JPanel panel;
   private JLabel messageLabel;
   private JTextField userNameField;
   private JLabel passwordLabel;
   private JPasswordField passwordField;
   private JButton loginButton;
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning 
   
   public BitsPleaseLogin()throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      setResizable(false);     
      
      messageLabel = new JLabel("User Name: ");
      userNameField = new JTextField(15);
      passwordLabel = new JLabel("Password: ");
      passwordField = new JPasswordField(15);
      loginButton = new JButton("Login");
      loginButton.addActionListener(new LoginButtonListener());
            
      messageLabel.setBounds(175, 50, 98, 27);
      userNameField.setBounds(250, 50, 98, 27);
      passwordLabel.setBounds(420, 50, 98, 27);
      passwordField.setBounds(500, 50, 98, 27);
      loginButton.setBounds(650, 50, 98, 27);
            
      add(messageLabel);
      add(userNameField);
      add(passwordLabel);
      add(passwordField);
      add(loginButton);
      
      setVisible(true);
   }
   
      
   private class LoginButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String user = userNameField.getText();
         String pass = new String(passwordField.getPassword());
         ResultSet rs = null;
         
         try
         {
            Statement stmt  = BitsPlease.conn.createStatement();;
            rs = stmt.executeQuery("SELECT * FROM Users WHERE " +
                                     "uID='" + user + "' AND Password='" +
                                     pass + "'");
         }
         catch(SQLException ex)
         {
         
         }
         /*try
         {
            Statement stmt2 = BitsPlease.conn.createStatement();
            
                    
            stmt2.execute("INSERT INTO Users VALUES (" +
                         "'user', 'password')");
         }
         catch(SQLException exc)
         {
            System.out.println("ERROR1: " + exc.getMessage());
         } */

         try
         {
            if (rs.next()== true)
            {  
               setVisible(false);
               dispose();
               //JOptionPane.showMessageDialog(null, "Good Job, " + userNameField.getText());
              try
              {
                  BitsPleaseMainMenu menu = new BitsPleaseMainMenu();
              } 
              catch (IOException x)
              {
              
              }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "User Name or Password don't exist");
            }
         }
         catch(Exception exc)
         {
            JOptionPane.showMessageDialog(null, "Exceptionsss" + exc.getMessage());
         }         
      }  
   }      
}