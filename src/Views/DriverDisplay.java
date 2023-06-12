package Views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;
public class DriverDisplay {
    private JFrame frame;
    private JLabel nameLabel, cnicLabel, ageLabel, licenceTypeLabel, issuedDateLabel, expiryDateLabel;
    private JTextField nameField, cnicField, ageField, licenceTypeField, issuedDateField, expiryDateField;
    private JButton insertButton, updateButton, deleteButton, viewButton;
    private DriverDAO driverDAO;
    public DriverDisplay()
    {
        driverDAO = new DriverDAO();

        frame = new JFrame("Driver Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout for more flexibility

        // Create GridBagConstraints to specify component positioning
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5); // Add padding
        frame.setSize(200,300);
        frame.setLocationRelativeTo(null);


        nameLabel = new JLabel("Name");
        constraints.gridx = 0;
        constraints.gridy = 0;
        frame.add(nameLabel, constraints);

        nameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        frame.add(nameField, constraints);

        cnicLabel = new JLabel("CNIC");
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(cnicLabel, constraints);

        cnicField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        frame.add(cnicField, constraints);

        ageLabel = new JLabel("Age");
        constraints.gridx = 0;
        constraints.gridy = 2;
        frame.add(ageLabel, constraints);

        ageField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        frame.add(ageField, constraints);

        licenceTypeLabel = new JLabel("Licence Type");
        constraints.gridx = 0;
        constraints.gridy = 3;
        frame.add(licenceTypeLabel, constraints);

        licenceTypeField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 3;
        frame.add(licenceTypeField, constraints);

        issuedDateLabel = new JLabel("Licence Issued Date");
        constraints.gridx = 0;
        constraints.gridy = 4;
        frame.add(issuedDateLabel, constraints);

        issuedDateField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 4;
        frame.add(issuedDateField, constraints);

        expiryDateLabel = new JLabel("Licence Expiry Date");
        constraints.gridx = 0;
        constraints.gridy = 5;
        frame.add(expiryDateLabel, constraints);

        expiryDateField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 5;
        frame.add(expiryDateField, constraints);

        // Customize the buttons' appearance
        insertButton = new JButton("Insert");
        insertButton.setBackground(Color.GREEN);
        insertButton.setPreferredSize(new Dimension(120, 30)); // Set preferred size
        updateButton = new JButton("Update");
        updateButton.setBackground(Color.ORANGE);
        updateButton.setPreferredSize(new Dimension(120, 30)); // Set preferred size
        deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setPreferredSize(new Dimension(120, 30)); // Set preferred size
        viewButton = new JButton("View");
        viewButton.setBackground(Color.GRAY);
        viewButton.setPreferredSize(new Dimension(120, 30)); // Set preferred size

        // Add action listeners to the buttons...
        // Rest of the code...

        // Add the buttons to the frame
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        frame.add(insertButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        frame.add(updateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        frame.add(deleteButton, constraints);
        constraints.gridx = 0;
        constraints.gridy = 9;
        frame.add(viewButton, constraints);

        // Set a background color for the frame
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Add action listeners to the buttons
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Driver driver = new Driver();
                driver.setName(nameField.getText());
                driver.setCnic(cnicField.getText());
                driver.setAge(Integer.parseInt(ageField.getText()));
                driver.setLicenceType(licenceTypeField.getText());
                driver.setLiecnceIssuedDate(issuedDateField.getText());
                driver.setLicenceExpiryDate(expiryDateField.getText());

                driverDAO.insertDriver(driver);
                JOptionPane.showMessageDialog(frame, "Data Inserted Successfully", "message", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Driver driver = new Driver();
                driver.setName(nameField.getText());
                driver.setCnic(cnicField.getText());
                driver.setAge(Integer.parseInt(ageField.getText()));
                driver.setLicenceType(licenceTypeField.getText());
                driver.setLiecnceIssuedDate(issuedDateField.getText());
                driver.setLicenceExpiryDate(expiryDateField.getText());

                driverDAO.updateDriver(driver);
                JOptionPane.showMessageDialog(frame, "Data Updated Successfully ", "message", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cnic = cnicField.getText();
                driverDAO.deleteDriver(cnic);
                JOptionPane.showMessageDialog(frame, "Data Deleted Sucessfully", "message", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DriverDAO driverDAO = new DriverDAO();
                List<Driver> drivers = driverDAO.getAllDrivers();

                // Create the table model
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Name");
                model.addColumn("Cnic");
                model.addColumn("Age");
                model.addColumn("LicenceType");
                model.addColumn("LicenceIssueDate");
                model.addColumn("LicenceExpiryDate");
                model.setRowCount(0);
                for (Driver driver : drivers) {
                    model.addRow(new Object[]{driver.getName(), driver.getCnic(),driver.getAge(),driver.getLicenceType(),driver.getLiecnceIssuedDate(),driver.getLicenceExpiryDate()});
                }

                // Create the table
                JTable bookTable = new JTable(model);

                // Create a scroll pane to contain the table
                JScrollPane scrollPane = new JScrollPane(bookTable);

                // Create a new frame for displaying the table
                JFrame frame = new JFrame();
                frame.setTitle("Driver");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(scrollPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });



        frame.pack();
        frame.setVisible(true);


        // Add a mouse listener to the frame to detect double-click events
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    clearFields();
                }
            }
        });
    }

    private void clearFields()  //will be called on double clicking the  frame
    {
        nameField.setText("");
        cnicField.setText("");
        ageField.setText("");
        licenceTypeField.setText("");
        issuedDateField.setText("");
        expiryDateField.setText("");
    }
    public static void main(String[] args){
        DriverDisplay driverdisplay=new DriverDisplay();
    }
}


