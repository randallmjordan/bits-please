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
import java.util.logging.*;
import javax.swing.table.*;

public class BitsPleaseBusinessReport extends JFrame
{
   final int WINDOW_WIDTH=1024;
   final int WINDOW_HEIGHT=768;
   private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   private Dimension dim2 = dim;
   private BitsPleaseMMButton returnHome, maintDue, maintOverDue;
   private JTable table;
   private JScrollPane pane;
   private JPanel listPanel, tablePanel;
   private static final long serialVersionUID = 7227575264622776147L; //added to get rid of serializable warning
   private static final Logger LOG = Logger.getLogger( BitsPleaseMemberReport.class.getName() );
   private final DefaultTableModel tableModel = new DefaultTableModel();
   
   public BitsPleaseBusinessReport() throws IOException, SQLException
   {
      
      setTitle("Bits Please Gym MGMT System - Business Reports");
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setResizable(false);
      
      setContentPane(new BitsPleaseBackImage());      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
      
      buildList();
      listPanel.setBounds(20,20,150,708);
      buildTablePanel();
      tablePanel.setBounds(170,20,834, 708);
      
      add(listPanel);
      add(tablePanel);
               
      setVisible(true);
   }
   public void buildList()
   {
      listPanel = new JPanel();
      listPanel.setBackground(new Color(255,229,153));
      listPanel.setLayout(new GridLayout(10,1));
      returnHome = new BitsPleaseMMButton("Main Menu");
      returnHome.addActionListener(new ButtonListener());
      listPanel.add(returnHome);
      maintDue = new BitsPleaseMMButton("Maintenence Due");
      maintDue.addActionListener(new QueryListener());
      maintOverDue = new BitsPleaseMMButton ("Maintenence OverDue");
      maintOverDue.addActionListener(new QueryListener());
            
       
     listPanel.add(maintDue);
     listPanel.add(maintOverDue);
     
   }
   public void buildTablePanel()
   {
      tablePanel = new JPanel();
      tablePanel.setBackground(new Color(255,229,153));
      table = new JTable(tableModel);
      table.setBackground(new Color(255,229,153));
      tablePanel.setLayout(new BorderLayout());
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      pane.setOpaque(false);
      pane.getViewport().setBackground(new Color(255,229,153));
      pane.getViewport().setOpaque(false);
      tablePanel.add(pane, BorderLayout.CENTER);
      
   }
   public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException 
   {

       ResultSetMetaData metaData = rs.getMetaData();
   
       // names of columns
       Vector<String> columnNames = new Vector<String>();
       int columnCount = metaData.getColumnCount();
       for (int column = 1; column <= columnCount; column++) 
       {
           columnNames.add(metaData.getColumnName(column));
       }
   
       // data of the table
       Vector<Vector<Object>> data = new Vector<Vector<Object>>();
       while (rs.next()) 
       {
           Vector<Object> vector = new Vector<Object>();
           for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
               vector.add(rs.getObject(columnIndex));
           }
           data.add(vector);
       }
    

      return new DefaultTableModel(data, columnNames);

   }
   private class QueryListener implements ActionListener
   {
        String query = "";
        public void actionPerformed(ActionEvent e) 
        {
            String actionCommand = e.getActionCommand();
            
            if (actionCommand.equals("Maintenence Due"))
            {
               query = "SELECT * FROM Equipment WHERE CAST(nextMaintDate AS DATE) < CURRENT_DATE + 30 ";
            }
            else if(actionCommand.equals("Maintenence OverDue"))
            {
               query = "SELECT * FROM Equipment WHERE CAST(nextMaintDate AS DATE) < CURRENT_DATE";
            }
            
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        
                        loadData(query);
                        return null;
                    }
                }.execute();
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
   private void loadData(String q) {
        LOG.info("START loadData method");

        //button.setEnabled(false);

        try
        {
            Statement stmt = BitsPlease.conn.createStatement(); 

            ResultSet rs = stmt.executeQuery(q);
            ResultSetMetaData metaData = rs.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            // Data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }

            tableModel.setDataVector(data, columnNames);
            for (int i = 0; i < metaData.getColumnCount(); i++)
            {
               table.getColumnModel().getColumn(i).setPreferredWidth(125);
            }
        } 
        catch (Exception e) {
            LOG.log(Level.SEVERE, "Exception in Load Data", e);
        }
        //button.setEnabled(true);

        LOG.info("END loadData method");
    }
}