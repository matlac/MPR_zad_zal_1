package dao;

import java.sql.SQLException;

public interface IRepositoryCatalog {

	public IUserRepository users();
	public IGroupsRepository groups();
	public IGroupRolesRepository groupsRoles();
	public IRolesPermissionsRepository rolesPermissions();
	
	public void saveAndClose() throws SQLException;
	
}
