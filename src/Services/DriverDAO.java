package Services;
import Structure.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DriverDAO {     //linking dataaccess object class for linking database
    private Connection connection;

    public DriverDAO() {
        try {
            // Establish a database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/driver", "root", "new_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDriver(Driver driver) {
        try {
            // Prepare the SQL statement for inserting a driver
            String query = "INSERT INTO driver_record (Name, Cnic, Age, LicenceType, LicenceIssuedDate, LicenceExpiryDate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the values for the parameters in the SQL statement
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getCnic());
            statement.setInt(3, driver.getAge());
            statement.setString(4, driver.getLicenceType());
            statement.setString(5, driver.getLiecnceIssuedDate());
            statement.setString(6, driver.getLicenceExpiryDate());

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the statement
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDriver(Driver driver) {
        try {
            // Prepare the SQL statement for updating a driver
            String query = "UPDATE driver_record SET Name=?, Cnic=?, Age=?, LicenceType=?, LicenceIssuedDate=?, LicenceExpiryDate=? WHERE Cnic=?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the new values for the parameters in the SQL statement
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getCnic());
            statement.setInt(3, driver.getAge());
            statement.setString(4, driver.getLicenceType());
            statement.setString(5, driver.getLiecnceIssuedDate());
            statement.setString(6, driver.getLicenceExpiryDate());
            statement.setString(7, driver.getCnic());

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDriver(String cnic) {
        try {
            // Prepare the SQL statement for deleting a driver
            String query = "DELETE FROM driver_record WHERE Cnic=?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the value for the parameter in the SQL statement
            statement.setString(1, cnic);

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try {
            // Prepare the SQL statement for retrieving all drivers
            String query = "SELECT * FROM driver_record";
            PreparedStatement statement = connection.prepareStatement(query);

            // Execute the SQL statement and get the result set
            ResultSet resultSet = statement.executeQuery();

            // Process the result set and create Driver objects
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setName(resultSet.getString("Name"));
                driver.setCnic(resultSet.getString("Cnic"));
                driver.setAge(resultSet.getInt("Age"));
                driver.setLicenceType(resultSet.getString("LicenceType"));
                driver.setLiecnceIssuedDate(resultSet.getString("LicenceIssuedDate"));
                driver.setLicenceExpiryDate(resultSet.getString("LicenceExpiryDate"));
                drivers.add(driver);
            }

            // Close the result set and the statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
}

