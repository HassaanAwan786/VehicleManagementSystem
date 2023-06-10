package Structure;


/**
 * @author MacBookPro
 * @version 1.0
 * @created 09-Jan-2023 9:36:26 PM
 */
public class Role {

	private int id;
	private String name;
	private AccessRights rights;

    public Role(int id, String name, AccessRights rights) {
        this.id = id;
        this.name = name;
        this.rights = rights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessRights getRights() {
        return rights;
    }

    public void setRights(AccessRights rights) {
        this.rights = rights;
    }

	public Role(){
        id=0;
        name="";
	}

	public void finalize() throws Throwable {

	}
}//end Role