package com.backcaps.vehiclemanagement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author moiza
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class DataHandler {
    void insertion(Person pessenger,Component component)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
            String SQL = "insert into reserve values(?,?,?,?,?,?)";
            PreparedStatement ptst = conn.prepareCall(SQL);
            ptst.setString(1, pessenger.getFROM());
            ptst.setString(2, pessenger.getTO());
            ptst.setString(3,pessenger.getDATE());
            ptst.setInt(4, pessenger.getdays());
            ptst.setString(5, pessenger.getCARTYPE());
            ptst.setInt(6, pessenger.getfare());
            
            ptst.executeUpdate();
            conn.close();
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    void show(Component component)
    {
        try
        {
            JTable History = new JTable();
            String[] columnNames = {"From", "To", "DATE", "DAYS", "CARTYPE", "FARE"};
            JPanel panel = new JPanel();
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
            Statement st = conn.createStatement();
            String SQL = "select * from reserve";
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ResultSet rs = ptst.executeQuery();
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            History.setModel(model);
            DefaultTableModel tm = (DefaultTableModel)History.getModel();
            tm.setRowCount(0);
            while(rs.next())
            {
                Object object[] = {rs.getString("From"),rs.getString("To"),rs.getString("DATE"),rs.getInt("DAYS"),rs.getString("CARTYPE"),rs.getInt("FARE")};
                tm.addRow(object);
            }
            TableColumn column = History.getColumnModel().getColumn(2);
            column.setPreferredWidth(200);
            System.out.println("Number of rows in table model: " + tm.getRowCount());
            panel.add(History);
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            panel.setVisible(true);
            History.setVisible(true);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    void register(Person pessenger,Component component)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
            String SQL = "insert into Approval values(?,?,?,?)";
            PreparedStatement ptst = conn.prepareCall(SQL);
            ptst.setString(1, pessenger.getName());
            ptst.setString(2, pessenger.getUserName());
            ptst.setString(3,pessenger.getPassword());
            ptst.setString(4, pessenger.getDOB());
            ptst.executeUpdate();
            conn.close();
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    boolean available(String UserName,Component component)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Registeration WHERE UserName ='"+UserName+"'");
            rs.next();
            int count = rs.getInt(1);
            return count>0;
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
            return false;
        }
    }
    boolean login(String UserName,String Password,Component component)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Registeration WHERE UserName ='"+UserName+"' AND Password='"+Password+"'");
            rs.next();
            int count = rs.getInt(1);
            return count>0;
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
            return false;
        }
    }
    public int getSizeApproval(Component comp){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
                String query ="SELECT COUNT(*) FROM Approval";
                PreparedStatement st = conn.prepareStatement(query);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp, e+" Error in SizeFunction");
                return -1;
            }
        return 0;
    }
    public List<String> getRolesFromList(Component comp){
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
                String query = "SELECT * FROM Roles";
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query);
                List<String> rolesList = new ArrayList<String>();
                while(rs.next()){
                    rolesList.add(rs.getString("Name"));
                }
                conn.close();
                return rolesList;
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp,e+" Error in roles list");
                return null;
            }
    }
    public boolean deleteApproval(int index, Component comp){
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
                String query ="SELECT * FROM Approval";
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query);
                rs.absolute(index+1);
                query = "DELETE FROM Approval WHERE Name='"+rs.getString("Name")+"' AND UserName='"+rs.getString("UserName")+"'";
                PreparedStatement ptst = conn.prepareCall(query);
                ptst.execute();
                conn.close();
                return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp, e);
                return false;
            }
    }
    public boolean approvePerson(Person nPassenger, String role, Component comp){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
            String SQL = "insert into Registeration values(?,?,?,?,?)";
            PreparedStatement ptst = conn.prepareCall(SQL);
            ptst.setString(1, nPassenger.getName());
            ptst.setString(2, nPassenger.getUserName());
            ptst.setString(3,nPassenger.getPassword());
            ptst.setString(4,nPassenger.getDOB());
            ptst.setString(5, role);
            ptst.executeUpdate();
            conn.close();
            return true;
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(comp,E);
            return false;
        }
    }
    public Person searchApproval(int index, Component comp){
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
                String query = "SELECT * FROM Approval";
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query);
                rs.absolute(index+1);
                Person nPassenger = new Person();
                nPassenger.setName(rs.getString("Name"));
                nPassenger.setUserName(rs.getString("UserName"));
                nPassenger.setPassword(rs.getString("Password"));
                nPassenger.setDOB(rs.getString("DOB"));
                conn.close();
                return nPassenger;
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp,e+" Error in searchApproval");
                return null;
            }
    }
    public int searchApproval(String name, Component comp){
        try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
                String query = "SELECT EXISTS(SELECT * FROM Approval WHERE Name = '"+name+"')";
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                int count = 0;
                //Check if the spesified person found in the database
                if (!st.execute(query)) throw new IOException("Passenger Not Found");
                //if found then return its position index
                else {
                    query ="SELECT * FROM Approval";
                    ResultSet rs = st.executeQuery(query);
                    while(rs.next()){
                        if(rs.getString("Name").equals(name)){
                            conn.close();
                            break;
                        }else count++;
                    }
                return count;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp, e);
                return -1;
            }
    }
    public String checkRole(String userName, Component comp){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleMGMT","root","");
                String query = "SELECT * FROM registeration WHERE UserName = '"+userName+"'";
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query);
                if(rs.next()){
                    return rs.getString("Role");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp, e);
                return "";
            }
        return null;
    }
}
