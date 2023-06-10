package Services;

import Handlers.RoleRecordHandlers.RoleHandler;
import Handlers.RoleRecordHandlers.RoleDataHandler;
import Structure.AccessRights;
import Structure.Interface.Handler;
import Structure.Role;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author MacBookPro
 * @version 1.0
 * @created 09-Jan-2023 9:36:28 PM
 */
public class RoleController {
	Handler<Role> handler;

	public RoleDataHandler m_RoleHandler;

	public RoleController(){
        handler = new RoleHandler();
		m_RoleHandler = new RoleDataHandler();
	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param role
	 * @param comp
	 */
	public boolean createRole(Role role){
		try {
			return handler.insert(role);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param index
	 */
	public boolean delRole(int index){
		return m_RoleHandler.deleteRole(index);
	}
	public ArrayList<Role> getRolesFromList(){
		RoleHandler handler = new RoleHandler();
		try {
			return handler.getAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 
	 * @param id
	 * @param name
	 * @param rights
	 * @param comp
	 */
	public boolean editRole(int id, String name, int index, AccessRights rights, Component comp){
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
}//end Services.RoleController