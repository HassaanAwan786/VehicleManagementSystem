package Handlers.RoleRecordHandlers;

import Services.Database;
import Structure.AccessRights;
import Structure.Interface.Handler;
import Structure.Role;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleHandler implements Handler<Role> {
    private final String tableName;
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
        String query = "INSERT INTO "+tableName+"(Name,CanCreate,CanDelete,CanEdit,CanRead) VALUES(?,?,?,?,?)";
        PreparedStatement ptst = conn.prepareStatement(query);
        ptst.setString(1,role.getName());
        AccessRights rights = role.getRights();
        ptst.setString(2,rights.isCanCreate() ? 1+"" : 0+"" );
        ptst.setString(3,rights.isCanDelete()? 1+"": 0+"");
        ptst.setString(4,rights.isCanEdit()? 1+"" : 0+"" );
        ptst.setString(5,rights.isCanRead()? 1+"" : 0+"" );
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
        AccessRights rights = role.getRights();
        int canCreate = rights.isCanCreate() ? 1 : 0;
        int canDelete = rights.isCanDelete()? 1: 0;
        int canEdit = rights.isCanEdit()? 1: 0;
        int canRead = rights.isCanRead()?1:0;
        String query = "UPDATE "+tableName+" SET Name ='"+role.getName()+"', CanCreate='"+canCreate+"', CanDelete='"+canDelete+"', CanEdit='"+canEdit+"', CanRead='"+canRead+"' WHERE ID='"+role.getId()+"'";
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        PreparedStatement ptst = conn.prepareStatement(query);
        ptst.executeUpdate();
        return true;
    }

    @Override
    public int getSize() throws SQLException {
        String query ="SELECT COUNT(*) FROM "+tableName;
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean deleteAt(int index) throws SQLException {
        String query ="SELECT * FROM "+tableName;
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        rs.absolute(index+1);
        query = "DELETE FROM Roles WHERE ID='"+rs.getInt("ID")+"' AND Name='"+rs.getString("Name")+"'";
        PreparedStatement ptst = conn.prepareCall(query);
        ptst.execute();
        return true;
    }

    @Override
    public Role getAt(int index) throws SQLException {
        String query = "SELECT * FROM "+tableName;
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        rs.absolute(index+1);
        Role nRole = new Role();
        nRole.setId(rs.getInt("ID"));
        nRole.setName(rs.getString("Name"));
        AccessRights rights = new AccessRights(rs.getBoolean("CanCreate"),rs.getBoolean("CanDelete"),rs.getBoolean("CanEdit"),rs.getBoolean("CanRead"));
        nRole.setRights(rights);
        return nRole;
    }

    @Override
    public int getIndexWhere(String data) throws SQLException, IOException {
        String query = "SELECT EXISTS(SELECT * FROM "+tableName+" WHERE Name = '"+data+"')";
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        int count = 0;
        //Check if the spesified person found in the database
        if (!st.execute(query)) throw new IOException("Role Not Found");
            //if found then return its position index
        else {
            query = "SELECT * FROM " + tableName;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("Name").equals(data)) {
                    break;
                } else count++;
            }
            return count;
        }
    }
}
