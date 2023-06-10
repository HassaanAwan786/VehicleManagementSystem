package Structure.Interface;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    public T get(String username, String password) throws SQLException;

    public T search(String data) throws SQLException;

    public ArrayList<T> getAll() throws SQLException;

    public boolean insert(T t) throws SQLException;

    public boolean delete(T t) throws SQLException;

    public boolean update(T t) throws SQLException;
}