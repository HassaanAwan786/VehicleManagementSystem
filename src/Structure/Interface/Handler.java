package Structure.Interface;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface Handler<T> extends DAO<T>{
    public int getSize() throws SQLException;
    public boolean deleteAt(int index) throws SQLException;
    public T getAt(int index) throws SQLException;
    public int getIndexWhere(String data) throws SQLException, IOException;
}
