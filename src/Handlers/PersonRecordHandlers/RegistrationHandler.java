package Handlers.PersonRecordHandlers;

import Services.Database;
import Structure.Interface.Handler;
import Structure.Person;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class RegistrationHandler implements Handler<Person> {

    private String tableName;
    Connection conn;
    public RegistrationHandler(){
        tableName = "Registration";
        conn = Database.getConnection();
    }
    @Override
    public Person get(String username, String password) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName+" WHERE UserName ='"+username+"' AND Password='"+password+"'");
        Person person = new Person();
        if(rs.next()){
            person.setName(rs.getString("Name"));
            person.setUserName(rs.getString("UserName"));
            person.setPassword(rs.getString("Password"));
            person.setDOB(rs.getString("DOB"));
            person.setRole(rs.getString("Role"));
        }
        return person;
    }

    @Override
    public Person search(String data) throws SQLException {
        try{
            String query = "SELECT * FROM "+tableName+" WHERE UserName = '"+data+"'";
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(query);
            Person nPassenger = new Person();
            nPassenger.setName(rs.getString("Name"));
            nPassenger.setUserName(rs.getString("UserName"));
            nPassenger.setPassword(rs.getString("Password"));
            nPassenger.setDOB(rs.getString("DOB"));
            nPassenger.setRole(rs.getString("Role"));
            return nPassenger;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    @Override
    public ArrayList<Person> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean insert(Person person) throws SQLException {
        String SQL = "INSERT INTO "+tableName+" values(?,?,?,?,?)";
        PreparedStatement ptst = conn.prepareCall(SQL);
        ptst.setString(1, person.getName());
        ptst.setString(2, person.getUserName());
        ptst.setString(3,person.getPassword());
        ptst.setString(4,person.getDOB());
        ptst.setString(5, person.getRole());
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
        return 0;
    }

    @Override
    public boolean deleteAt(int index) {
        return false;
    }

    @Override
    public Person getAt(int index) throws SQLException {
        return null;
    }

    @Override
    public int getIndexWhere(String data) throws SQLException {
        return 0;
    }
}
