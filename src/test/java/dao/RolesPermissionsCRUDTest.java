package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import domain.GroupsRoles;
import domain.RolesPermissions;
import domain.User;

public class RolesPermissionsCRUDTest {

	@Test
	public void test() throws SQLException {
		Connection connection = DriverManager.getConnection(""
				+ "jdbc:hsqldb:hsql://localhost/workdb");
		
		IRepositoryCatalog catalogOf = new RepositoryCatalog(connection);
		RolesPermissions g = new RolesPermissions();
		g.setPermName("insert");
		g.setPermType(1);
		catalogOf.rolesPermissions().add(g);
		
		catalogOf.save();
		
		List<RolesPermissions> rolesPerm = catalogOf.rolesPermissions().getAll();
		
		rolesPerm.get(0).setPermName("delete");
		rolesPerm.get(0).setPermType(0);
		catalogOf.rolesPermissions().update(rolesPerm.get(0));
		
		RolesPermissions rolesPerm_from_db = catalogOf.rolesPermissions().get(rolesPerm.get(0).getId());
		
		catalogOf.rolesPermissions().delete(rolesPerm_from_db);
		
		catalogOf.saveAndClose();		
	}

}
