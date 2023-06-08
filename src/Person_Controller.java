package com.backcaps.vehiclemanagement;

import java.awt.Component;
import java.util.List;
import javax.swing.ComboBoxModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author moiza
 */
public class Person_Controller {
    DataHandler datahandler = new DataHandler();
    void insertion(Person pessenger,Component component)
    {
        datahandler.insertion(pessenger,component);
    }
    void Show(Component component)
    {
        datahandler.show(component);
    }
    void register()
    {
        
    }
    boolean login(String UserName,String Password,Component component){
        return datahandler.login(UserName, Password,component);
    }
    void register(Person pessenger,Component component){
        datahandler.register(pessenger, component);
    }
    boolean available(String UserName,Component component){
        return datahandler.available(UserName, component);
    }
    public List<String> getRolesFromList(Component comp){
        return datahandler.getRolesFromList(comp);
    }
    public String checkRole(String userName,Component comp){
        return datahandler.checkRole(userName, comp);
    }
    public boolean approvePerson(Person nPassenger, String role, Component comp){
        return datahandler.approvePerson(nPassenger,role,comp);
    }
	/**
	 * 
	 * @param index
	 * @param comp
	 */
	public boolean delPerson(int index, Component comp){
		return datahandler.deleteApproval(index, comp);
	}
	/**
	 * 
	 * @param index
	 * @param comp
	 */
	public Person searchPerson(int index, Component comp){
		return datahandler.searchApproval(index, comp);
	}

	/**
	 * 
	 * @param name
	 * @param comp
	 */
	public int searchPerson(String name, Component comp){
		return datahandler.searchApproval(name, comp);
	}
        public int getSizeApproval(Component comp){
            return datahandler.getSizeApproval(comp);
        }
}
