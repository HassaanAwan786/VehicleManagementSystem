package Handlers.PersonRecordHandlers;

import Services.Database;
import Structure.Interface.Handler;
import Structure.Record;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecordHandler implements Handler<Record> {
    public String tableName;
    Connection conn;
    public RecordHandler(){
        tableName = "reserve";
        conn = Database.getConnection();
    }
    @Override
    public Record get(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public Record search(String data) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Record> getAll() throws SQLException {
        String SQL = "SELECT * FROM "+tableName;
        PreparedStatement ptst = conn.prepareStatement(SQL);
        ResultSet rs = ptst.executeQuery();
        ArrayList<Record> tempList = new ArrayList<>();
        Record tempRecord;
        while(rs.next())
        {
            tempRecord = new Record();
            tempRecord.setFrom(rs.getString("From"));
            tempRecord.setTo(rs.getString("To"));
            tempRecord.setDate(rs.getString("DATE"));
            tempRecord.setDays(rs.getInt("DAYS"));
            tempRecord.setCarType(rs.getString("CARTYPE"));
            tempRecord.setFare(rs.getInt("FARE"));
            tempList.add(tempRecord);
        }
        return tempList;
    }

    @Override
    public boolean insert(Record record) throws SQLException {
        String SQL = "INSERT INTO "+tableName+" values(?,?,?,?,?,?)";
        PreparedStatement ptst = conn.prepareCall(SQL);
        ptst.setString(1, record.getFrom());
        ptst.setString(2, record.getTo());
        ptst.setString(3,record.getDate());
        ptst.setInt(4, record.getDays());
        ptst.setString(5, record.getCarType());
        ptst.setInt(6, record.getFare());
        ptst.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(Record record) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Record record) throws SQLException {
        return false;
    }

    @Override
    public int getSize() throws SQLException {
        return 0;
    }

    @Override
    public boolean deleteAt(int index) throws SQLException {
        return false;
    }

    @Override
    public Record getAt(int index) throws SQLException {
        return null;
    }

    @Override
    public int getIndexWhere(String data) throws SQLException, IOException {
        return 0;
    }
}
