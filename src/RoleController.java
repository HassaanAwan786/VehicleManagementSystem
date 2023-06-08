package com.backcaps.vehiclemanagement;

import java.awt.Component;



/**
 * @author MacBookPro
 * @version 1.0
 * @created 09-Jan-2023 9:36:28 PM
 */
public class RoleController {

	public RoleDataHandler m_RoleHandler;

	public RoleController(){
            m_RoleHandler = new RoleDataHandler();
	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param role
	 * @param comp
	 */
	public boolean createRole(Role role, Component comp){
		return m_RoleHandler.insertRole(role, comp);
	}

	/**
	 * 
	 * @param index
	 * @param comp
	 */
	public boolean delRole(int index, Component comp){
		return m_RoleHandler.deleteRole(index, comp);
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param rights
	 * @param comp
	 */
	public boolean editRole(int id, String name,int index, AccessRights rights, Component comp){
		return m_RoleHandler.updateRole(id, name, index, rights, comp);
	}

	/**
	 * 
	 * @param index
	 * @param comp
	 */
	public Role searchRole(int index, Component comp){
		return m_RoleHandler.searchRole(index, comp);
	}

	/**
	 * 
	 * @param name
	 * @param comp
	 */
	public int searchRole(String name, Component comp){
		return m_RoleHandler.searchRole(name, comp);
	}
        public int getSize(Component comp){
            return m_RoleHandler.getSize(comp);
        }
}//end RoleController