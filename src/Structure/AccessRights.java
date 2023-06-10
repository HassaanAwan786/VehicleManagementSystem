package Structure;


/**
 * @author MacBookPro
 * @version 1.0
 * @created 09-Jan-2023 9:36:24 PM
 */
public class AccessRights {

	private boolean canCreate;
	private boolean canDelete;
	private boolean canEdit;
	private boolean canRead;

    public AccessRights(boolean canCreate, boolean canDelete, boolean canEdit, boolean canRead) {
        this.canCreate = canCreate;
        this.canDelete = canDelete;
        this.canEdit = canEdit;
        this.canRead = canRead;
    }

    public boolean isCanCreate() {
        return canCreate;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

	public AccessRights(){

	}

	public void finalize() throws Throwable {

	}
}//end AccessRights