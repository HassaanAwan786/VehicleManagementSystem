package Services;

import Handlers.PersonRecordHandlers.ApprovalHandler;
import Handlers.PersonRecordHandlers.RecordHandler;
import Handlers.PersonRecordHandlers.RegistrationHandler;
import Structure.Interface.Handler;
import Structure.Person;
import Structure.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author moiza
 */
public class Person_Controller {
    Handler<Person> handler;
    RecordHandler recordHandler = new RecordHandler();

    public void insertRecord(Record record) throws SQLException {
        recordHandler.insert(record);
    }
    public DefaultTableModel getRecord()
    {
        try {
            DefaultTableModel model = new DefaultTableModel();
            ArrayList<Record> list = recordHandler.getAll();
            for (Record record : list) {
                Object[] object = {record.getFrom(), record.getTo(), record.getDate(), record.getDays(), record.getCarType(), record.getFare()};
                model.addRow(object);
            }
            return model;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void register()
    {
        
    }
    public Person login(String UserName, String Password){
        handler = new RegistrationHandler();
        try {
            return handler.get(UserName, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean register(Person pessenger){
        handler = new ApprovalHandler();
        try {
            return handler.insert(pessenger);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Person available(String UserName){
        handler = new RegistrationHandler();
        try {
            return handler.search(UserName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean approvePerson(Person nPassenger){
        handler = new RegistrationHandler();
        try {
            return handler.insert(nPassenger);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	/**
	 *
	 * @param index
	 */
	public boolean delPerson(int index){
        handler = new ApprovalHandler();
        try {
            return handler.deleteAt(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	/**
	 * 
	 * @param index
	 * @param comp
	 */
	public Person searchPerson(int index, Component comp){
		handler = new ApprovalHandler();
        try {
            if(handler.getSize()>0){
                return handler.getAt(index);

            }else{
                JOptionPane.showMessageDialog(null,"List is empty");
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * 
	 * @param name
	 */
	public int searchPerson(String name){
		handler = new ApprovalHandler();
        try {
            return handler.getIndexWhere(name);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
        public int getSizeApproval(Component comp){
        handler = new ApprovalHandler();
            try {
                return handler.getSize();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
