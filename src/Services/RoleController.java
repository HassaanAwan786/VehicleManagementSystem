package Services;

import Handlers.RoleRecordHandlers.RoleHandler;
import Structure.Interface.Handler;
import Structure.Role;

import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author MacBookPro
 * @version 1.0
 * @created 09-Jan-2023 9:36:28 PM
 */
public class RoleController {
	Handler<Role> handler;

	public RoleController(){
        handler = new RoleHandler();
	}

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
		try {
			return handler.deleteAt(index);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ArrayList<Role> getRolesFromList(){
		RoleHandler handler = new RoleHandler();
		try {
			return handler.getAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean editRole(Role role){
		try {
			return handler.update(role);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Role searchRole(int index){
		try {
			if(handler.getSize()>=index+1){
				return handler.getAt(index);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public int searchRole(String name){
		try {
			return handler.getIndexWhere(name);
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}
        public int getSize(){
			try {
				return handler.getSize();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}//end Services.RoleController