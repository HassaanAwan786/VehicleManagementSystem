package Handlers.RoleRecordHandlers;

import Services.Database;
import Structure.AccessRights;
import Structure.Interface.Handler;
import Structure.Role;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleHandler implements Handler<Role> {
    private String tableName;
    Connection conn;
    public RoleHandler(){
        tableName = "Roles";
        conn = Database.getConnection();
    }

    @Override
    public Role get(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public Role search(String data) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Role> getAll() throws SQLException {
        String query = "SELECT * FROM "+tableName;
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        ArrayList<Role> rolesList = new ArrayList<Role>();
        Role nRole;
        while(rs.next()){
            nRole = new Role();
            nRole.setName(rs.getString("Name"));

            rolesList.add(nRole);
        }
        return rolesList;
    }

    @Override
    public boolean insert(Role role) throws SQLException {
        String query = "INSERT INTO Roles VALUES(?,?,?,?,?,?)";
        PreparedStatement ptst = conn.prepareStatement(query);
        ptst.setString(1,role.getId()+"");
        ptst.setString(2,role.getName());
        AccessRights rights = role.getRights();
        ptst.setString(3,rights.isCanCreate() ? 1+"" : 0+"" );
        ptst.setString(4,rights.isCanDelete()? 1+"": 0+"");
        ptst.setString(5,rights.isCanEdit()? 1+"" : 0+"" );
        ptst.setString(6,rights.isCanRead()? 1+"" : 0+"" );
        ptst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Saved Successfully");

        return true;
    }

    @Override
    public boolean delete(Role role) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Role role) throws SQLException {
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
    public Role getAt(int index) throws SQLException {
        return null;
    }

    @Override
    public int getIndexWhere(String data) throws SQLException {
        return 0;
    }
}
