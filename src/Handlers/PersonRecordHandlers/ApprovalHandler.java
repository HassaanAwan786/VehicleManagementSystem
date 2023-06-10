package Handlers.PersonRecordHandlers;

import Services.Database;
import Structure.Interface.DAO;
import Structure.Interface.Handler;
import Structure.Person;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ApprovalHandler implements Handler<Person> {

    public String tableName;
    Connection conn;
    public ApprovalHandler(){
        tableName = "Approval";
        conn = Database.getConnection();
    }

    @Override
    public Person get(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public Person search(String data) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Person> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean insert(Person person) throws SQLException {
        String SQL = "INSERT INTO Approval values(?,?,?,?)";
        PreparedStatement ptst = conn.prepareCall(SQL);
        ptst.setString(1, person.getName());
        ptst.setString(2, person.getUserName());
        ptst.setString(3,person.getPassword());
        ptst.setString(4, person.getDOB());
        ptst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(Person person) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Person person) throws SQLException {
        return false;
    }

    @Override
    public int getSize() {
        try {
            String query ="SELECT COUNT(*) FROM Approval";
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e + " Error in SizeFunction");
            return -1;
        }
        return 0;
    }

    @Override
    public boolean deleteAt(int index) throws SQLException {
        String query ="SELECT * FROM "+tableName;
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        rs.absolute(index+1);
        query = "DELETE FROM Approval WHERE Name='"+rs.getString("Name")+"' AND UserName='"+rs.getString("UserName")+"'";
        PreparedStatement ptst = conn.prepareCall(query);
        ptst.execute();
        return true;
    }

    @Override
    public Person getAt(int index) throws SQLException {
        String query = "SELECT * FROM "+tableName;
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        rs.absolute(index+1);
        Person person = new Person();
        person.setName(rs.getString("Name"));
        person.setUserName(rs.getString("UserName"));
        person.setPassword(rs.getString("Password"));
        person.setDOB(rs.getString("DOB"));
        return person;
    }

    @Override
    public int getIndexWhere(String data) throws SQLException, IOException {
        String query = "SELECT EXISTS(SELECT * FROM "+tableName+" WHERE Name = '"+data+"')";
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        int count = 0;
        //Check if the spesified person found in the database
        if (!st.execute(query)) throw new IOException("Passenger Not Found");
            //if found then return its position index
        else {
            query ="SELECT * FROM Approval";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                if(rs.getString("Name").equals(data)){

                    break;
                }else count++;
            }
            return count;
        }
    }
}
